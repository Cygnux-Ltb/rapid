package io.cygnuxltb.adaptor.ctp.gateway.msg;

import com.lmax.disruptor.EventFactory;
import io.cygnuxltb.adaptor.ctp.gateway.rsp.FtdcDepthMarketData;
import io.cygnuxltb.adaptor.ctp.gateway.rsp.FtdcInputOrder;
import io.cygnuxltb.adaptor.ctp.gateway.rsp.FtdcInputOrderAction;
import io.cygnuxltb.adaptor.ctp.gateway.rsp.FtdcInvestorPosition;
import io.cygnuxltb.adaptor.ctp.gateway.rsp.FtdcOrder;
import io.cygnuxltb.adaptor.ctp.gateway.rsp.FtdcOrderAction;
import io.cygnuxltb.adaptor.ctp.gateway.rsp.FtdcRspInfo;
import io.cygnuxltb.adaptor.ctp.gateway.rsp.FtdcTrade;
import io.cygnuxltb.adaptor.ctp.gateway.rsp.state.FtdcMdConnectState;
import io.cygnuxltb.adaptor.ctp.gateway.rsp.state.FtdcTraderConnectState;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 事件循环使用Event类型
 *
 * @author yellow013
 */
@Getter
public final class FtdcEvent {

    public static final EventFactory<FtdcEvent> FTDC_EVENT_FACTORY = FtdcEvent::new;

    @Setter
    @Accessors(chain = true)
    private FtdcRspType type = FtdcRspType.Other;

    // 是否最后一条
    @Setter
    @Accessors(chain = true)
    private boolean isLast = false;

    // 请求编号
    @Setter
    @Accessors(chain = true)
    private int RequestID = 0;

    // 返回交易接口连接信息
    private final FtdcTraderConnectState traderConnect = new FtdcTraderConnectState();

    // 返回行情接口连接信息
    private final FtdcMdConnectState mdConnect = new FtdcMdConnectState();

    // 返回行情
    private final FtdcDepthMarketData depthMarketData = new FtdcDepthMarketData();

    // 返回持仓
    private final FtdcInvestorPosition investorPosition = new FtdcInvestorPosition();

    // 报单推送
    private final FtdcOrder order = new FtdcOrder();

    // 成交推送
    private final FtdcTrade trade = new FtdcTrade();

    // 返回报单错误
    private final FtdcInputOrder inputOrder = new FtdcInputOrder();

    // 返回撤单提交错误
    private final FtdcInputOrderAction inputOrderAction = new FtdcInputOrderAction();

    // 返回撤单错误
    private final FtdcOrderAction orderAction = new FtdcOrderAction();

    // 错误消息
    private final FtdcRspInfo rspInfo = new FtdcRspInfo();

    private FtdcEvent() {
        // For EventFactory call
    }

    /**
     * @author yellow013
     */
    public enum FtdcRspType {

        DepthMarketData,

        TraderConnect,

        MdConnect,

        InvestorPosition,

        Order,

        Trade,

        InputOrder,

        InputOrderAction,

        OrderAction,

        RspInfo,

        Other

    }

}
