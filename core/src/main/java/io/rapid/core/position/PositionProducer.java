package io.rapid.core.position;


import io.rapid.core.instrument.Instrument;

@FunctionalInterface
public interface PositionProducer {

    Position produce(int accountId, Instrument instrument);

    PositionProducer DEFAULT = PositionImpl::new;

}
