package io.cygnuxltb.console.persistence.dao;

import io.cygnuxltb.console.persistence.entity.TblTOrderEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * OrderEvent Repository
 *
 * @author yellow013
 */
@Repository
public interface OrderEventDao extends JpaRepository<TblTOrderEvent, Long> {

    /**
     * @param strategyId int
     * @param tradingDay int
     * @return List<OrderEventEntity>
     */
    List<TblTOrderEvent> queryByStrategyIdAndTradingDay(int strategyId, int tradingDay);

    /**
     * @param tradingDay int
     * @return List<OrderEventEntity>
     */
    List<TblTOrderEvent> queryByTradingDay(int tradingDay);

    /**
     * @param ordSysId long
     * @return List<OrderEventEntity>
     */
    List<TblTOrderEvent> queryByOrdSysId(long ordSysId);

}
