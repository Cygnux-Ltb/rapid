package io.cygnuxltb.console.persistence.dao;

import io.cygnuxltb.console.persistence.entity.TblMInstrumentSettlement;
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
public interface InstrumentSettlementDao extends JpaRepository<TblMInstrumentSettlement, Long> {
    /**
     * @param instrumentCode String
     * @param tradingDay     int
     * @return List<InstrumentSettlementEntity>
     */
    @Query("SELECT e FROM #{#entityName} e WHERE 1 = 1"
            + " AND e.tradingDay = :tradingDay "
            + " AND e.instrumentCode LIKE :instrumentCode% "
    )
    List<TblMInstrumentSettlement> queryBy(@Param("tradingDay") int tradingDay,
                                           @Param("instrumentCode") String instrumentCode);

}
