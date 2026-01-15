package io.cygnux.rapid.infra.service;

import io.cygnux.rapid.infra.component.ApplicationConfiguration;
import io.cygnux.rapid.infra.dto.common.GeneralOption;
import io.cygnux.rapid.infra.dto.req.StrategyParamReq;
import io.cygnux.rapid.infra.dto.req.StrategyReq;
import io.cygnux.rapid.infra.dto.resp.StrategyParamRsp;
import io.cygnux.rapid.infra.dto.resp.StrategyRsp;
import io.cygnux.rapid.infra.persistence.dao.StrategyDao;
import io.cygnux.rapid.infra.persistence.dao.StrategyParamDao;
import io.cygnux.rapid.infra.persistence.entity.StrategyEntity;
import io.cygnux.rapid.infra.persistence.entity.StrategyParamEntity;
import io.cygnux.rapid.infra.service.util.RespConverter;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.eclipse.collections.api.map.ConcurrentMutableMap;
import org.eclipse.collections.impl.map.mutable.ConcurrentHashMap;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Supplier;

import static io.cygnux.rapid.infra.persistence.JpaExecutor.insert;
import static io.cygnux.rapid.infra.persistence.JpaExecutor.insertOrUpdate;
import static io.cygnux.rapid.infra.persistence.JpaExecutor.select;
import static io.mercury.common.lang.Throws.illegalArgument;
import static io.mercury.common.log4j2.Log4j2LoggerFactory.getLogger;
import static java.util.Optional.ofNullable;

@Service
public class StrategyService {

    private static final Logger log = getLogger(StrategyService.class);

    @Resource
    private StrategyDao strategyDao;

    @Resource
    private StrategyParamDao strategyParamDao;

    @Resource
    private UserService userService;

    @Resource
    private AlgoService algoService;

    @Resource
    private ApplicationConfiguration configuration;

    private final List<GeneralOption> strategyOptions = new CopyOnWriteArrayList<>();

    private final ConcurrentMutableMap<Integer, String> strategyNameMap = new ConcurrentHashMap<>();

    private final ConcurrentMutableMap<Integer, String> strategyDisplayNameMap = new ConcurrentHashMap<>();

    /**
     * VEnergy
     * SimpleMA
     * FlexMA
     * SAR
     */
    @PostConstruct
    public void init() {
        // 根据配置初始化4个策略
        // if (configuration.isInitializeData()) {
        insertOrUpdate(strategyDao, dao -> ofNullable(dao.queryByStrategyId(100))
                .orElse(new StrategyEntity().setStrategyId(100))
                .setStrategyName("VEnergy").setStrategyType("Swing").setEnabled(true));

        insertOrUpdate(strategyDao, dao -> ofNullable(dao.queryByStrategyId(101))
                .orElse(new StrategyEntity().setStrategyId(101))
                .setStrategyName("SimpleMA").setStrategyType("Trend").setEnabled(true));

        insertOrUpdate(strategyDao, dao -> ofNullable(dao.queryByStrategyId(102))
                .orElse(new StrategyEntity().setStrategyId(102))
                .setStrategyName("FlexMA").setStrategyType("Trend").setEnabled(true));

        insertOrUpdate(strategyDao, dao -> ofNullable(dao.queryByStrategyId(103))
                .orElse(new StrategyEntity().setStrategyId(103))
                .setStrategyName("SAR").setStrategyType("Reversal").setEnabled(true));
        // }

        // 初始化加载策略显示名称
        strategyDao.findAll().forEach(this::loadStrategyName);
    }

    /**
     * 加载策略显示名称
     *
     * @param entity TStrategyEntity
     */
    private void loadStrategyName(StrategyEntity entity) {
        strategyNameMap.put(entity.getStrategyId(), entity.getStrategyName());
        var displayName = entity.getStrategyName()
                          + "(" + entity.getStrategyId() + "/" + entity.getStrategyType() + ")";
        strategyDisplayNameMap.put(entity.getStrategyId(), displayName);
        strategyOptions.add(new GeneralOption(entity.getStrategyId(), displayName));
    }


    /**
     * @return List<StrategyDTO>
     */
    public List<StrategyRsp> getAllStrategy() {
        return select(StrategyEntity.class,
                () -> strategyDao.getAllByOrderByStrategyIdAsc())
                .stream()
                .map(RespConverter::fromEntity)
                .toList();
    }

    /**
     * @param strategyId int
     * @return StrategyDTO
     */
    public StrategyRsp getStrategy(int strategyId) {
        if (illegalStrategyId(strategyId, log))
            illegalArgument("strategyId");
        StrategyEntity entity = strategyDao.queryByStrategyId(strategyId);
        if (entity == null)
            log.warn("entity == null where strategyId -> {}", strategyId);
        return RespConverter.fromEntity(entity);
    }

    /**
     * @param strategyName String
     * @return StrategyDTO
     */
    public StrategyRsp getStrategy(String strategyName) {
        if (illegalStrategyName(strategyName, log))
            illegalArgument("strategyName");
        StrategyEntity entity = strategyDao.queryByStrategyName(strategyName);
        if (entity == null)
            log.warn("entity == null where strategyName -> {}", strategyName);
        return RespConverter.fromEntity(entity);
    }

    /**
     * @param request StrategyRequest
     * @return boolean
     */
    public boolean putStrategy(StrategyReq request) {
        var entity = ofNullable(strategyDao.queryByStrategyId(request.getStrategyId()))
                .orElse(new StrategyEntity().setStrategyId(request.getStrategyId()))
                .setStrategyName(request.getStrategyName())
                .setStrategyType(request.getStrategyType())
                .setRemark(request.getRemark());
        if (insert(strategyDao, entity)) {
            loadStrategyName(entity);
            return true;
        } else {
            log.warn("StrategyRequest -> {} , insert failed", request);
            return false;
        }

    }

    public void enableStrategy(int strategyId) {
        if (setEnabled(strategyDao.queryByStrategyId(strategyId), true))
            return;
        log.warn("enable strategy failed, strategyId -> {}", strategyId);
    }

    public void disableStrategy(int strategyId) {
        if (setEnabled(strategyDao.queryByStrategyId(strategyId), false))
            return;
        log.warn("disable strategy failed, strategyId -> {}", strategyId);
    }

    private boolean setEnabled(StrategyEntity entity, boolean enabled) {
        if (entity != null)
            try {
                strategyDao.saveAndFlush(entity.setEnabled(enabled));
                return true;
            } catch (Exception e) {
                log.error("Strategy modification status failed, current status {}, id=[{}], name=[{}], msg -> {}",
                        entity.isEnabled(), entity.getStrategyId(), entity.getStrategyName(), e.getMessage(), e);
                return false;
            }
        else
            return false;
    }

    //############################## STRATEGY PARAM ##############################//

    /**
     * @return List<StrategyParamRsp>
     */
    public List<StrategyParamRsp> getStrategyParam() {
        return toStrategyParamRsp(() -> strategyParamDao.findAll());
    }

    /**
     * @param userid     int
     * @param strategyId int
     * @return List<StrategyParamRsp>
     */
    public List<StrategyParamRsp> getStrategyParam(int userid, int strategyId) {
        return toStrategyParamRsp(() -> strategyParamDao.queryBy(userid, strategyId));
    }

    /**
     * @param userid     int
     * @param strategyId int
     * @param algoId     int
     * @param paramName  String
     * @return StrategyParamRsp
     */
    public StrategyParamRsp getStrategyParam(int userid, int strategyId, int algoId, String paramName) {
        return toStrategyParamRsp(strategyParamDao.queryBy(userid, strategyId, algoId, paramName));
    }

    private List<StrategyParamRsp> toStrategyParamRsp(Supplier<List<StrategyParamEntity>> supplier) {
        return select(StrategyParamEntity.class, supplier)
                .stream()
                .map(this::toStrategyParamRsp)
                .toList();
    }

    private StrategyParamRsp toStrategyParamRsp(StrategyParamEntity entity) {
        return RespConverter.fromEntity(entity)
                .setUsername(userService.getUsername(entity.getUserid()))
                .setStrategyDisplayName(getStrategyDisplayName(entity.getStrategyId()))
                .setAlgoDisplayName(algoService.getAlgoDisplayName(entity.getAlgoId()));
    }

    /**
     * @param request StrategyParamReq
     * @return boolean
     */
    public boolean putStrategyParam(StrategyParamReq request) {
        return insertOrUpdate(strategyParamDao,
                jpa -> ofNullable(
                        jpa.queryBy(request.getUserid(), request.getStrategyId(),
                                request.getAlgoId(), request.getParamName()))
                        .orElse(new StrategyParamEntity().setUserid(request.getUserid())
                                .setStrategyId(request.getStrategyId()).setAlgoId(request.getAlgoId())
                                .setParamName(request.getParamName()))
                        .setParamType(request.getParamType())
                        .setParamValue(request.getParamValue())
                        .setRemark(request.getRemark())
        );
    }

    public List<GeneralOption> getStrategyOptions() {
        return strategyOptions;
    }

    public String getStrategyName(int strategyId) {
        return strategyNameMap.get(strategyId);
    }

    public String getStrategyDisplayName(int strategyId) {
        return strategyDisplayNameMap.get(strategyId);
    }

}
