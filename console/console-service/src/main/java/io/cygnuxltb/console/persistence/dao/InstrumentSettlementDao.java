package io.cygnuxltb.console.persistence.dao;

import io.cygnuxltb.console.persistence.entity.MkdInstrumentSettlementEntity;
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
public interface InstrumentSettlementDao extends JpaRepository<MkdInstrumentSettlementEntity, Long> {

    /**
     * @param instrumentCode String
     * @param tradingDay     int
     * @return List<InstrumentSettlementEntity>
     */
    @Query("SELECT e FROM #{#entityName} e WHERE 1 = 1"
            + " AND (e.instrumentCode LIKE :instrumentCode%) "
            + " AND (e.tradingDay = :tradingDay) ")
    List<MkdInstrumentSettlementEntity> queryBy(@Param("instrumentCode") String instrumentCode,
                                                @Param("tradingDay") int tradingDay);

}
