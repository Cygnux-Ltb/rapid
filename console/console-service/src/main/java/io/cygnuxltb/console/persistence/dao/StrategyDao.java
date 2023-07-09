package io.cygnuxltb.console.persistence.dao;

import io.cygnuxltb.console.persistence.entity.TblTStrategy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Strategy Repository
 *
 * @author yellow013
 */
@Repository
public interface StrategyDao extends JpaRepository<TblTStrategy, Long> {

    /**
     * @param strategyId int
     * @return StrategyEntity
     */
    TblTStrategy queryByStrategyId(int strategyId);

    /**
     * @param strategyName String
     * @return StrategyEntity
     */
    TblTStrategy queryByStrategyName(String strategyName);

}
