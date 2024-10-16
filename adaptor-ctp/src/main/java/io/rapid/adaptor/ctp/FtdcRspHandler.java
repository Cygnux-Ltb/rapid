package io.rapid.adaptor.ctp;

import com.lmax.disruptor.EventHandler;
import io.mercury.common.functional.Handler;
import io.rapid.adaptor.ctp.event.FtdcRspEvent;

/**
 * 实现CTP响应数据处理
 */
@FunctionalInterface
public interface FtdcRspHandler extends Handler<FtdcRspEvent>, EventHandler<FtdcRspEvent> {

    default void onEvent(FtdcRspEvent event, long sequence, boolean endOfBatch) {
        handle(event);
    }

}
