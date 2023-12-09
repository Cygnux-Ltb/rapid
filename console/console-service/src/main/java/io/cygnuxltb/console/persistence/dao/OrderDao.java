package io.cygnuxltb.console.persistence.dao;

import io.cygnuxltb.console.persistence.entity.TblTrdOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Order Repository
 *
 * @author yellow013
 */
@Repository
public interface OrderDao extends JpaRepository<TblTrdOrder, Long> {

    /**
     * @param strategyId      int [策略ID, 可为空]
     * @param accountId       int [账户ID, 不可为空]
     * @param instrumentCode  String [标的ID]
     * @param startTradingDay int [开始日]
     * @param endTradingDay   int [结束日]
     * @return List<OrderEntity>
     */
    @Query("SELECT e FROM #{#entityName} e WHERE 1 = 1 "
            + " AND (e.accountId = :accountId) "
            + " AND (:strategyId = 0 OR e.strategyId = :strategyId) "
            + " AND (:instrumentCode IS NULL OR e.instrumentCode = :instrumentCode) "
            + " AND (:startTradingDay = 0 OR e.tradingDay >= :startTradingDay) "
            + " AND (:endTradingDay = 0 OR e.tradingDay <= :endTradingDay) ")
    List<TblTrdOrder> queryBy(@Param("accountId") int accountId,
                              @Param("strategyId") int strategyId,
                              @Param("instrumentCode") String instrumentCode,
                              @Param("startTradingDay") int startTradingDay,
                              @Param("endTradingDay") int endTradingDay);

    /**
     * @param ordSysId long
     * @return OrderEntity
     */
    TblTrdOrder queryByOrdSysId(long ordSysId);

}
