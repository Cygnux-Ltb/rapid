package io.cygnux.rapid.infra.persistence.dao;

import io.cygnux.rapid.infra.persistence.entity.OrderEntity;
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
public interface OrderDao extends JpaRepository<OrderEntity, Long> {

    /**
     * @param userid          int [账户ID, 可为空]
     * @param accountId       int [账户ID, 可为空]
     * @param strategyId      int [策略ID, 可为空]
     * @param instrumentCode  String [标的ID, 可为空]
     * @param startTradingDay int [开始日, 可为空]
     * @param endTradingDay   int [结束日, 可为空]
     * @return List<TOrderEntity>
     */
    @Query("""
            SELECT e FROM #{#entityName} e WHERE 1 = 1
                AND (:userid = 0 OR e.userid = :userid)
                AND (:accountId = 0 OR e.accountId = :accountId)
                AND (:strategyId = 0 OR e.strategyId = :strategyId)
                AND (:instrumentCode IS NULL OR e.instrumentCode = :instrumentCode)
                AND (:startTradingDay = 0 OR e.tradingDay >= :startTradingDay)
                AND (:endTradingDay = 0 OR e.tradingDay <= :endTradingDay)
            """)
    List<OrderEntity> queryBy(@Param("userid") int userid,
                              @Param("accountId") int accountId,
                              @Param("strategyId") int strategyId,
                              @Param("instrumentCode") String instrumentCode,
                              @Param("startTradingDay") int startTradingDay,
                              @Param("endTradingDay") int endTradingDay);

    /**
     * @param ordSysId long
     * @return TOrderEntity
     */
    OrderEntity queryByOrdSysId(long ordSysId);

}
