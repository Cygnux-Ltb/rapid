package io.cygnuxltb.jcts.engine.barrier;

import io.cygnuxltb.jcts.core.order.ChildOrder;
import io.cygnuxltb.jcts.core.risk.OrderBarrier;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class AdaptorBarrier implements OrderBarrier {

	@Override
	public boolean filter(ChildOrder order) {
		// TODO Auto-generated method stub
		return false;
	}

}
