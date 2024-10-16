package io.rapid.core;

import io.rapid.core.event.InboundEventHandler;
import io.rapid.core.strategy.StrategySignal;
import io.rapid.core.strategy.StrategySignalHandler;
import org.eclipse.collections.api.list.MutableList;

/**
 * 需要实现的事件调度器接口<p>
 * (核心处理接口)<p>
 * 1.处理入站事件, 并且管理订单, 策略, 仓位, 风控等管理器的调用顺序<p>
 * 2.负责策略信号的收集与处理.<p>
 * 3.负责管理出站事件的发布.
 */
public interface CoreScheduler extends InboundEventHandler, StrategySignalHandler {


    /**
     * 对本次数据运行产生的信号进行处理
     */
    default void handleSignal(MutableList<StrategySignal> signals) {
        signals.each(this::handleSignal);
        signals.clear();
    }

    /**
     * 对本次数据运行产生的信号进行处理
     *
     * @param signal StrategySignal
     */
    void handleSignal(StrategySignal signal);

}
