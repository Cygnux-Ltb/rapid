package io.cygnux.rapid.adapter.ctp;

import com.lmax.disruptor.EventHandler;
import io.cygnux.rapid.gateway.ctp.event.FtdcRspEvent;
import io.mercury.common.functional.Handler;

/**
 * 实现CTP响应数据处理
 */
public sealed interface FtdcRspHandler extends Handler<FtdcRspEvent>, EventHandler<FtdcRspEvent>
        permits FtdcRspHandlerImpl {

    default void onEvent(FtdcRspEvent event, long sequence, boolean endOfBatch) {
        handle(event);
    }

}
