package io.rapid.core.event;

import io.mercury.common.state.StartupException;
import io.rapid.core.event.container.InboundEventLoop;

public interface InboundEventFeeder {

    void startup() throws StartupException;

    void shutdown();

    void addEventLoop(InboundEventLoop loop);

}
