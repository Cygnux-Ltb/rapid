package io.cygnux.rapid.core.adapter;

public final class AdapterStartupException extends RuntimeException {

    public AdapterStartupException(AdapterRunningMode mode, String adapterId, Exception cause) {
        super("Adapter:[" + adapterId + "], RunningMode:[" + mode + "] startup failed", cause);
    }

}
