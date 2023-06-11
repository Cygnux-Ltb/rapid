package io.cygnuxltb.console.persistence.dao;

import io.cygnuxltb.console.persistence.entity.TbtPnl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * PnlDaily Repository
 *
 * @author yellow013
 */
@Repository
public interface PnlDao extends JpaRepository<TbtPnl, Long> {

    /**
     * @param strategyId int
     * @param tradingDay int
     * @return List<PnlEntity>
     */
    @Query("SELECT e FROM #{#entityName} e WHERE 1 = 1"
            + " AND e.strategyId = :strategyId "
            + " AND e.tradingDay = :tradingDay "
    )
    List<TbtPnl> queryBy(int strategyId, int tradingDay);

}
