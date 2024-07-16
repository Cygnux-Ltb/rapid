package io.rapid.adaptor.ctp.gateway.event;

import com.lmax.disruptor.EventFactory;
import io.rapid.adaptor.ctp.gateway.event.recv.FtdcConnectionStatus;
import io.rapid.adaptor.ctp.gateway.event.recv.FtdcRspInfo;
import io.rapid.adaptor.ctp.gateway.event.recv.md.FtdcDepthMarketData;
import io.rapid.adaptor.ctp.gateway.event.recv.trader.FtdcInputOrder;
import io.rapid.adaptor.ctp.gateway.event.recv.trader.FtdcInputOrderAction;
import io.rapid.adaptor.ctp.gateway.event.recv.trader.FtdcInvestorPosition;
import io.rapid.adaptor.ctp.gateway.event.recv.trader.FtdcOrder;
import io.rapid.adaptor.ctp.gateway.event.recv.trader.FtdcOrderAction;
import io.rapid.adaptor.ctp.gateway.event.recv.trader.FtdcTrade;
import io.rapid.adaptor.ctp.gateway.event.recv.trader.FtdcTradingAccount;
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

    public static final EventFactory<FtdcEvent> EVENT_FACTORY = FtdcEvent::new;

    /**
     * 事件类型
     */
    @Setter
    @Accessors(chain = true)
    private FtdcRspType type = FtdcRspType.Unsupported;

    /**
     * 是否最后一条
     */
    @Setter
    @Accessors(chain = true)
    private boolean last = true;

    /**
     * 请求编号
     */
    @Setter
    @Accessors(chain = true)
    private int requestId = 0;

    /**
     * 距离上次接收报文的时间
     */
    @Setter
    @Accessors(chain = true)
    private int timeLapse = 0;

    /**
     * 错误原因
     */
    @Setter
    @Accessors(chain = true)
    private int reason;

    /**
     * 接口连接信息
     */
    private final FtdcConnectionStatus connectionStatus = new FtdcConnectionStatus();

    /**
     * 行情
     */
    private final FtdcDepthMarketData depthMarketData = new FtdcDepthMarketData();

    /**
     * 返回账户信息
     */
    private final FtdcTradingAccount tradingAccount = new FtdcTradingAccount();

    /**
     * 返回持仓信息
     */
    private final FtdcInvestorPosition investorPosition = new FtdcInvestorPosition();

    /**
     * 报单推送
     */
    private final FtdcOrder order = new FtdcOrder();

    /**
     * 成交推送
     */
    private final FtdcTrade trade = new FtdcTrade();

    /**
     * 返回报单错误
     */
    private final FtdcInputOrder inputOrder = new FtdcInputOrder();

    /**
     * 返回撤单提交错误
     */
    private final FtdcInputOrderAction inputOrderAction = new FtdcInputOrderAction();

    /**
     * 返回撤单错误
     */
    private final FtdcOrderAction orderAction = new FtdcOrderAction();

    /**
     * 错误消息
     */
    private final FtdcRspInfo rspInfo = new FtdcRspInfo();

    /**
     * For EventFactory call
     */
    private FtdcEvent() {
    }

    /**
     * 可处理的事件类型
     */
    public enum FtdcRspType {

        HeartBeatWarning,

        MdConnectionStatus,

        TraderConnectionStatus,

        DepthMarketData,

        TradingAccount,

        InvestorPosition,

        Order,

        Trade,

        InputOrder,

        InputOrderAction,

        OrderAction,

        RspInfo,

        Unsupported

    }

}
