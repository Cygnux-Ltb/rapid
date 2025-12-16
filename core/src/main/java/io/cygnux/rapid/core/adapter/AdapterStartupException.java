package io.cygnux.rapid.core.adapter;

public final class AdapterStartupException extends RuntimeException {

    public AdapterStartupException(AdapterRunningMode mode, String adaptorId, Exception cause) {
        super("Adaptor:[" + adaptorId + "], RunningMode:[" + mode + "] startup failed", cause);
    }

}
