package io.rapid.adaptor.ctp;

import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.rapid.adaptor.ctp.serializable.avro.md.FtdcDepthMarketData;
import io.rapid.core.mkd.FastMarketData;
import org.slf4j.Logger;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static io.mercury.common.datetime.TimeConst.NANOS_PER_MILLIS;
import static io.mercury.common.datetime.pattern.impl.DatePattern.YYYYMMDD;
import static io.mercury.common.datetime.pattern.impl.TimePattern.HH_MM_SS;
import static io.rapid.core.instrument.InstrumentKeeper.getInstrument;

/**
 * FtdcDepthMarketData -> FastMarketData
 *
 * @author yellow013
 */
public final class MarketDataConverter {

    private static final Logger log = Log4j2LoggerFactory.getLogger(MarketDataConverter.class);

    private final DateTimeFormatter updateTimeFormatter = HH_MM_SS.newFormatter();

    private final DateTimeFormatter actionDayFormatter = YYYYMMDD.newFormatter();

    /**
     * 转换
     *
     * @param marketData FtdcDepthMarketData
     * @return FastMarketData
     */
    public FastMarketData withFtdcDepthMarketData(FtdcDepthMarketData marketData) {
        // 业务日期
        var actionDay = LocalDate.parse(marketData.getActionDay(), actionDayFormatter);
        // 最后修改时间
        var updateTime = LocalTime.parse(marketData.getUpdateTime(), updateTimeFormatter)
                .plusNanos(marketData.getUpdateMillisec() * NANOS_PER_MILLIS);
        // 最后修改时间
        var instrument = getInstrument(marketData.getInstrumentID());

        log.info("Convert FtdcDepthMarketData apply -> InstrumentCode==[{}], actionDay==[{}], updateTime==[{}]",
                instrument.getInstrumentCode(), actionDay, updateTime);
        var multiplier = instrument.getSymbol().getMultiplier();
        return new FastMarketData().setDepth(5)
                // 交易标的
                .setInstrumentCode(instrument.getInstrumentCode())
                .setActionDay(marketData.getActionDay())
                // 业务日期, 最后修改时间, 最后修改时间毫秒数
                .setUpdateTime(marketData.getUpdateTime())
                .setUpdateMillisec(marketData.getUpdateMillisec())
                // 最新价, 成交量, 成交额
                .setLastPrice(multiplier.toLong(marketData.getLastPrice()))
                .setVolume(marketData.getVolume())
                .setTurnover(multiplier.toLong(marketData.getTurnover()))
                // 五档买入价格和成交量
                .setBidPrice1(multiplier.toLong(marketData.getBidPrice1()))
                .setBidVolume1(marketData.getBidVolume1())
                .setBidPrice2(multiplier.toLong(marketData.getBidPrice2()))
                .setBidVolume2(marketData.getBidVolume2())
                .setBidPrice3(multiplier.toLong(marketData.getBidPrice3()))
                .setBidVolume3(marketData.getBidVolume3())
                .setBidPrice4(multiplier.toLong(marketData.getBidPrice4()))
                .setBidVolume4(marketData.getBidVolume4())
                .setBidPrice5(multiplier.toLong(marketData.getBidPrice5()))
                .setBidVolume5(marketData.getBidVolume5())
                // 五档卖出价格和成交量
                .setAskPrice1(multiplier.toLong(marketData.getAskPrice1()))
                .setAskVolume1(marketData.getAskVolume1())
                .setAskPrice2(multiplier.toLong(marketData.getAskPrice2()))
                .setAskVolume2(marketData.getAskVolume2())
                .setAskPrice3(multiplier.toLong(marketData.getAskPrice3()))
                .setAskVolume3(marketData.getAskVolume3())
                .setAskPrice4(multiplier.toLong(marketData.getAskPrice4()))
                .setAskVolume4(marketData.getAskVolume4())
                .setAskPrice5(multiplier.toLong(marketData.getAskPrice5()))
                .setAskVolume5(marketData.getAskVolume5());
    }

}
