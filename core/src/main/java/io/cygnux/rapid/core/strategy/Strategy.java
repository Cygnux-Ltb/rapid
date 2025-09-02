package io.cygnux.rapid.core.strategy;

import io.cygnux.console.api.ValueLimitation;
import io.cygnux.rapid.core.account.SubAccount;
import io.cygnux.rapid.core.instrument.Instrument;
import io.cygnux.rapid.core.mdata.MarketDataConsumer;
import io.cygnux.rapid.core.order.OrdSysIdAllocator;
import io.cygnux.rapid.core.order.OrdSysIdAllocatorKeeper;
import io.cygnux.rapid.core.order.OrderHandler;
import io.cygnux.rapid.core.stream.StreamEventHandler;
import io.mercury.common.epoch.EpochUnit;
import io.mercury.common.param.Params;
import io.mercury.common.state.Enableable;

import javax.annotation.Nonnull;

/**
 * 策略接口
 */
public interface Strategy extends
        // 可用状态控制
        Enableable,
        // 优先级排序
        Comparable<Strategy>,
        // 行情处理
        MarketDataConsumer,
        // 订单处理
        OrderHandler,
        // 流事件处理器
        StreamEventHandler {

    Strategy setParams(Params params);

    int getStrategyId();

    String getStrategyName();

    SubAccount getSubAccount();

    default OrdSysIdAllocator getAllocator() {
        return OrdSysIdAllocatorKeeper.acquireAllocator(getStrategyId());
    }

    void addInstrument(Instrument instrument);

    void onEpochTime(long epochTime, EpochUnit epochUnit);

    void onStrategyEvent(@Nonnull StrategyEvent event);

    void onException(Exception exception) throws StrategyException;

    @Override
    default int compareTo(Strategy o) {
        return Integer.compare(this.getStrategyId(), o.getStrategyId());
    }

    int MIN_STRATEGY_ID = ValueLimitation.MIN_STRATEGY_ID;

    int MAX_STRATEGY_ID = ValueLimitation.MAX_STRATEGY_ID;

    int EXTERNAL_ORDER_STRATEGY_ID = 0;

}
