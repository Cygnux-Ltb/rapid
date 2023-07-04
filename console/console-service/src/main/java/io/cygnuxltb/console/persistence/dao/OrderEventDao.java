package io.cygnuxltb.console.persistence.dao;

import io.cygnuxltb.console.persistence.entity.TblOrderEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * OrderEvent Repository
 *
 * @author yellow013
 */
@Repository
public interface OrderEventDao extends JpaRepository<TblOrderEvent, Long> {

    /**
     * @param strategyId int
     * @param tradingDay int
     * @return List<OrderEventEntity>
     */
    List<TblOrderEvent> queryByStrategyIdAndTradingDay(int strategyId, int tradingDay);

    /**
     * @param tradingDay int
     * @return List<OrderEventEntity>
     */
    List<TblOrderEvent> queryByTradingDay(int tradingDay);

    /**
     * @param ordSysId long
     * @return List<OrderEventEntity>
     */
    List<TblOrderEvent> queryByOrdSysId(long ordSysId);

}
