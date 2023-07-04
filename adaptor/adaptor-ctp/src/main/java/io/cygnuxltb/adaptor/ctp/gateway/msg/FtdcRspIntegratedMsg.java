package io.cygnuxltb.channel.ctp.gateway.msg;

import com.lmax.disruptor.EventFactory;
import io.cygnuxltb.channel.ctp.gateway.msg.FtdcRspMsg.FtdcRspType;

public final class FtdcRspIntegratedMsg {

    public FtdcRspIntegratedMsg() {
    }

    @SuppressWarnings("unused")
    private FtdcRspType type;


    public static final EventFactory<FtdcRspIntegratedMsg> FACTORY = FtdcRspIntegratedMsg::new;


}