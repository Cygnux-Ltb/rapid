package io.cygnux.rapid.core.stream;

import com.lmax.disruptor.EventFactory;
import io.cygnux.rapid.core.stream.event.AdaptorReport;
import io.cygnux.rapid.core.stream.event.BalanceReport;
import io.cygnux.rapid.core.stream.event.DepthMarketData;
import io.cygnux.rapid.core.stream.event.FastMarketData;
import io.cygnux.rapid.core.stream.event.InstrumentStatusReport;
import io.cygnux.rapid.core.stream.event.OrderReport;
import io.cygnux.rapid.core.stream.event.PositionsReport;
import io.cygnux.rapid.core.stream.event.StrategySignal;
import io.mercury.common.epoch.EpochUnit;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.mercury.common.serialization.specific.JsonSerializable;
import io.mercury.serialization.json.JsonObjectExt;
import lombok.Getter;
import org.slf4j.Logger;

import javax.annotation.Nonnull;
import java.util.concurrent.atomic.AtomicBoolean;

import static io.mercury.common.epoch.HighResolutionEpoch.micros;

public final class StreamEvent implements JsonSerializable {

    private static final Logger log = Log4j2LoggerFactory.getLogger(StreamEvent.class);

    public static final EventFactory<StreamEvent> EVENT_FACTORY = StreamEvent::new;

    private static final AtomicBoolean isLogging = new AtomicBoolean(false);


    /**
     * 微秒时间戳
     */
    @Getter
    private long epochMicros;

    /**
     * 事件类型
     */
    @Getter
    private StreamEventType type = StreamEventType.INVALID;

    /// EVENT INSTANCE ///
    @Getter
    private final FastMarketData fastMarketData = new FastMarketData();
    @Getter
    private final DepthMarketData depthMarketData = new DepthMarketData();
    @Getter
    private final OrderReport orderReport = new OrderReport();
    @Getter
    private final PositionsReport positionsReport = new PositionsReport();
    @Getter
    private final BalanceReport balanceReport = new BalanceReport();
    @Getter
    private final AdaptorReport adaptorReport = new AdaptorReport();
    @Getter
    private final InstrumentStatusReport instrumentStatusReport = new InstrumentStatusReport();
    @Getter
    private final StrategySignal strategySignal = new StrategySignal();
    /// EVENT INSTANCE ///

    /**
     * For EventFactory Call
     */
    StreamEvent() {
    }

    /**
     * @param event FastMarketDataEvent
     * @return InboundEvent
     */
    public StreamEvent updateWith(FastMarketData event) {
        this.epochMicros = micros();
        this.type = StreamEventType.FAST_MARKET_DATA;
        this.fastMarketData.copyOf(event);
        return this;
    }

    /**
     * @param event DepthMarketDataEvent
     * @return InboundEvent
     */
    public StreamEvent updateWith(DepthMarketData event) {
        this.epochMicros = micros();
        this.type = StreamEventType.DEPTH_MARKET_DATA;
        this.depthMarketData.copyOf(event);
        return this;
    }

    /**
     * @param event OrderEvent
     * @return InboundEvent
     */
    public StreamEvent updateWith(OrderReport event) {
        this.epochMicros = micros();
        this.type = StreamEventType.ORDER_REPORT;
        this.orderReport.copyOf(event);
        return this;
    }

    /**
     * @param event PositionsEvent
     * @return InboundEvent
     */
    public StreamEvent updateWith(PositionsReport event) {
        this.epochMicros = micros();
        this.type = StreamEventType.POSITIONS_REPORT;
        this.positionsReport.copyOf(event);
        return this;
    }

    /**
     * @param event BalanceEvent
     * @return InboundEvent
     */
    public StreamEvent updateWith(BalanceReport event) {
        this.epochMicros = micros();
        this.type = StreamEventType.BALANCE_REPORT;
        this.balanceReport.copyOf(event);
        return this;
    }

    /**
     * @param event AdaptorEvent
     * @return InboundEvent
     */
    public StreamEvent updateWith(AdaptorReport event) {
        this.epochMicros = micros();
        this.type = StreamEventType.ADAPTOR_STATUS_REPORT;
        this.adaptorReport.copyOf(event);
        return this;
    }

    /**
     * @param event MarketDataSubscribeEvent
     * @return InboundEvent
     */
    public StreamEvent updateWith(InstrumentStatusReport event) {
        this.epochMicros = micros();
        this.type = StreamEventType.INSTRUMENT_STATUS_REPORT;
        this.instrumentStatusReport.copyOf(event);
        return this;
    }

    @Override
    public String toString() {
        return toJson();
    }

    /**
     * @return JsonRecord
     */
    public JsonObjectExt toJsonRecord() {
        return new JsonObjectExt()
                .setTitle(type.name())
                .setEpochUnit(EpochUnit.MICROS)
                .setEpochTime(epochMicros)
                .setObject(switch (type) {
                    case FAST_MARKET_DATA -> fastMarketData;
                    case DEPTH_MARKET_DATA -> depthMarketData;
                    case ORDER_REPORT -> orderReport;
                    case POSITIONS_REPORT -> positionsReport;
                    case BALANCE_REPORT -> balanceReport;
                    case ADAPTOR_STATUS_REPORT -> adaptorReport;
                    case INSTRUMENT_STATUS_REPORT -> instrumentStatusReport;
                    case STRATEGY_SIGNAL -> strategySignal;
                    case INVALID -> null;
                });
    }


    @Nonnull
    @Override
    public String toJson() {
        return toJsonRecord().toJson();
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

    /**
     * 输出日志
     *
     * @return InboundEvent
     */
    public StreamEvent logging() {
        if (isLogging.get())
            log.info("InboundEvent logging -> {}", this);
        return this;
    }

}
