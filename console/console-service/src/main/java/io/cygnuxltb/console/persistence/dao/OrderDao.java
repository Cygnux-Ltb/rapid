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
     * @param strategyId      int
     * @param investorId      String
     * @param instrumentCode  String
     * @param startTradingDay int
     * @param endTradingDay   int
     * @return List<OrderEntity>
     */
    @Query("SELECT e FROM #{#entityName} e WHERE 1 = 1"
            + " AND e.strategyId = :strategyId "
            + " AND e.investorId = :investorId "
            + " AND e.instrumentCode = :instrumentCode "
            + " AND e.tradingDay >= :startTradingDay "
            + " AND e.tradingDay <= :endTradingDay "
    )
    List<TblTrdOrder> queryBy(@Param("strategyId") int strategyId,
                              @Param("investorId") String investorId,
                              @Param("instrumentCode") String instrumentCode,
                              @Param("startTradingDay") int startTradingDay,
                              @Param("endTradingDay") int endTradingDay);

    /**
     * @param ordSysId long
     * @return OrderEntity
     */
    TblTrdOrder queryByOrdSysId(long ordSysId);

}
