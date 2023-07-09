package io.cygnuxltb.console.service;

import io.cygnuxltb.console.persistence.entity.TblTStrategy;
import io.cygnuxltb.console.persistence.dao.StrategyDao;
import io.cygnuxltb.console.persistence.util.DaoExecutor;
import io.cygnuxltb.console.service.util.DtoConverter;
import io.cygnuxltb.protocol.http.response.StrategyDTO;
import io.mercury.common.lang.Throws;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static io.cygnuxltb.console.controller.util.ControllerUtil.illegalStrategyId;
import static io.cygnuxltb.console.controller.util.ControllerUtil.illegalStrategyName;
import static io.cygnuxltb.console.persistence.util.DaoExecutor.select;

@Service
public final class StrategyService {

    private static final Logger log = Log4j2LoggerFactory.getLogger(StrategyService.class);

    @Resource
    private StrategyDao dao;

    /**
     * @return List<StrategyEntity>
     */
    public List<StrategyDTO> getAllStrategy() {
        return select(TblTStrategy.class,
                () -> dao.findAll())
                .stream().map(DtoConverter::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * @param strategyId int
     * @return StrategyEntity
     */
    public StrategyDTO getStrategy(int strategyId) {
        if (illegalStrategyId(strategyId, log))
            Throws.illegalArgument("strategyId");
        TblTStrategy entity = dao.queryByStrategyId(strategyId);
        if (entity == null)
            log.warn("entity == null where strategyId -> {}", strategyId);
        return DtoConverter.toDTO(entity);
    }

    /**
     * @param strategyName String
     * @return StrategyEntity
     */
    public StrategyDTO getStrategy(String strategyName) {
        if (illegalStrategyName(strategyName, log))
            Throws.illegalArgument("strategyName");
        TblTStrategy entity = dao.queryByStrategyName(strategyName);
        if (entity == null)
            log.warn("entity == null where strategyName -> {}", strategyName);
        return DtoConverter.toDTO(entity);
    }


    /**
     * @param entity StrategyEntity
     * @return boolean
     */
    public boolean putStrategy(TblTStrategy entity) {
        return DaoExecutor.insertOrUpdate(dao, entity);
    }


}
