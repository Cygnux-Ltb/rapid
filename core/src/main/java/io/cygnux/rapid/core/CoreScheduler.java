package io.cygnux.rapid.core;

import io.cygnux.rapid.core.shared.SharedEventHandler;

/**
 * 需要实现的事件调度器接口<p>
 * (核心处理接口)<p>
 * 1.处理入站事件, 并且管理订单, 策略, 仓位, 风控等管理器的调用顺序<p>
 * 2.负责策略信号的收集与处理.<p>
 * 3.负责管理出站事件的发布.
 */
@Deprecated
public interface CoreScheduler extends SharedEventHandler {

}
