package io.cygnuxltb.console.persistence.repository;

import io.cygnuxltb.console.persistence.entity.trade.OrderEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * OrderEvent Repository
 *
 * @author yellow013
 */
@Repository
public interface OrderEventRepository extends JpaRepository<OrderEventEntity, Long> {

    /**
     * @param strategyId int
     * @param tradingDay int
     * @return List<OrderEventEntity>
     */
    List<OrderEventEntity> queryByStrategyIdAndTradingDay(int strategyId, int tradingDay);

    /**
     * @param tradingDay int
     * @return List<OrderEventEntity>
     */
    List<OrderEventEntity> queryByTradingDay(int tradingDay);

    /**
     * @param ordSysId long
     * @return List<OrderEventEntity>
     */
    List<OrderEventEntity> queryByOrdSysId(long ordSysId);


}
