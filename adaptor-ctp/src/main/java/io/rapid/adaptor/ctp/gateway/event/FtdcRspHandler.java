package io.rapid.adaptor.ctp.gateway.event;

import com.lmax.disruptor.EventHandler;
import io.mercury.common.functional.Handler;

@FunctionalInterface
public interface FtdcRspHandler extends Handler<FtdcRspEvent>, EventHandler<FtdcRspEvent> {

    default void onEvent(FtdcRspEvent rsp, long sequence, boolean endOfBatch) {
        handle(rsp);
    }

}
