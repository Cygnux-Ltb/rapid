package io.cygnux.rapid.core.position;


import io.cygnux.rapid.core.instrument.Instrument;

@FunctionalInterface
public interface PositionProducer {

    Position produce(int accountId, Instrument instrument);

    PositionProducer DEFAULT = PositionImpl::new;

}
