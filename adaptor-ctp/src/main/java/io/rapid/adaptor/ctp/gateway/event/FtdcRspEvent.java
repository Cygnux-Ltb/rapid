package io.rapid.adaptor.ctp.gateway.event;

import com.lmax.disruptor.EventFactory;
import io.mercury.common.epoch.EpochUnit;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.mercury.common.serialization.specific.JsonSerializable;
import io.mercury.serialization.json.JsonRecord;
import io.rapid.adaptor.ctp.serializable.FtdcRspType;
import io.rapid.adaptor.ctp.serializable.md.FtdcDepthMarketData;
import io.rapid.adaptor.ctp.serializable.md.FtdcSpecificInstrument;
import io.rapid.adaptor.ctp.serializable.shared.FrontDisconnected;
import io.rapid.adaptor.ctp.serializable.shared.HeartBeatWarning;
import io.rapid.adaptor.ctp.serializable.shared.RspError;
import io.rapid.adaptor.ctp.serializable.shared.RspUserLogin;
import io.rapid.adaptor.ctp.serializable.shared.UserLogout;
import io.rapid.adaptor.ctp.serializable.trader.FtdcInputOrder;
import io.rapid.adaptor.ctp.serializable.trader.FtdcInputOrderAction;
import io.rapid.adaptor.ctp.serializable.trader.FtdcInstrumentStatus;
import io.rapid.adaptor.ctp.serializable.trader.FtdcInvestorPosition;
import io.rapid.adaptor.ctp.serializable.trader.FtdcOrder;
import io.rapid.adaptor.ctp.serializable.trader.FtdcOrderAction;
import io.rapid.adaptor.ctp.serializable.trader.FtdcTrade;
import io.rapid.adaptor.ctp.serializable.trader.FtdcTradingAccount;
import jakarta.annotation.Nonnull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.slf4j.Logger;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 事件循环使用Event类型
 *
 * @author yellow013
 */
@Getter
public final class FtdcRspEvent implements JsonSerializable {

    private static final Logger log = Log4j2LoggerFactory.getLogger(FtdcRspEvent.class);

    public static final EventFactory<FtdcRspEvent> EVENT_FACTORY = FtdcRspEvent::new;

    private static final AtomicBoolean isLogging = new AtomicBoolean(false);

    /**
     * 事件类型
     */
    @Setter
    @Accessors(chain = true)
    private FtdcRspType type = FtdcRspType.Unsupported;

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
    private FtdcRspEvent() {
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
                    case FtdcDepthMarketData -> ftdcDepthMarketData;
                    case FtdcOrder -> ftdcOrder;
                    case FtdcTrade -> ftdcTrade;
                    case FtdcInputOrder -> ftdcInputOrder;
                    case FtdcInputOrderAction -> ftdcInputOrderAction;
                    case FtdcOrderAction -> ftdcOrderAction;
                    case HeartBeatWarning -> heartBeatWarning;
                    case FtdcInstrumentStatus -> ftdcInstrumentStatus;
                    case FtdcSpecificInstrument -> ftdcSpecificInstrument;
                    case FtdcInvestorPosition -> ftdcInvestorPosition;
                    case FtdcTradingAccount -> ftdcTradingAccount;
                    case RspError -> rspError;
                    case Unsupported -> "Unsupported";
                    case FrontDisconnected -> frontDisconnected;
                    case RspUserLogin -> rspUserLogin;
                    case UserLogout -> userLogout;
                    case MdClosed -> "MdClosed";
                    case TraderClosed -> "TraderClosed";
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
