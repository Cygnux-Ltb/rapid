package io.rapid.core.handler;

import io.rapid.core.serializable.avro.event.OrderEvent;

import javax.annotation.Nonnull;

@FunctionalInterface
public interface OrderEventHandler {

    void onOrderEvent(@Nonnull final OrderEvent event);

}
