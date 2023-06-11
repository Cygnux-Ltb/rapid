package io.cygnuxltb.console.persistence.repository;

import io.cygnuxltb.console.persistence.entity.TbmInstrumentSettlement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.Nullable;
import java.util.List;

/**
 * InstrumentSettlement Repository
 *
 * @author yellow013
 */
@Repository
public interface InstrumentSettlementRepository extends JpaRepository<TbmInstrumentSettlement, Long> {

    /**
     * @param instrumentCode String
     * @param tradingDay     int
     * @return List<InstrumentSettlementEntity>
     */
    @Query("SELECT e FROM #{#entityName} e WHERE 1 = 1"
            + " AND e.instrumentCode LIKE :instrumentCode% "
            + " AND e.tradingDay = :tradingDay "
    )
    List<TbmInstrumentSettlement> queryByInstrumentCodeAndTradingDay(
            @Nullable String instrumentCode, int tradingDay);

}
