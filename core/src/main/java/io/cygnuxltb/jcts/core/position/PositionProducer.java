package io.cygnuxltb.jcts.core.position;


import io.cygnuxltb.jcts.core.instrument.Instrument;

@FunctionalInterface
public interface PositionProducer<P extends Position> {

	P produce(int accountId, Instrument instrument);

}
