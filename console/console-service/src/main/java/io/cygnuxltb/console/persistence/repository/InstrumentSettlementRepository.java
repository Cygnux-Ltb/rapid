package io.cygnuxltb.console.persistence.repository;

import io.cygnuxltb.console.persistence.entity.market.InstrumentSettlementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Nullable;
import java.util.List;

/**
 * InstrumentSettlement Repository
 *
 * @author yellow013
 */
@Repository
public interface InstrumentSettlementRepository extends JpaRepository<InstrumentSettlementEntity, Long> {

    /**
     *
     * @param instrumentCode String
     * @param tradingDay int
     * @return List<InstrumentSettlementEntity>
     */
    List<InstrumentSettlementEntity> queryByInstrumentCodeAndTradingDay(
            @Nullable String instrumentCode, int tradingDay);
    
}
