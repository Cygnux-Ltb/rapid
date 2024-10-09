package io.rapid.core.strategy;

import io.mercury.common.epoch.EpochUnit;
import io.mercury.common.param.Params;
import io.mercury.common.state.Enableable;
import io.rapid.core.account.SubAccount;
import io.rapid.core.instrument.Instrument;
import io.rapid.core.mdata.MarketDataConsumer;
import io.rapid.core.order.OrdSysIdAllocator;
import io.rapid.core.order.OrderHandler;

import javax.annotation.Nonnull;
import java.io.Closeable;
import java.util.function.Supplier;

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
        // 资源清理
        Closeable {

    Strategy setParams(Params params);

    int getStrategyId();

    String getStrategyName();

    SubAccount getSubAccount();

    OrdSysIdAllocator getOrdSysIdAllocator();

    void addInstrument(Instrument instrument);

    Strategy initialize(Supplier<Boolean> initializer);

    void onEpochTime(long epochTime, EpochUnit epochUnit);

    void onStrategyEvent(@Nonnull StrategyEvent event);

    void onException(Exception exception) throws StrategyException;

    @Override
    default int compareTo(Strategy o) {
        return Integer.compare(this.getStrategyId(), o.getStrategyId());
    }

}
