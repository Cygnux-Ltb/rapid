package io.rapid.adaptor.ctp.gateway.event;

import com.lmax.disruptor.EventFactory;
import io.rapid.adaptor.ctp.serializable.avro.md.FtdcDepthMarketData;
import io.rapid.adaptor.ctp.serializable.avro.md.FtdcSpecificInstrument;
import io.rapid.adaptor.ctp.serializable.avro.pack.FtdcRspType;
import io.rapid.adaptor.ctp.serializable.avro.shared.FrontDisconnected;
import io.rapid.adaptor.ctp.serializable.avro.shared.HeartBeatWarning;
import io.rapid.adaptor.ctp.serializable.avro.shared.RspError;
import io.rapid.adaptor.ctp.serializable.avro.shared.RspUserLogin;
import io.rapid.adaptor.ctp.serializable.avro.shared.UserLogout;
import io.rapid.adaptor.ctp.serializable.avro.trader.FtdcInputOrder;
import io.rapid.adaptor.ctp.serializable.avro.trader.FtdcInputOrderAction;
import io.rapid.adaptor.ctp.serializable.avro.trader.FtdcInstrumentStatus;
import io.rapid.adaptor.ctp.serializable.avro.trader.FtdcInvestorPosition;
import io.rapid.adaptor.ctp.serializable.avro.trader.FtdcOrder;
import io.rapid.adaptor.ctp.serializable.avro.trader.FtdcOrderAction;
import io.rapid.adaptor.ctp.serializable.avro.trader.FtdcTrade;
import io.rapid.adaptor.ctp.serializable.avro.trader.FtdcTradingAccount;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 事件循环使用Event类型
 *
 * @author yellow013
 */
@Getter
public final class FtdcRspEvent {

    public static final EventFactory<FtdcRspEvent> EVENT_FACTORY = FtdcRspEvent::new;

    /**
     * 事件类型
     */
    @Setter
    @Accessors(chain = true)
    private FtdcRspType type = FtdcRspType.Unsupported;

    /**
     * 毫秒时间戳
     */
    @Setter
    @Accessors(chain = true)
    private long epochMillis;


    //////////////////////////////////// SHARED RSP ////////////////////////////////////
    /**
     * 通信连接断开
     */
    private final FrontDisconnected frontDisconnected = new FrontDisconnected();
    /**
     * 心跳超时警告
     */
    private final HeartBeatWarning heartBeatWarning = new HeartBeatWarning();
    /**
     * 错误应答
     */
    private final RspError rspError = new RspError();
    /**
     * 用户登录响应
     */
    private final RspUserLogin rspUserLogin = new RspUserLogin();
    /**
     * 用户登出请求(响应)
     */
    private final UserLogout userLogout = new UserLogout();


    //////////////////////////////////// MD RSP ////////////////////////////////////
    /**
     * 行情
     */
    private final FtdcDepthMarketData ftdcDepthMarketData = new FtdcDepthMarketData();
    /**
     * 指定的合约
     */
    private final FtdcSpecificInstrument ftdcSpecificInstrument = new FtdcSpecificInstrument();


    //////////////////////////////////// TRADER RSP ////////////////////////////////////
    /**
     * 返回报单错误
     */
    private final FtdcInputOrder ftdcInputOrder = new FtdcInputOrder();

    /**
     * 返回撤单提交错误
     */
    private final FtdcInputOrderAction ftdcInputOrderAction = new FtdcInputOrderAction();

    /**
     * 合约状态
     */
    private final FtdcInstrumentStatus ftdcInstrumentStatus = new FtdcInstrumentStatus();

    /**
     * 持仓信息
     */
    private final FtdcInvestorPosition ftdcInvestorPosition = new FtdcInvestorPosition();

    /**
     * 报单推送
     */
    private final FtdcOrder ftdcOrder = new FtdcOrder();

    /**
     * 返回撤单错误
     */
    private final FtdcOrderAction ftdcOrderAction = new FtdcOrderAction();

    /**
     * 成交推送
     */
    private final FtdcTrade ftdcTrade = new FtdcTrade();

    /**
     * 账户信息(余额)
     */
    private final FtdcTradingAccount ftdcTradingAccount = new FtdcTradingAccount();

    /**
     * For EventFactory Call
     */
    private FtdcRspEvent() {
    }

}
