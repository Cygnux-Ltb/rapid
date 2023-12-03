package io.cygnuxltb.console.persistence.dao;

import io.cygnuxltb.console.persistence.entity.TblTrdOrderEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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
     * @param strategyId      int
     * @param startTradingDay int
     * @param endTradingDay   int
     * @return List<TblTrdOrderEvent>
     */
    @Query("SELECT e FROM #{#entityName} e WHERE 1 = 1 "
            + " AND (:strategyId = 0 OR e.strategyId = :strategyId) "
            + " AND (:startTradingDay = 0 OR e.tradingDay >= :startTradingDay) "
            + " AND (:endTradingDay = 0 OR e.tradingDay <= :endTradingDay) "
    )
    List<TblTrdOrderEvent> queryBy(int strategyId, int startTradingDay, int endTradingDay);

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
