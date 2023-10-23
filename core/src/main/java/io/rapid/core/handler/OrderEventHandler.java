package io.cygnuxltb.jcts.core.handler;

import io.cygnuxltb.jcts.core.ser.event.OrderEvent;

import javax.annotation.Nonnull;

@FunctionalInterface
public interface OrderEventHandler {

    void onOrderEvent(@Nonnull final OrderEvent event);

}
