package io.cygnux.rapid.engine.status;

import com.lmax.disruptor.EventHandler;
import io.cygnux.rapid.core.types.order.Order;


public final class OrderRecorder implements EventHandler<Order> {

	@Override
	public void onEvent(Order event, long sequence, boolean endOfBatch) {
		// TODO
	}

}
