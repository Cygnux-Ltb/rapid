package io.rapid.engine.barrier;

import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.rapid.core.order.impl.ChildOrder;
import io.rapid.core.risk.OrderBarrier;
import org.slf4j.Logger;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class HighFrequencyBarrier implements OrderBarrier {

	private static final Logger log = Log4j2LoggerFactory.getLogger(HighFrequencyBarrier.class);

	@Override
	public boolean filter(ChildOrder order) {
		switch (order.getDirection()) {
			case LONG, SHORT -> {
				return false;
			}
            default -> {
				log.error("");
				return false;
			}
		}
	}

	public static void main(String[] args) {
		System.out.println(Integer.MAX_VALUE);
	}

}
