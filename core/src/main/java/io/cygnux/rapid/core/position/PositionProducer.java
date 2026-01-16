package io.cygnux.rapid.core.position;


import io.cygnux.rapid.core.types.instrument.Instrument;
import io.cygnux.rapid.core.types.field.AccountID;

@FunctionalInterface
public interface PositionProducer {

    io.cygnux.rapid.core.types.position.Position produce(int accountId, Instrument instrument);

    PositionProducer DEFAULT = (int accountId, Instrument instrument) ->
            new io.cygnux.rapid.core.types.position.PositionImpl(AccountID.of(accountId), instrument);

}
