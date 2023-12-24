package io.cygnuxltb.console.service;

import com.github.jsonzou.jmockdata.JMockData;
import io.cygnuxltb.console.SysConfiguration;
import io.cygnuxltb.console.controller.util.ControllerUtil;
import io.cygnuxltb.console.persistence.dao.OrderDao;
import io.cygnuxltb.console.persistence.dao.OrderEventDao;
import io.cygnuxltb.console.persistence.dao.OrderExtDao;
import io.cygnuxltb.console.persistence.entity.TrdOrderEntity;
import io.cygnuxltb.console.persistence.entity.TrdOrderEventEntity;
import io.cygnuxltb.console.service.util.DtoUtil;
import io.cygnuxltb.protocol.http.response.OrderDTO;
import io.cygnuxltb.protocol.http.response.OrderEventDTO;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import jakarta.annotation.Resource;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static io.cygnuxltb.console.controller.util.ControllerUtil.illegalAccountId;
import static io.cygnuxltb.console.controller.util.ControllerUtil.illegalInstrumentCode;
import static io.cygnuxltb.console.controller.util.ControllerUtil.illegalOrdSysId;
import static io.cygnuxltb.console.controller.util.ControllerUtil.illegalStrategyId;
import static io.cygnuxltb.console.controller.util.ControllerUtil.illegalTradingDay;
import static io.cygnuxltb.console.persistence.JpaExecutor.insertOrUpdate;
import static io.cygnuxltb.console.persistence.JpaExecutor.select;
import static io.mercury.common.collections.MutableLists.newFastList;
import static io.mercury.common.lang.Throws.illegalArgument;

@Service
public final class OrderService {

    private static final Logger log = Log4j2LoggerFactory.getLogger(OrderService.class);

    @Resource
    private OrderDao dao;

    @Resource
    private OrderExtDao extDao;

    @Resource
    private OrderEventDao eventDao;

    @Resource
    private SysConfiguration configuration;

    /**
     * @param accountId      int
     * @param strategyId     int
     * @param instrumentCode String
     * @param tradingDay     int
     * @return List<OrderDTO>
     */
    public List<OrderDTO> getOrders(int accountId, int strategyId,
                                    String instrumentCode, int tradingDay) {
        return getOrders(strategyId, accountId, instrumentCode, tradingDay, tradingDay);
    }

    /**
     * @param accountId       int
     * @param strategyId      int
     * @param instrumentCode  String
     * @param startTradingDay int
     * @param endTradingDay   int
     * @return List<TOrder>
     */
    public List<OrderDTO> getOrders(int accountId, int strategyId, String instrumentCode,
                                    int startTradingDay, int endTradingDay) {
        if (illegalAccountId(accountId, log))
            illegalArgument("accountId");
        if (illegalStrategyId(strategyId, log))
            illegalArgument("strategyId");
        if (illegalInstrumentCode(instrumentCode, log))
            illegalArgument("instrumentCode");
        if (illegalTradingDay(startTradingDay, endTradingDay, log))
            illegalArgument("startTradingDay & endTradingDay");
        if (configuration.isMock()) {
            var mockData = new ArrayList<OrderDTO>();
            mockData.add(JMockData.mock(OrderDTO.class));
            mockData.add(JMockData.mock(OrderDTO.class));
            mockData.add(JMockData.mock(OrderDTO.class));
            return mockData;
        }
        return select(TrdOrderEntity.class,
                () -> dao.queryBy(accountId, strategyId, instrumentCode,
                        startTradingDay, endTradingDay))
                .stream()
                .map(DtoUtil::toDto)
                .collect(Collectors.toList());
    }

    /**
     * @param ordSysId long
     * @return List<TOrderEvent>
     */
    public List<OrderEventDTO> getOrderEventsByOrderSysId(long ordSysId) {
        if (illegalOrdSysId(ordSysId, log))
            return newFastList();
        return select(TrdOrderEventEntity.class,
                () -> eventDao.queryByOrdSysId(ordSysId))
                .stream()
                .map(DtoUtil::toDto)
                .collect(Collectors.toList());
    }

    /**
     * @param tradingDay int
     * @return List<OrderEventEntity>
     */
    public List<OrderEventDTO> getOrderEventsByTradingDay(int tradingDay) {
        if (ControllerUtil.illegalTradingDay(tradingDay, log))
            return new FastList<>();
        return select(TrdOrderEventEntity.class,
                () -> eventDao.queryBy(0, tradingDay, tradingDay))
                .stream()
                .map(DtoUtil::toDto)
                .collect(Collectors.toList());
    }

    /**
     * @param entity OrderEntity
     * @return boolean
     */
    public boolean putOrder(TrdOrderEntity entity) {
        return insertOrUpdate(dao, entity);
    }

    /**
     * @param entity OrderEventEntity
     * @return boolean
     */
    public boolean putOrderEvent(TrdOrderEventEntity entity) {
        return insertOrUpdate(eventDao, entity);
    }

}
