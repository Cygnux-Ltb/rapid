package io.cygnux.rapid.infra.service;

import io.cygnux.rapid.infra.component.ApplicationConfiguration;
import io.cygnux.rapid.infra.dto.req.NewOrderReq;
import io.cygnux.rapid.infra.dto.resp.OrderEventRsp;
import io.cygnux.rapid.infra.dto.resp.OrderRsp;
import io.cygnux.rapid.infra.persistence.dao.OrderDao;
import io.cygnux.rapid.infra.persistence.dao.OrderEventDao;
import io.cygnux.rapid.infra.persistence.dao.OrderExtDao;
import io.cygnux.rapid.infra.persistence.entity.OrderEntity;
import io.cygnux.rapid.infra.persistence.entity.OrderEventEntity;
import io.cygnux.rapid.infra.service.util.RespConverter;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import jakarta.annotation.Resource;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

import static io.cygnux.rapid.core.types.field.TradingDay.illegalTradingDay;
import static io.cygnux.rapid.infra.persistence.JpaExecutor.insert;
import static io.cygnux.rapid.infra.persistence.JpaExecutor.select;
import static io.mercury.common.collections.MutableLists.newFastList;
import static io.mercury.common.lang.Throws.illegalArgument;

@Service
public class OrderService {

    private static final Logger log = Log4j2LoggerFactory.getLogger(OrderService.class);

    @Resource
    private OrderDao orderDao;

    @Resource
    private OrderExtDao orderExtDao;

    @Resource
    private OrderEventDao orderEventDao;

    @Resource
    private ApplicationConfiguration configuration;

    /**
     * @param userid         int
     * @param accountId      int
     * @param strategyId     int
     * @param instrumentCode String
     * @param tradingDay     int
     * @return List<OrderRsp>
     */
    public List<OrderRsp> getOrders(int userid, int accountId, int strategyId,
                                    String instrumentCode, int tradingDay) {
        return getOrders(userid, accountId, strategyId, instrumentCode, tradingDay, tradingDay);
    }

    /**
     * @param userid          int
     * @param accountId       int
     * @param strategyId      int
     * @param instrumentCode  String
     * @param startTradingDay int
     * @param endTradingDay   int
     * @return List<OrderRsp>
     */
    public List<OrderRsp> getOrders(int userid, int accountId,
                                    int strategyId, String instrumentCode,
                                    int startTradingDay, int endTradingDay) {
        if (illegalAccountId(accountId, log))
            illegalArgument("accountId");
        if (illegalStrategyId(strategyId, log))
            illegalArgument("strategyId");
        if (illegalInstrumentCode(instrumentCode, log))
            illegalArgument("instrumentCode");
        if (illegalTradingDay(startTradingDay, endTradingDay, log))
            illegalArgument("startTradingDay & endTradingDay");
        return select(OrderEntity.class,
                () -> orderDao.queryBy(userid, accountId, strategyId, instrumentCode,
                        startTradingDay, endTradingDay))
                .stream()
                .map(RespConverter::fromEntity)
                .toList();
    }

    /**
     * @param ordSysId long
     * @return List<OrderEventRsp>
     */
    public List<OrderEventRsp> getOrderEventsByOrderSysId(long ordSysId) {
        if (illegalOrdSysId(ordSysId, log))
            return newFastList();
        return select(OrderEventEntity.class,
                () -> orderEventDao.queryByOrdSysId(ordSysId))
                .stream()
                .map(RespConverter::fromEntity)
                .toList();
    }

    /**
     * @param tradingDay int
     * @return List<OrderEventRsp>
     */
    public List<OrderEventRsp> getOrderEventsByTradingDay(int tradingDay) {
        if (illegalTradingDay(tradingDay, log))
            return new FastList<>();
        return select(OrderEventEntity.class,
                () -> orderEventDao.queryBy(0, 0, tradingDay, tradingDay))
                .stream()
                .map(RespConverter::fromEntity)
                .toList();
    }

    /**
     * @param entity TOrderEntity
     * @return boolean
     */
    public boolean putOrder(OrderEntity entity) {
        return insert(orderDao, entity);
    }

    /**
     * @param req NewOrderReq
     * @return boolean
     */
    public boolean putOrder(NewOrderReq req) {
        return false;
    }

    /**
     * @param entity TOrderEventEntity
     * @return boolean
     */
    public boolean putOrderEvent(OrderEventEntity entity) {
        return insert(orderEventDao, entity);
    }

}
