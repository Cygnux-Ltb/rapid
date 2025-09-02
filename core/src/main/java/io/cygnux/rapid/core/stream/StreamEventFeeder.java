package io.cygnux.rapid.core.stream;

import io.mercury.common.state.StartupException;

import java.io.Closeable;
import java.io.IOException;

public interface StreamEventFeeder extends
        // 用于清理资源
        Closeable {

    void startup() throws StartupException;

    void shutdown() throws IOException;

    void addEventbus(StreamEventbus eventbus);

    @Override
    default void close() throws IOException {
        shutdown();
    }

}
