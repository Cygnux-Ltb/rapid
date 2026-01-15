package io.cygnux.rapid.infra.service;

import io.cygnux.rapid.infra.dto.common.GeneralOption;
import io.cygnux.rapid.infra.dto.req.AdapterParamReq;
import io.cygnux.rapid.infra.dto.req.AdapterReq;
import io.cygnux.rapid.infra.dto.resp.AdapterParamRsp;
import io.cygnux.rapid.infra.dto.resp.AdapterRsp;
import io.cygnux.rapid.infra.persistence.dao.AdapterDao;
import io.cygnux.rapid.infra.persistence.dao.AdapterParamDao;
import io.cygnux.rapid.infra.persistence.entity.AdapterEntity;
import io.cygnux.rapid.infra.persistence.entity.AdapterParamEntity;
import io.cygnux.rapid.infra.service.util.RespConverter;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.eclipse.collections.api.map.ConcurrentMutableMap;
import org.eclipse.collections.impl.map.mutable.ConcurrentHashMap;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Supplier;

import static io.cygnux.rapid.infra.persistence.JpaExecutor.insertOrUpdate;
import static io.cygnux.rapid.infra.persistence.JpaExecutor.select;
import static java.util.Optional.ofNullable;

@Service
public class AdapterService {

    private static final Logger log = Log4j2LoggerFactory.getLogger(AdapterService.class);

    @Resource
    private AdapterDao adapterDao;

    @Resource
    private AdapterParamDao adapterParamDao;

    private final List<GeneralOption> adaptorOptions = new CopyOnWriteArrayList<>();

    private final ConcurrentMutableMap<Integer, String> adapterNameMap = new ConcurrentHashMap<>();

    private final ConcurrentMutableMap<Integer, String> adapterDisplayNameMap = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        select(AdapterEntity.class, () -> adapterDao.findAll())
                .forEach(adaptor -> {
                    adapterNameMap.put(adaptor.getAdapterId(), adaptor.getAdapterName());
                    var displayName = adaptor.getAdapterName()
                                      + "(" + adaptor.getAdapterId() + "/" + adaptor.getAdapterType() + ")";
                    adapterDisplayNameMap.put(adaptor.getAdapterId(), displayName);
                    adaptorOptions.add(new GeneralOption(adaptor.getAdapterId(), displayName));
                });
    }

    /**
     * @return List<AdaptorRsp>
     */
    public List<AdapterRsp> getAllAdaptor() {
        return select(AdapterEntity.class,
                () -> adapterDao.queryAllWithOrderByAdapterId())
                .stream()
                .map(RespConverter::fromEntity)
                .toList();
    }


    /**
     * @param adaptorId int
     * @return AdaptorRsp
     */
    public AdapterRsp getAdaptor(int adaptorId) {
        return RespConverter.fromEntity(adapterDao.queryByAdapterId(adaptorId));
    }


    /**
     * @param request StrategyRequest
     * @return boolean
     */
    public boolean putAdapter(AdapterReq request) {
        return insertOrUpdate(adapterDao,
                dao -> ofNullable(dao.queryByAdapterId(request.getAdapterId()))
                        .orElse(new AdapterEntity().setAdapterId(request.getAdapterId()))
                        .setAdapterName(request.getAdapterName())
                        .setAdapterType(request.getAdapterType())
                        .setRemark(request.getRemark())
        );
    }

    public void enableAdapter(int adapterId) {
        if (setEnabled(adapterDao.queryByAdapterId(adapterId), true))
            return;
        log.warn("enable adaptor failed, adapterId -> {}", adapterId);
    }

    public void disableAdapter(int adapterId) {
        if (setEnabled(adapterDao.queryByAdapterId(adapterId), false))
            return;
        log.warn("disable adapter failed, adapterId -> {}", adapterId);
    }

    private boolean setEnabled(AdapterEntity entity, boolean enabled) {
        if (entity != null)
            try {
                adapterDao.saveAndFlush(entity.setEnabled(enabled));
                return true;
            } catch (Exception e) {
                log.error("Adapter modification status failed, current status {}, id=[{}], name=[{}], msg -> {}",
                        entity.isEnabled(), entity.getAdapterId(), entity.getAdapterName(), e.getMessage(), e);
                return false;
            }
        else
            return false;
    }


    //############################## ADAPTOR PARAM ##############################//

    /**
     * @return List<AdaptorParamRsp>
     */
    public List<AdapterParamRsp> getAdapterParam() {
        return toAdapterParamRsp(() -> adapterParamDao.findAll());
    }

    /**
     * @param adaptorId  int
     * @param paramGroup String
     * @return List<AdaptorParamRsp>
     */
    public List<AdapterParamRsp> getAdapterParam(int adaptorId, String paramGroup) {
        return toAdapterParamRsp(() -> adapterParamDao.queryBy(adaptorId, paramGroup));
    }

    /**
     * @param adapterId  int
     * @param paramGroup String
     * @param paramName  String
     * @return AdaptorParamRsp
     */
    public AdapterParamRsp getAdapterParam(int adapterId, String paramGroup, String paramName) {
        return toAdapterParamRsp(adapterParamDao.getBy(adapterId, paramGroup, paramName));
    }

    private List<AdapterParamRsp> toAdapterParamRsp(Supplier<List<AdapterParamEntity>> supplier) {
        return select(AdapterParamEntity.class, supplier).stream()
                .map(this::toAdapterParamRsp)
                .toList();
    }

    private AdapterParamRsp toAdapterParamRsp(AdapterParamEntity entity) {
        return RespConverter.fromEntity(entity)
                .setAdapterDisplayName(getAdaptorDisplayName(entity.getAdapterId()));
    }

    /**
     * @param request AdaptorParamReq
     * @return boolean
     */
    public boolean putAdapterParam(AdapterParamReq request) {
        return insertOrUpdate(adapterParamDao,
                jpa -> ofNullable(jpa.getBy(request.getAdapterId(), request.getParamGroup(), request.getParamName()))
                        .orElse(new AdapterParamEntity()
                                .setAdapterId(request.getAdapterId())
                                .setParamGroup(request.getParamGroup())
                                .setParamName(request.getParamName()))
                        .setParamValue(request.getParamValue())
                        .setRemark(request.getRemark())
        );
    }

    public List<GeneralOption> getAdapterOptions() {
        return adaptorOptions;
    }

    public String getAdaptorName(int adaptorId) {
        return adapterNameMap.get(adaptorId);
    }

    public String getAdaptorDisplayName(int adaptorId) {
        return adapterDisplayNameMap.get(adaptorId);
    }

}
