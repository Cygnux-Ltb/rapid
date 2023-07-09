package io.cygnuxltb.adaptor.ctp.gateway.msg;

import com.lmax.disruptor.EventFactory;

public final class FtdcRspIntegratedMsg {

    public FtdcRspIntegratedMsg() {
    }

    @SuppressWarnings("unused")
    private FtdcRspMsg.FtdcRspType type;

    public static final EventFactory<FtdcRspIntegratedMsg> FACTORY = FtdcRspIntegratedMsg::new;


}