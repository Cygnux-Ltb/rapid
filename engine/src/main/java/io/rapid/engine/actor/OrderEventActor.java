package io.rapid.engine.actor;


import io.rapid.core.handler.OrderEventHandler;
import io.rapid.core.serializable.avro.event.OrderEvent;

import javax.annotation.Nonnull;

public class OrderEventActor implements OrderEventHandler {

    @Override
    public void onOrderEvent(@Nonnull OrderEvent event) {
        // TODO Auto-generated method stub
    }

}
