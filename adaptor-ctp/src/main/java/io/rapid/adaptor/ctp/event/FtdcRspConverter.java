package io.rapid.adaptor.ctp.event;

import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.rapid.adaptor.ctp.event.md.FtdcDepthMarketData;
import io.rapid.core.event.inbound.RawMarketData;
import io.rapid.core.instrument.InstrumentKeeper;
import org.slf4j.Logger;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static io.mercury.common.datetime.TimeConst.NANOS_PER_MILLIS;
import static io.mercury.common.datetime.pattern.impl.DatePattern.YYYYMMDD;
import static io.mercury.common.datetime.pattern.impl.TimePattern.HH_MM_SS;

/**
 * FtdcDepthMarketData -> FastMarketData
 *
 * @author yellow013
 */
public final class FtdcRspConverter {

    private static final Logger log = Log4j2LoggerFactory.getLogger(FtdcRspConverter.class);

    private final DateTimeFormatter updateTimeFormatter = HH_MM_SS.newFormatter();

    private final DateTimeFormatter actionDayFormatter = YYYYMMDD.newFormatter();

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
    public RawMarketData withFtdcDepthMarketData(FtdcDepthMarketData marketData) {
        // 业务日期
        var actionDay = LocalDate.parse(marketData.ActionDay, actionDayFormatter);
        // 最后修改时间
        var updateTime = LocalTime.parse(marketData.UpdateTime, updateTimeFormatter)
                .plusNanos(marketData.UpdateMillisec * NANOS_PER_MILLIS);

        var instrument = InstrumentKeeper.getInstrument(marketData.InstrumentID);

        return new RawMarketData()
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
                // .setTradingDay(marketData.TradingDay)
                // .setActualDate(marketData.ActualDate)
                // .setUpdateTime(marketData.UpdateTime)
                .setUpdateMillisec(marketData.UpdateMillisec);
    }

}
