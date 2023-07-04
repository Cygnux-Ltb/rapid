package io.cygnuxltb.engine.actor;

import io.horizon.trader.handler.OrderEventHandler;
import io.horizon.trader.serialization.avro.receive.AvroOrderEvent;

import javax.annotation.Nonnull;

public class OrderEventActor implements OrderEventHandler {

    @Override
    public void onOrderEvent(@Nonnull AvroOrderEvent event) {
        // TODO Auto-generated method stub
    }

}
