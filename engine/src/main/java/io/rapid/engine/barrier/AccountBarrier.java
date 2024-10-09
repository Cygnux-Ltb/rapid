package io.rapid.engine.barrier;


import io.rapid.core.order.impl.Order;
import io.rapid.core.risk.OrderBarrier;

import javax.annotation.concurrent.NotThreadSafe;

/**
 *
 */

@NotThreadSafe
public class AccountBarrier implements OrderBarrier {

	@Override
	public boolean filter(Order order) {
		// TODO Auto-generated method stub
		return false;
	}

}
