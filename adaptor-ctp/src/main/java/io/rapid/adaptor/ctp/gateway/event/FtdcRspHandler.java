package io.rapid.adaptor.ctp.gateway.event;

import com.lmax.disruptor.EventHandler;
import io.mercury.common.functional.Handler;

@FunctionalInterface
public interface FtdcEventHandler extends Handler<FtdcEvent>, EventHandler<FtdcEvent> {

    default void onEvent(FtdcEvent event, long sequence, boolean endOfBatch) {
        handle(event);
    }

}
