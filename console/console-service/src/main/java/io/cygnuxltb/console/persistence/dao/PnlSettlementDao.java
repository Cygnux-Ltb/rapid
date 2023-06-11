package io.cygnuxltb.console.persistence.dao;

import io.cygnuxltb.console.persistence.entity.TbtPnlSettlement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * PnlSettlementDaily Repository
 *
 * @author yellow013
 */
@Repository
public interface PnlSettlementDao extends JpaRepository<TbtPnlSettlement, Long> {

    /**
     * @param strategyId int
     * @param tradingDay int
     * @return List<PnlSettlementEntity>
     */
    @Query("SELECT e FROM #{#entityName} e WHERE 1 = 1"
            + " AND e.strategyId = :strategyId "
            + " AND e.tradingDay = :tradingDay "
    )
    List<TbtPnlSettlement> queryBy(int strategyId, int tradingDay);

}
