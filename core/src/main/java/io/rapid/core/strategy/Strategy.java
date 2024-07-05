package io.rapid.core.strategy;

import io.mercury.common.state.Enableable;
import io.rapid.core.account.Account;
import io.rapid.core.account.SubAccount;
import io.rapid.core.handler.AdaptorEventHandler;
import io.rapid.core.handler.MarketDataHandler;
import io.rapid.core.handler.OrderHandler;
import io.rapid.core.instrument.Instrument;
import org.eclipse.collections.api.set.ImmutableSet;

import javax.annotation.Nonnull;
import java.io.Closeable;
import java.util.function.Supplier;

/**
 *
 */
public interface Strategy extends
        // 用于控制可用状态
        Enableable,
        // 用于确定优先级
        Comparable<Strategy>,
        // 集成行情处理
        MarketDataHandler,
        // 集成订单处理
        OrderHandler,
        // 集成AdaptorReport处理
        AdaptorEventHandler,
        // 用于资源清理
        Closeable {

    /**
     * 系统可允许的最大策略ID
     */
    int MAX_STRATEGY_ID = 1023;

    int getStrategyId();

    String getStrategyName();

    SubAccount getSubAccount();

    Account getAccount();

    ImmutableSet<Instrument> getInstruments();

    Strategy initialize(@Nonnull Supplier<Boolean> initializer);

    void onStrategyEvent(@Nonnull StrategyEvent event);

    void onThrowable(Throwable throwable) throws StrategyException;

    @Override
    default int compareTo(Strategy o) {
        return Integer.compare(this.getStrategyId(), o.getStrategyId());
    }

}
