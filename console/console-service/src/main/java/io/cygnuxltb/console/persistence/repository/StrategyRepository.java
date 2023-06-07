package io.cygnuxltb.console.persistence.repository;

import io.cygnuxltb.console.persistence.entity.trade.StrategyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Strategy Repository
 *
 * @author yellow013
 */
@Repository
public interface StrategyRepository extends JpaRepository<StrategyEntity, Long> {

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
