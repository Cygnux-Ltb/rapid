package io.rapid.core.handler;

import io.rapid.core.serializable.avro.event.AdaptorEvent;

import javax.annotation.Nonnull;

@FunctionalInterface
public interface AdaptorEventHandler {

    void onAdaptorEvent(@Nonnull final AdaptorEvent event);

}
