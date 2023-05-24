package io.cygnuxltb.console.persistence.repository;

import io.cygnuxltb.console.persistence.entity.PnlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * PnlDaily Repository
 *
 * @author yellow013
 */
@Repository
public interface PnlRepository extends JpaRepository<PnlEntity, Long> {

    /**
     * @param strategyId int
     * @param tradingDay int
     * @return List<PnlEntity>
     */
    List<PnlEntity> queryByStrategyIdAndTradingDay(int strategyId, int tradingDay);

}
