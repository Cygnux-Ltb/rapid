package io.cygnux.rapid.adaptor.ctp;

import io.mercury.common.datetime.DateTimeUtil;
import io.mercury.common.datetime.TimeZone;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.cygnux.rapid.adaptor.ctp.consts.FtdcDirection;
import io.cygnux.rapid.adaptor.ctp.consts.FtdcOffsetFlag;
import io.cygnux.rapid.adaptor.ctp.consts.FtdcOrderStatus;
import io.cygnux.rapid.adaptor.ctp.consts.FtdcPosiDirection;
import io.cygnux.rapid.adaptor.ctp.event.md.FtdcDepthMarketData;
import io.cygnux.rapid.adaptor.ctp.event.md.FtdcSpecificInstrument;
import io.cygnux.rapid.adaptor.ctp.event.trader.FtdcInputOrder;
import io.cygnux.rapid.adaptor.ctp.event.trader.FtdcInputOrderAction;
import io.cygnux.rapid.adaptor.ctp.event.trader.FtdcInstrumentStatus;
import io.cygnux.rapid.adaptor.ctp.event.trader.FtdcInvestorPosition;
import io.cygnux.rapid.adaptor.ctp.event.trader.FtdcOrder;
import io.cygnux.rapid.adaptor.ctp.event.trader.FtdcOrderAction;
import io.cygnux.rapid.adaptor.ctp.event.trader.FtdcTrade;
import io.cygnux.rapid.adaptor.ctp.event.trader.FtdcTradingAccount;
import io.cygnux.rapid.adaptor.ctp.param.FtdcParams;
import io.cygnux.rapid.core.event.enums.OrdStatus;
import io.cygnux.rapid.core.event.enums.SubscribeStatus;
import io.cygnux.rapid.core.event.enums.TradingStatus;
import io.cygnux.rapid.core.event.inbound.BalanceReport;
import io.cygnux.rapid.core.event.inbound.InstrumentStatusReport;
import io.cygnux.rapid.core.event.inbound.OrderReport;
import io.cygnux.rapid.core.event.inbound.PositionsReport;
import io.cygnux.rapid.core.event.inbound.FastMarketData;
import io.cygnux.rapid.core.instrument.InstrumentKeeper;
import io.cygnux.rapid.core.order.OrderRefKeeper;
import org.slf4j.Logger;

import java.time.format.DateTimeFormatter;

import static io.mercury.common.datetime.pattern.impl.DatePattern.YYYYMMDD;
import static io.mercury.common.datetime.pattern.impl.TimePattern.HH_MM_SS;
import static io.mercury.common.datetime.pattern.impl.TimePattern.HH_MM_SS_SSS;
import static io.mercury.common.epoch.HighResolutionEpoch.micros;
import static io.mercury.common.util.CharUtil.decimalCharToInt;
import static io.mercury.common.util.StringSupport.removeNonDigits;
import static io.cygnux.rapid.adaptor.ctp.consts.FtdcInstrumentStatus.getPrompt;
import static io.cygnux.rapid.adaptor.ctp.consts.FtdcInstrumentStatus.getSubscribeStatus;
import static io.cygnux.rapid.adaptor.ctp.consts.FtdcInstrumentStatus.getTradingStatus;
import static io.cygnux.rapid.core.instrument.Exchange.SHFE;
import static java.lang.Integer.parseInt;
import static java.lang.System.currentTimeMillis;

/**
 * FtdcDepthMarketData -> FastMarketData
 *
 * @author yellow013
 */
public final class FtdcRspConverter {

    private static final Logger log = Log4j2LoggerFactory.getLogger(FtdcRspConverter.class);

    private final DateTimeFormatter updateTimeFormatter = HH_MM_SS.newFormatter();

    private final DateTimeFormatter actionDayFormatter = YYYYMMDD.newFormatter();

    private final OrderRefKeeper orderRefKeeper = OrderRefKeeper.DEFAULT;

    // 经纪公司代码
    private final String brokerId;

    // 投资者代码
    private final String investorId;

    private int tradingDay;

    void setTradingDay(String tradingDay) {
        this.tradingDay = Integer.parseInt(tradingDay);
    }

    FtdcRspConverter(FtdcParams params) {
        this.brokerId = params.getBrokerId();
        this.investorId = params.getInvestorId();
        log.info("FtdcRspConverter initialized, brokerId=={}, investorId=={}", brokerId, investorId);
    }

    /**
     * [RawMarketData]复用对象
     */
    private final FastMarketData fastMarketData = new FastMarketData();

    /**
     * 转换
     * <pre>
     * {
     *     "ActionDay": 20241010,
     *     "AskPrice1": 4012,
     *     "AskVolume1": 4,
     *     "AveragePrice": 1210068.3663638374,
     *     "BandingLowerPrice": 0,
     *     "BandingUpperPrice": 0,
     *     "BidPrice1": 4010.8,
     *     "BidVolume1": 24,
     *     "HighestPrice": 4125,
     *     "InstrumentID": "IF2410",
     *     "LastPrice": 4010.8,
     *     "LowerLimitPrice": 3617.4,
     *     "LowestPrice": 3937.4,
     *     "OpenInterest": 84972,
     *     "OpenPrice": 4000,
     *     "PreClosePrice": 3934,
     *     "PreOpenInterest": 91658,
     *     "PreSettlementPrice": 4019.2,
     *     "RecvEpochMicros": 1728545065978741,
     *     "TradingDay": 20241010,
     *     "Turnover": 169558409700,
     *     "UpdateMillisec": 500,
     *     "UpdateTime": "15:24:25",
     *     "UpperLimitPrice": 4421,
     *     "Volume": 140123
     * }
     * <pre/>
     * @param marketData FtdcDepthMarketData
     * @return FastMarketData
     */
    public FastMarketData convert(FtdcDepthMarketData marketData) {
        // 业务日期
        // var actionDay = LocalDate.parse(marketData.ActionDay, actionDayFormatter);
        // 最后修改时间
        // var updateTime = LocalTime.parse(marketData.UpdateTime, updateTimeFormatter).plusNanos(marketData.UpdateMillisec * NANOS_PER_MILLIS);
        var instrument = InstrumentKeeper.getInstrumentByCode(marketData.InstrumentID);
        return fastMarketData
                // 交易标的ID
                .setInstrumentId(instrument.getInstrumentId())
                // 交易标的代码
                .setInstrumentCode(instrument.getInstrumentCode())
                // 最新价格
                .setLastPrice(marketData.LastPrice)
                // 成交量
                .setVolume(marketData.Volume)
                // 成交金额
                .setTurnover(marketData.Turnover)
                // 持仓量
                .setOpenInterest(marketData.OpenInterest)
                // 今开盘价
                .setOpenPrice(marketData.OpenPrice)
                // 今最高价
                .setHighestPrice(marketData.HighestPrice)
                // 今最低价
                .setLowestPrice(marketData.LowestPrice)
                // 今涨停板价
                .setUpperLimitPrice(marketData.UpperLimitPrice)
                // 今跌停板价
                .setLowerLimitPrice(marketData.LowerLimitPrice)
                // 买价1
                .setBidPrice1(marketData.BidPrice1)
                // 买量1
                .setBidVolume1(marketData.BidVolume1)
                // 卖价1
                .setAskPrice1(marketData.AskPrice1)
                // 卖量1
                .setAskVolume1(marketData.AskVolume1)
                // 平均价格
                .setAveragePrice(marketData.AveragePrice)
                // 时间坐标相关字段
                .setEpochMicros(marketData.RecvEpochMicros)
                .setTradingDay(tradingDay)
                .setActualDate(DateTimeUtil.date(SHFE.getZoneOffset()))
                .setUpdateTime(decimalCharToInt(marketData.UpdateTime.charAt(0)) * 100000
                        + decimalCharToInt(marketData.UpdateTime.charAt(1)) * 10000
                        + decimalCharToInt(marketData.UpdateTime.charAt(3)) * 1000
                        + decimalCharToInt(marketData.UpdateTime.charAt(4)) * 100
                        + decimalCharToInt(marketData.UpdateTime.charAt(6)) * 10
                        + decimalCharToInt(marketData.UpdateTime.charAt(7)))
                .setUpdateMillisec(marketData.UpdateMillisec);
    }


    public InstrumentStatusReport convert(FtdcSpecificInstrument specificInstrument) {
        var report = new InstrumentStatusReport()
                .setEpochMillis(currentTimeMillis())
                .setActualDate(YYYYMMDD.now(TimeZone.CST))
                .setUpdateTime(HH_MM_SS.now(TimeZone.CST))
                .setSymbolCode(null)
                .setInstrumentCode(specificInstrument.InstrumentID)
                .setTradingStatus(TradingStatus.NOT_PROVIDED)
                .setSubscribeStatus(
                        switch (specificInstrument.Source) {
                            case SUB_MARKET_DATA, SUB_FOR_QUOTE -> SubscribeStatus.SUBSCRIBED;
                            case UNSUB_MARKET_DATA, UNSUB_FOR_QUOTE -> SubscribeStatus.SUBSCRIPTION_FAILED;
                        }
                )
                .setMsg("FtdcSpecificInstrument-" + specificInstrument.Source);
        log.info("FtdcRspConverter received [FtdcSpecificInstrument] convert to [InstrumentStatusReport] -> {}", report);
        return report;
    }

    public InstrumentStatusReport convert(FtdcInstrumentStatus instrumentStatus) {
        var report = new InstrumentStatusReport()
                .setEpochMillis(currentTimeMillis())
                .setActualDate(YYYYMMDD.now(SHFE.getZoneOffset()))
                .setUpdateTime(instrumentStatus.EnterTime)
                .setSymbolCode(instrumentStatus.InstrumentID)
                .setInstrumentCode(null)
                .setTradingStatus(getTradingStatus(instrumentStatus.InstrumentStatus))
                .setSubscribeStatus(getSubscribeStatus(instrumentStatus.InstrumentStatus))
                .setMsg("FtdcInstrumentStatus-" + getPrompt(instrumentStatus.InstrumentStatus));
        log.info("FtdcRspConverter received [FtdcInstrumentStatus] convert to [InstrumentStatusReport] -> {}", report);
        return report;
    }

    private void updateOrderBasicAttributes(OrderReport order, String orderRef, String brokerOrdSysId,
                                            String updateTime, String exchangeId, String instrumentId) {
        long ordSysId = orderRefKeeper.getOrdSysId(orderRef);
        order // 报单引用, OrdSysId
                .setOrderRef(orderRef).setOrdSysId(ordSysId)
                //报单编号,
                .setBrokerOrdSysId(brokerOrdSysId).setUpdateTime(updateTime)
                // 经纪商ID, 投资者ID
                .setBrokerId(brokerId).setInvestorId(investorId)
                // 交易日, 微秒时间戳
                .setTradingDay(tradingDay).setEpochMicros(micros())
                // 交易所, 合约代码
                .setExchangeCode(exchangeId).setInstrumentCode(instrumentId);
    }

    /**
     * [OrderReport]复用对象
     */
    private final OrderReport fromInputOrder = new OrderReport();

    /**
     * 报单错误消息转换 <br>
     * FtdcInputOrder -> OrderReport
     *
     * @param field FtdcInputOrder
     * @return OrderReport
     */
    public OrderReport convert(FtdcInputOrder field) {
        updateOrderBasicAttributes(fromInputOrder, field.OrderRef, "",
                HH_MM_SS_SSS.now(SHFE.getZoneOffset()), field.ExchangeID, field.InstrumentID);
        fromInputOrder
                // 报单状态
                .setStatus(OrdStatus.NEW_REJECTED)
                // 买卖方向, 组合开平标志
                .setDirection(FtdcDirection.getTrdDirection(field.Direction))
                .setAction(FtdcOffsetFlag.getTrdAction(field.CombOffsetFlag))
                // 委托数量, 委托价格
                .setOfferQty(field.VolumeTotalOriginal).setOfferPrice(field.LimitPrice);
        log.info("FtdcRspConverter received [FtdcInputOrder] convert to [OrderReport] -> {}", fromInputOrder);
        return fromInputOrder;
    }

    /**
     * [OrderReport]复用对象
     */
    private final OrderReport fromOrder = new OrderReport();

    /**
     * 订单回报消息转换 <br>
     * FtdcOrder -> OrderReport
     *
     * @param field FtdcOrder
     * @return OrderReport
     */
    public OrderReport convert(FtdcOrder field) {
        updateOrderBasicAttributes(fromOrder, field.OrderRef, field.OrderSysID,
                field.UpdateTime, field.ExchangeID, field.InstrumentID);
        fromOrder
                // 交易日
                .setTradingDay(parseInt(field.TradingDay))
                // 报单状态
                .setStatus(FtdcOrderStatus.getOrdStatus(field.OrderStatus))
                // 买卖方向, 组合开平标志
                .setDirection(FtdcDirection.getTrdDirection(field.Direction))
                .setAction(FtdcOffsetFlag.getTrdAction(field.CombOffsetFlag))
                // 委托数量
                .setOfferQty(field.VolumeTotalOriginal)
                // 完成数量
                .setFilledQty(field.VolumeTraded)
                // 委托价格
                .setOfferPrice(field.LimitPrice)
                // 报单日期 + 委托时间
                .setOfferTime(removeNonDigits(field.InsertDate) + removeNonDigits(field.InsertTime))
                // 更新时间
                .setUpdateTime(field.UpdateTime);
        log.info("FtdcRspConverter received [FtdcOrder] convert to [OrderReport] -> {}", fromOrder);
        return fromOrder;
    }

    /**
     * [OrderReport]复用对象
     */
    private final OrderReport fromTrade = new OrderReport();

    /**
     * 成交回报消息转换 <br>
     * FtdcTrade -> OrderReport
     *
     * @param field FtdcTrade
     * @return OrderReport
     */
    public OrderReport convert(FtdcTrade field) {
        updateOrderBasicAttributes(fromTrade, field.OrderRef, field.OrderSysID,
                field.TradeTime, field.ExchangeID, field.InstrumentID);
        fromTrade
                // 交易日
                .setTradingDay(parseInt(field.TradingDay))
                // 报单状态
                .setStatus(OrdStatus.UNPROVIDED)
                // 买卖方向, 组合开平标志
                .setDirection(FtdcDirection.getTrdDirection(field.Direction))
                .setAction(FtdcOffsetFlag.getTrdAction(field.OffsetFlag))
                // 完成数量, 成交价格
                .setFilledQty(field.Volume).setTradePrice(field.Price)
                // 最后修改时间
                .setUpdateTime(removeNonDigits(field.TradeDate) + removeNonDigits(field.TradeTime));
        log.info("FtdcRspConverter received [FtdcTrade] convert to [OrderReport] -> {}", fromTrade);
        return fromTrade;
    }

    /**
     * [OrderReport]复用对象
     */
    private final OrderReport fromInputOrderAction = new OrderReport();

    /**
     * 撤单错误回报消息转换1 <br>
     * <br>
     * FtdcInputOrderAction -> OrderReport
     *
     * @param field FtdcInputOrderAction
     * @return OrderReport
     */
    public OrderReport convert(FtdcInputOrderAction field) {
        updateOrderBasicAttributes(fromInputOrderAction, field.OrderRef, field.OrderSysID,
                HH_MM_SS_SSS.now(SHFE.getZoneOffset()), field.ExchangeID, field.InstrumentID);
        fromInputOrderAction.setStatus(OrdStatus.CANCEL_REJECTED);
        log.warn("FtdcRspConverter received [FtdcInputOrderAction] convert to [OrderReport] -> {}", fromInputOrderAction);
        return fromInputOrderAction;
    }

    /**
     * [OrderReport]复用对象
     */
    private final OrderReport fromOrderAction = new OrderReport();

    /**
     * 撤单错误回报消息转换2<br>
     * FtdcOrderAction -> OrderReport
     *
     * @param field FtdcOrderAction
     * @return OrderReport
     */
    public OrderReport convert(FtdcOrderAction field) {
        updateOrderBasicAttributes(fromOrderAction, field.OrderRef, field.OrderSysID,
                field.ActionTime, field.ExchangeID, field.InstrumentID);
        log.warn("FtdcRspConverter received [FtdcOrderAction] convert to OrderReport -> {}", fromOrderAction);
        return fromOrderAction;
    }

    /**
     * [PositionsReport]复用对象
     */
    private final PositionsReport positionsReport = new PositionsReport();

    /**
     * 转换[FtdcInvestorPosition]到[PositionsReport]
     *
     * @param investorPosition FtdcInvestorPosition
     * @return PositionsReport
     */
    public PositionsReport convert(FtdcInvestorPosition investorPosition) {
        positionsReport
                .setEpochMillis(currentTimeMillis())
                .setBrokerId(brokerId)
                .setInvestorId(investorId)
                .setExchangeCode(investorPosition.ExchangeID)
                .setInstrumentCode(investorPosition.InstrumentID)
                .setDirection(FtdcPosiDirection.getTrdDirection(investorPosition.PosiDirection))
                .setYesterdayQty(investorPosition.YdPosition)
                .setTodayQty(investorPosition.TodayPosition)
                .setQty(investorPosition.Position)
                .setMsg(investorPosition.ErrorMsg);
        log.info("FtdcRspConverter received [FtdcInvestorPosition] convert to [PositionsReport] -> {}", positionsReport);
        return positionsReport;
    }

    /**
     * [BalanceReport]复用对象
     */
    private final BalanceReport balanceReport = new BalanceReport();

    /**
     * @param tradingAccount FtdcTradingAccount
     * @return BalanceReport
     */
    public BalanceReport convert(FtdcTradingAccount tradingAccount) {
        balanceReport
                .setEpochMillis(currentTimeMillis())
                .setBrokerId(brokerId)
                .setInvestorId(investorId)
                .setCurrencyId(tradingAccount.CurrencyID)
                .setAvailable((long) tradingAccount.Available)
                .setMsg(tradingAccount.ErrorMsg);
        log.info("FtdcRspConverter received [FtdcTradingAccount] convert to [BalanceReport] -> {}", balanceReport);
        return balanceReport;
    }

}
