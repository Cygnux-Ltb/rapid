package io.cygnuxltb.adaptor.ctp;

import com.lmax.disruptor.EventTranslatorOneArg;
import ctp.thostapi.CThostFtdcDepthMarketDataField;
import io.cygnuxltb.adaptor.ctp.gateway.msg.FtdcEvent;
import io.mercury.common.concurrent.ring.RingEventbus;

public final class FtdcTraderMsgHandler {


    private EventTranslatorOneArg<FtdcEvent, CThostFtdcDepthMarketDataField> translatorWithDepthMarketDataField =
            (msg, sequence, arg) -> msg.getDepthMarketData().load(arg);

    public FtdcTraderMsgHandler(RingEventbus<FtdcEvent> eventBus) {


    }


}
