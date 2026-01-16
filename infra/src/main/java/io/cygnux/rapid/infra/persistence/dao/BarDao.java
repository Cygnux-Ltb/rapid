package io.cygnux.rapid.infra.persistence.dao;

import io.cygnux.rapid.infra.persistence.entity.BarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Bar Repository
 *
 * @author yellow013
 */
@Repository
public interface BarDao extends JpaRepository<BarEntity, Long> {

    /**
     * @param instrumentCode  String
     * @param startTradingDay int
     * @param endTradingDay   int
     * @return List<MBarEntity>
     */
    @Query("""
            SELECT e FROM #{#entityName} e WHERE 1 = 1
                AND (e.instrumentCode = :instrumentCode)
                AND (e.tradingDay >= :startTradingDay)
                AND (e.tradingDay <= :endTradingDay)
            """)
    List<BarEntity> queryBy(@Param("instrumentCode") String instrumentCode,
                            @Param("startTradingDay") int startTradingDay,
                            @Param("endTradingDay") int endTradingDay);

}
