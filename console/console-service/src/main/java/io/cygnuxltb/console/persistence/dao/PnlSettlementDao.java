package io.cygnuxltb.console.persistence.dao;

import io.cygnuxltb.console.persistence.entity.TblTPnlSettlement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * PnlSettlementDaily Repository
 *
 * @author yellow013
 */
@Repository
public interface PnlSettlementDao extends JpaRepository<TblTPnlSettlement, Long> {

    /**
     * @param strategyId int
     * @param tradingDay int
     * @return List<PnlSettlementEntity>
     */
    @Query("SELECT e FROM #{#entityName} e WHERE 1 = 1"
            + " AND e.strategyId = :strategyId "
            + " AND e.tradingDay = :tradingDay "
    )
    List<TblTPnlSettlement> queryBy(@Param("strategyId") int strategyId,
                                    @Param("tradingDay") int tradingDay);

}
