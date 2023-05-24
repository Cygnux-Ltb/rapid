package io.cygnuxltb.console.persistence.dao;

import io.cygnuxltb.console.persistence.dao.base.BaseJpaRepository;
import io.cygnuxltb.console.persistence.entity.OrderEventEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * OrderEvent DAO
 *
 * @author yellow013
 */
@Repository
public interface OrderEventRepository extends BaseJpaRepository<OrderEventEntity> {

    /**
     *
     * @param strategyId int
     * @param tradingDay int
     * @return List<OrderEventEntity>
     */
    List<OrderEventEntity> queryByStrategyIdAndTradingDay(int strategyId, int tradingDay);

    /**
     *
     * @param tradingDay int
     * @return List<OrderEventEntity>
     */
    List<OrderEventEntity> queryByTradingDay(int tradingDay);

    /**
     *
     * @param ordSysId long
     * @return List<OrderEventEntity>
     */
    List<OrderEventEntity> queryByOrdSysId(long ordSysId);


}
