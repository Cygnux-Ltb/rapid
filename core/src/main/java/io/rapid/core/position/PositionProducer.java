package io.cygnuxltb.jcts.core.position;


import io.rapid.core.instrument.Instrument;

@FunctionalInterface
public interface PositionProducer<P extends Position> {

	P produce(int accountId, Instrument instrument);

}
