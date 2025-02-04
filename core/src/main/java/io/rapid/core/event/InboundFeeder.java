package io.rapid.core.event;

import io.mercury.common.state.StartupException;

import java.io.Closeable;
import java.io.IOException;

public interface InboundFeeder extends
        // 用于清理资源
        Closeable {

    void startup() throws StartupException;

    void shutdown() throws IOException;

    void addEventLoop(InboundEventLoop loop);

    @Override
    default void close() throws IOException {
        shutdown();
    }

}
