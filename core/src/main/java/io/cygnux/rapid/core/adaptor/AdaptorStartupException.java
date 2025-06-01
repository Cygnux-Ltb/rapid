package io.cygnux.rapid.core.adaptor;

public final class AdaptorStartupException extends RuntimeException {

    public AdaptorStartupException(AdaptorRunningMode mode, String adaptorId, Exception cause) {
        super("Adaptor:[" + adaptorId + "], RunningMode:[" + mode + "] startup failed", cause);
    }

}
