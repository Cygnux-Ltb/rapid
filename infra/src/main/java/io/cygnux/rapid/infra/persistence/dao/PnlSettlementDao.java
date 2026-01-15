package io.cygnux.rapid.infra.persistence.dao;

import io.cygnux.rapid.infra.persistence.entity.PnlSettlementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * PnlSettlement Repository
 *
 * @author yellow013
 */
@Repository
public interface PnlSettlementDao extends JpaRepository<PnlSettlementEntity, Long> {

    /**
     * @param strategyId int
     * @param tradingDay int
     * @return List<TPnlSettlementEntity>
     */
    @Query("""
            SELECT e FROM #{#entityName} e WHERE 1 = 1
                AND (e.strategyId = :strategyId)
                AND (e.tradingDay = :tradingDay)
            """)
    List<PnlSettlementEntity> queryBy(@Param("strategyId") int strategyId,
                                      @Param("tradingDay") int tradingDay);

}
