package io.cygnuxltb.console.persistence.dao;

import io.cygnuxltb.console.persistence.entity.TblStrategy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Strategy Repository
 *
 * @author yellow013
 */
@Repository
public interface StrategyDao extends JpaRepository<TblStrategy, Long> {

    /**
     * @param strategyId int
     * @return StrategyEntity
     */
    TblStrategy queryByStrategyId(int strategyId);

    /**
     * @param strategyName String
     * @return StrategyEntity
     */
    TblStrategy queryByStrategyName(String strategyName);

}
