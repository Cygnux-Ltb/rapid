package io.cygnux.rapid.infra.persistence.dao;

import io.cygnux.rapid.infra.persistence.entity.OrderEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * OrderEvent Repository
 *
 * @author yellow013
 */
@Repository
public interface OrderEventDao extends JpaRepository<OrderEventEntity, Long> {

    /**
     * @param accountId       int
     * @param strategyId      int
     * @param startTradingDay int
     * @param endTradingDay   int
     * @return List<TOrderEventEntity>
     */
    @Query("""
            SELECT e FROM #{#entityName} e WHERE 1 = 1
                AND (:accountId = 0 OR e.accountId = :accountId)
                AND (:strategyId = 0 OR e.strategyId = :strategyId)
                AND (:startTradingDay = 0 OR e.tradingDay >= :startTradingDay)
                AND (:endTradingDay = 0 OR e.tradingDay <= :endTradingDay)
            """)
    List<OrderEventEntity> queryBy(@Param("accountId") int accountId,
                                   @Param("strategyId") int strategyId,
                                   @Param("startTradingDay") int startTradingDay,
                                   @Param("endTradingDay") int endTradingDay);

    /**
     * @param tradingDay int
     * @return List<TOrderEventEntity>
     */
    List<OrderEventEntity> queryByTradingDay(int tradingDay);

    /**
     * @param ordSysId long
     * @return List<TOrderEventEntity>
     */
    List<OrderEventEntity> queryByOrdSysId(long ordSysId);

}
