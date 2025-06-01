package io.cygnux.rapid.adaptor.ctp;

import com.lmax.disruptor.EventHandler;
import io.mercury.common.functional.Handler;
import io.cygnux.rapid.adaptor.ctp.event.FtdcRspEvent;

/**
 * 实现CTP响应数据处理
 */
public sealed interface FtdcRspHandler extends Handler<FtdcRspEvent>, EventHandler<FtdcRspEvent>
        permits FtdcRspHandlerImpl {

    default void onEvent(FtdcRspEvent event, long sequence, boolean endOfBatch) {
        handle(event);
    }

}
