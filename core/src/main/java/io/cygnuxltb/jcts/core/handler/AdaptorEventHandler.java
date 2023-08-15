package io.cygnuxltb.jcts.core.handler;

import io.cygnuxltb.jcts.core.ser.event.AdaptorEvent;

import javax.annotation.Nonnull;

@FunctionalInterface
public interface AdaptorEventHandler {

    void onAdaptorEvent(@Nonnull final AdaptorEvent event);

}
