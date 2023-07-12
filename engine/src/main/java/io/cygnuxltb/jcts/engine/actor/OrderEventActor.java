package io.cygnuxltb.jcts.engine.actor;


import io.cygnuxltb.jcts.core.handler.OrderEventHandler;
import io.cygnuxltb.jcts.core.serialization.avro.event.AvOrderEvent;

import javax.annotation.Nonnull;

public class OrderEventActor implements OrderEventHandler {

    @Override
    public void onOrderEvent(@Nonnull AvOrderEvent event) {
        // TODO Auto-generated method stub
    }

}
