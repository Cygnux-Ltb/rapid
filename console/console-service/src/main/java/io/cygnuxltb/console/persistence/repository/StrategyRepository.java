package io.cygnuxltb.console.persistence.dao;

import io.cygnuxltb.console.persistence.dao.base.BaseJpaRepository;
import io.cygnuxltb.console.persistence.entity.StrategyEntity;
import org.springframework.stereotype.Repository;

/**
 * Strategy DAO
 *
 * @author yellow013
 */
@Repository
public interface StrategyDao extends BaseJpaRepository<StrategyEntity> {

    /**
     * @param strategyId int
     * @return StrategyEntity
     */
    StrategyEntity queryByStrategyId(int strategyId);

    /**
     * @param strategyName String
     * @return StrategyEntity
     */
    StrategyEntity queryByStrategyName(String strategyName);

}
