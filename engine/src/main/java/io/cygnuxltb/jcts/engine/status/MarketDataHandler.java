package io.cygnuxltb.jcts.engine.status;

import com.lmax.disruptor.EventHandler;
import io.cygnuxltb.jcts.core.mkd.impl.BasicMarketData;


public final class MarketDataHandler implements EventHandler<BasicMarketData> {

	@Override
	public void onEvent(BasicMarketData event, long sequence, boolean endOfBatch) {
		// TODO
	}

}
