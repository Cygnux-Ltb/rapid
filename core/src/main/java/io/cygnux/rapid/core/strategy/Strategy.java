package io.cygnux.rapid.core.strategy;

import io.cygnux.rapid.core.types.account.SubAccount;
import io.cygnux.rapid.core.types.instrument.Instrument;
import io.cygnux.rapid.core.mdata.MarketDataConsumer;
import io.cygnux.rapid.core.order.OrdSysIdAllocator;
import io.cygnux.rapid.core.order.OrdSysIdAllocatorKeeper;
import io.cygnux.rapid.core.event.SharedEventHandler;
import io.cygnux.rapid.core.types.field.StrategyID;
import io.cygnux.rapid.core.types.strategy.StrategyException;
import io.mercury.common.epoch.EpochUnit;
import io.mercury.common.param.Params;
import io.mercury.common.state.Available;

import javax.annotation.Nonnull;

/**
 * 策略接口
 */
public interface Strategy extends
        // 可用状态控制
        Available,
        // 优先级排序
        Comparable<Strategy>,
        // 行情处理
        MarketDataConsumer,
        // 流事件处理器
        SharedEventHandler {

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

    int MIN_STRATEGY_ID = StrategyID.MIN_STRATEGY_ID;

    int MAX_STRATEGY_ID = StrategyID.MAX_STRATEGY_ID;

    int EXTERNAL_ORDER_STRATEGY_ID = 0;

}
