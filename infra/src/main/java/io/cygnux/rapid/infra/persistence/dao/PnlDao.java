package io.cygnux.rapid.infra.persistence.dao;

import io.cygnux.rapid.infra.persistence.entity.PnlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * PnlDaily Repository
 *
 * @author yellow013
 */
@Repository
public interface PnlDao extends JpaRepository<PnlEntity, Long> {

    /**
     * @param strategyId int
     * @param tradingDay int
     * @return List<TPnlEntity>
     */
    @Query("""
            SELECT e FROM #{#entityName} e WHERE 1 = 1
                AND (e.strategyId = :strategyId)
                AND (e.tradingDay = :tradingDay)
            """)
    List<PnlEntity> queryBy(@Param("strategyId") int strategyId,
                            @Param("tradingDay") int tradingDay);

}
