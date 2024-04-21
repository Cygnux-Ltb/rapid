package io.rapid.engine.status;

import com.lmax.disruptor.EventHandler;
import io.rapid.core.mkd.BasicMarketData;


public final class MarketDataHandler implements EventHandler<BasicMarketData> {

	@Override
	public void onEvent(BasicMarketData event, long sequence, boolean endOfBatch) {
		// TODO
	}

}
