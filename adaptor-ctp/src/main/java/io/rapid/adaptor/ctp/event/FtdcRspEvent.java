package io.rapid.adaptor.ctp.event;

import io.mercury.common.epoch.EpochUnit;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.mercury.common.serialization.specific.JsonSerializable;
import io.mercury.serialization.json.JsonObjectExt;
import io.rapid.adaptor.ctp.event.md.FtdcDepthMarketData;
import io.rapid.adaptor.ctp.event.md.FtdcSpecificInstrument;
import io.rapid.adaptor.ctp.event.shared.FrontDisconnected;
import io.rapid.adaptor.ctp.event.shared.HeartBeatWarning;
import io.rapid.adaptor.ctp.event.shared.RspError;
import io.rapid.adaptor.ctp.event.shared.RspUserLogin;
import io.rapid.adaptor.ctp.event.shared.UserLogout;
import io.rapid.adaptor.ctp.event.trader.FtdcInputOrder;
import io.rapid.adaptor.ctp.event.trader.FtdcInputOrderAction;
import io.rapid.adaptor.ctp.event.trader.FtdcInstrumentStatus;
import io.rapid.adaptor.ctp.event.trader.FtdcInvestorPosition;
import io.rapid.adaptor.ctp.event.trader.FtdcOrder;
import io.rapid.adaptor.ctp.event.trader.FtdcOrderAction;
import io.rapid.adaptor.ctp.event.trader.FtdcTrade;
import io.rapid.adaptor.ctp.event.trader.FtdcTradingAccount;
import jakarta.annotation.Nonnull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.slf4j.Logger;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 环形队列使用[FtdcEvent]类型
 *
 * @author yellow013
 */
@Getter
public final class FtdcRspEvent implements JsonSerializable {

    private static final Logger log = Log4j2LoggerFactory.getLogger(FtdcRspEvent.class);

    private static final AtomicBoolean isLogging = new AtomicBoolean(false);

    /**
     * 事件类型
     */
    @Setter
    @Accessors(chain = true)
    private FtdcRspType type = FtdcRspType.UNSUPPORTED;

    /**
     * 微秒时间戳
     */
    @Setter
    @Accessors(chain = true)
    private long epochMicros;

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
    private final FtdcDepthMarketData depthMarketData = new FtdcDepthMarketData();
    /**
     * 指定的合约
     */
    private final FtdcSpecificInstrument specificInstrument = new FtdcSpecificInstrument();

    //////////////////////////////////// TRADER RSP ////////////////////////////////////
    /**
     * 返回报单错误
     */
    private final FtdcInputOrder inputOrder = new FtdcInputOrder();

    /**
     * 返回撤单提交错误
     */
    private final FtdcInputOrderAction inputOrderAction = new FtdcInputOrderAction();

    /**
     * 合约状态
     */
    private final FtdcInstrumentStatus instrumentStatus = new FtdcInstrumentStatus();

    /**
     * 持仓信息
     */
    private final FtdcInvestorPosition investorPosition = new FtdcInvestorPosition();

    /**
     * 报单推送
     */
    private final FtdcOrder order = new FtdcOrder();

    /**
     * 返回撤单错误
     */
    private final FtdcOrderAction orderAction = new FtdcOrderAction();

    /**
     * 成交推送
     */
    private final FtdcTrade trade = new FtdcTrade();

    /**
     * 账户信息(余额)
     */
    private final FtdcTradingAccount tradingAccount = new FtdcTradingAccount();

    /**
     * For EventFactory Call
     */
    FtdcRspEvent() {
    }

    @Override
    public String toString() {
        return toJson();
    }

    public JsonObjectExt toJsonObjectExt() {
        return setValue(new JsonObjectExt().setEpochUnit(EpochUnit.MICROS));
    }

    // 复用Record
    private final JsonObjectExt jsonObjectExt = new JsonObjectExt().setEpochUnit(EpochUnit.MICROS);

    @Nonnull
    @Override
    public String toJson() {
        return setValue(jsonObjectExt).toJson();
    }

    private JsonObjectExt setValue(JsonObjectExt objectExt) {
        return objectExt.setTitle(type.name())
                .setEpochTime(epochMicros)
                .setObject(switch (type) {
                    case FTDC_DEPTH_MARKET_DATA -> depthMarketData;
                    case FTDC_ORDER -> order;
                    case FTDC_TRADE -> trade;
                    case FTDC_INPUT_ORDER -> inputOrder;
                    case FTDC_INPUT_ORDER_ACTION -> inputOrderAction;
                    case FTDC_ORDER_ACTION -> orderAction;
                    case HEARTBEAT_WARNING -> heartBeatWarning;
                    case FTDC_INSTRUMENT_STATUS -> instrumentStatus;
                    case FTDC_SPECIFIC_INSTRUMENT -> specificInstrument;
                    case FTDC_INVESTOR_POSITION -> investorPosition;
                    case FTDC_TRADING_ACCOUNT -> tradingAccount;
                    case RSP_ERROR -> rspError;
                    case FRONT_DISCONNECTED -> frontDisconnected;
                    case RSP_USER_LOGIN -> rspUserLogin;
                    case USER_LOGOUT -> userLogout;
                    case UNSUPPORTED -> null;
                });
    }

    /**
     * 启用日志记录
     */
    public static void enableLogging() {
        isLogging.set(true);
    }

    /**
     * 禁用日志记录
     */
    public static void disableLogging() {
        isLogging.set(false);
    }

    public FtdcRspEvent logging() {
        if (isLogging.get())
            log.info("FtdcRspEvent logging -> {}", this);
        return this;
    }

}
