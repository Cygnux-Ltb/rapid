package io.cygnuxltb.console.persistence.dao;

import io.cygnuxltb.console.persistence.entity.TblTrdStrategy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Strategy Repository
 *
 * @author yellow013
 */
@Repository
public interface StrategyDao extends JpaRepository<TblTrdStrategy, Long> {

    /**
     * @param strategyId int
     * @return StrategyEntity
     */
    TblTrdStrategy queryByStrategyId(int strategyId);

    /**
     * @param strategyName String
     * @return StrategyEntity
     */
    TblTrdStrategy queryByStrategyName(String strategyName);

}
