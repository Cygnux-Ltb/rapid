package io.cygnuxltb.console.persistence.dao;

import io.cygnuxltb.console.persistence.entity.TblTrdOrderEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * OrderEvent Repository
 *
 * @author yellow013
 */
@Repository
public interface OrderEventDao extends JpaRepository<TblTrdOrderEvent, Long> {

    /**
     * @param strategyId int
     * @param tradingDay int
     * @return List<OrderEventEntity>
     */
    List<TblTrdOrderEvent> queryByStrategyIdAndTradingDay(int strategyId, int tradingDay);

    /**
     * @param tradingDay int
     * @return List<OrderEventEntity>
     */
    List<TblTrdOrderEvent> queryByTradingDay(int tradingDay);

    /**
     * @param ordSysId long
     * @return List<OrderEventEntity>
     */
    List<TblTrdOrderEvent> queryByOrdSysId(long ordSysId);

}
