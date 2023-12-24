package io.cygnuxltb.console.persistence.dao;

import io.cygnuxltb.console.persistence.entity.TrdStrategyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Strategy Repository
 *
 * @author yellow013
 */
@Repository
public interface StrategyDao extends JpaRepository<TrdStrategyEntity, Long> {

    /**
     * @param strategyId int
     * @return StrategyEntity
     */
    TrdStrategyEntity queryByStrategyId(int strategyId);

    /**
     * @param strategyName String
     * @return StrategyEntity
     */
    TrdStrategyEntity queryByStrategyName(String strategyName);

}
