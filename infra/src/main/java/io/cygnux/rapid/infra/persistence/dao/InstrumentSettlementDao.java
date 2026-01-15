package io.cygnux.rapid.infra.persistence.dao;

import io.cygnux.rapid.infra.persistence.entity.InstrumentSettlementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * InstrumentSettlement Repository
 *
 * @author yellow013
 */
@Repository
public interface InstrumentSettlementDao extends JpaRepository<InstrumentSettlementEntity, Long> {

    /**
     * @param instrumentCode String
     * @param tradingDay     int
     * @return List<MInstrumentSettlementEntity>
     */
    @Query("""
            SELECT e FROM #{#entityName} e WHERE 1 = 1
                AND (e.instrumentCode LIKE :instrumentCode%)
                AND (e.tradingDay = :tradingDay)
            """)
    List<InstrumentSettlementEntity> queryBy(@Param("instrumentCode") String instrumentCode,
                                             @Param("tradingDay") int tradingDay);

}
