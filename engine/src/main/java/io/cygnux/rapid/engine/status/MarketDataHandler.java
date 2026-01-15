package io.cygnux.rapid.engine.status;

import com.lmax.disruptor.EventHandler;
import io.cygnux.rapid.core.types.mkd.Level2MarketData;


public final class MarketDataHandler implements EventHandler<Level2MarketData> {

	@Override
	public void onEvent(Level2MarketData event, long sequence, boolean endOfBatch) {
		// TODO
	}

}
