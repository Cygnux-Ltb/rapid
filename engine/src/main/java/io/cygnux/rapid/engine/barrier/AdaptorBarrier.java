package io.cygnux.rapid.engine.barrier;

import io.cygnux.rapid.core.order.impl.ChildOrder;
import io.cygnux.rapid.core.risk.OrderBarrier;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class AdaptorBarrier implements OrderBarrier {

	@Override
	public boolean filter(ChildOrder order) {
		return false;
	}

}
