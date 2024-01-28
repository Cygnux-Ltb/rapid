package io.cygnuxltb.console.service;

import io.cygnuxltb.console.SysConfiguration;
import io.cygnuxltb.console.persistence.dao.StrategyDao;
import io.cygnuxltb.console.persistence.entity.TrdStrategyEntity;
import io.cygnuxltb.console.service.util.DtoUtil;
import io.cygnuxltb.protocol.http.response.dto.StrategyDTO;
import io.mercury.common.lang.Throws;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static io.cygnuxltb.console.controller.util.ControllerUtil.illegalStrategyId;
import static io.cygnuxltb.console.controller.util.ControllerUtil.illegalStrategyName;
import static io.cygnuxltb.console.persistence.JpaExecutor.insertOrUpdate;
import static io.cygnuxltb.console.persistence.JpaExecutor.select;
import static io.mercury.common.log4j2.Log4j2LoggerFactory.getLogger;

@Service
public final class StrategyService {

    private static final Logger log = getLogger(StrategyService.class);

    @Resource
    private StrategyDao dao;

    @Resource
    private SysConfiguration configuration;

    /**
     * @return List<StrategyDTO>
     */
    public List<StrategyDTO> getAllStrategy() {
        return select(TrdStrategyEntity.class,
                () -> dao.findAll())
                .stream().map(DtoUtil::toDto)
                .collect(Collectors.toList());
    }

    /**
     * @param strategyId int
     * @return StrategyDTO
     */
    public StrategyDTO getStrategy(int strategyId) {
        if (illegalStrategyId(strategyId, log))
            Throws.illegalArgument("strategyId");
        TrdStrategyEntity entity = dao.queryByStrategyId(strategyId);
        if (entity == null)
            log.warn("entity == null where strategyId -> {}", strategyId);
        return DtoUtil.toDto(entity);
    }

    /**
     * @param strategyName String
     * @return StrategyDTO
     */
    public StrategyDTO getStrategy(String strategyName) {
        if (illegalStrategyName(strategyName, log))
            Throws.illegalArgument("strategyName");
        TrdStrategyEntity entity = dao.queryByStrategyName(strategyName);
        if (entity == null)
            log.warn("entity == null where strategyName -> {}", strategyName);
        return DtoUtil.toDto(entity);
    }


    /**
     * @param entity TrdStrategyEntity
     * @return boolean
     */
    public boolean putStrategy(TrdStrategyEntity entity) {
        return insertOrUpdate(dao, entity);
    }

}
