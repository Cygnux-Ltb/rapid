package io.cygnuxltb.console.persistence.dao;

import io.cygnuxltb.console.persistence.dao.base.BaseJpaRepository;
import io.cygnuxltb.console.persistence.entity.PnlEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * PnlDaily DAO
 *
 * @author yellow013
 */
@Repository
public interface PnlRepository extends BaseJpaRepository<PnlEntity> {

    /**
     * @param strategyId int
     * @param tradingDay int
     * @return List<PnlEntity>
     */
    List<PnlEntity> queryByStrategyIdAndTradingDay(int strategyId, int tradingDay);

}
