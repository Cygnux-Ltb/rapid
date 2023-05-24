package io.cygnuxltb.console.persistence.dao;

import io.cygnuxltb.console.persistence.dao.base.BaseJpaRepository;
import io.cygnuxltb.console.persistence.entity.InstrumentSettlementEntity;
import org.springframework.stereotype.Repository;

import javax.annotation.Nullable;
import java.util.List;

/**
 * InstrumentSettlement DAO
 *
 * @author yellow013
 */
@Repository
public interface InstrumentSettlementRepository extends BaseJpaRepository<InstrumentSettlementEntity> {

    /**
     *
     * @param instrumentCode String
     * @param tradingDay int
     * @return List<InstrumentSettlementEntity>
     */
    List<InstrumentSettlementEntity> queryByInstrumentCodeAndTradingDay(
            @Nullable String instrumentCode, int tradingDay);
    
}
