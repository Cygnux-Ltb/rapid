package io.rapid.adaptor.ctp.event;

import io.mercury.common.epoch.EpochUnit;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.mercury.common.serialization.specific.JsonSerializable;
import io.mercury.serialization.json.JsonRecord;
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
    FtdcRspEvent() {
    }

    @Override
    public String toString() {
        return toJson();
    }

    public JsonRecord toJsonRecord() {
        return setValue(new JsonRecord().setEpochUnit(EpochUnit.MICROS));
    }

    // 复用Record
    private final JsonRecord record = new JsonRecord().setEpochUnit(EpochUnit.MICROS);

    @Nonnull
    @Override
    public String toJson() {
        return setValue(record).toJson();
    }

    private JsonRecord setValue(JsonRecord record) {
        return record.setTitle(type.name())
                .setEpochTime(epochMicros)
                .setRecord(switch (type) {
                    case FTDC_DEPTH_MARKET_DATA -> ftdcDepthMarketData;
                    case FTDC_ORDER -> ftdcOrder;
                    case FTDC_TRADE -> ftdcTrade;
                    case FTDC_INPUT_ORDER -> ftdcInputOrder;
                    case FTDC_INPUT_ORDER_ACTION -> ftdcInputOrderAction;
                    case FTDC_ORDER_ACTION -> ftdcOrderAction;
                    case HEARTBEAT_WARNING -> heartBeatWarning;
                    case FTDC_INSTRUMENT_STATUS -> ftdcInstrumentStatus;
                    case FTDC_SPECIFIC_INSTRUMENT -> ftdcSpecificInstrument;
                    case FTDC_INVESTOR_POSITION -> ftdcInvestorPosition;
                    case FTDC_TRADING_ACCOUNT -> ftdcTradingAccount;
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
