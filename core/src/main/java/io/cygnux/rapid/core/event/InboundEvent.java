package io.cygnux.rapid.core.event;

import com.lmax.disruptor.EventFactory;
import io.mercury.common.epoch.EpochUnit;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.mercury.common.serialization.specific.JsonSerializable;
import io.mercury.serialization.json.JsonObjectExt;
import io.cygnux.rapid.core.event.inbound.AdaptorReport;
import io.cygnux.rapid.core.event.inbound.BalanceReport;
import io.cygnux.rapid.core.event.inbound.DepthMarketDataReport;
import io.cygnux.rapid.core.event.inbound.InstrumentStatusReport;
import io.cygnux.rapid.core.event.inbound.OrderReport;
import io.cygnux.rapid.core.event.inbound.PositionsReport;
import io.cygnux.rapid.core.event.inbound.MarketDataReport;
import lombok.Getter;
import org.slf4j.Logger;

import javax.annotation.Nonnull;
import java.util.concurrent.atomic.AtomicBoolean;

import static io.mercury.common.epoch.HighResolutionEpoch.micros;

public final class InboundEvent implements JsonSerializable {

    private static final Logger log = Log4j2LoggerFactory.getLogger(InboundEvent.class);

    public static final EventFactory<InboundEvent> EVENT_FACTORY = InboundEvent::new;

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
    private InboundEventType type = InboundEventType.INVALID;

    /// EVENT INSTANCE ///
    @Getter
    private final MarketDataReport marketDataReport = new MarketDataReport();
    @Getter
    private final DepthMarketDataReport depthMarketDataReport = new DepthMarketDataReport();
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
    /// EVENT INSTANCE ///

    /**
     * For EventFactory Call
     */
    InboundEvent() {
    }

    /**
     * @param event FastMarketDataEvent
     * @return InboundEvent
     */
    public InboundEvent updateWith(MarketDataReport event) {
        this.epochMicros = micros();
        this.type = InboundEventType.RAW_MARKET_DATA;
        this.marketDataReport.copyOf(event);
        return this;
    }

    /**
     * @param event DepthMarketDataEvent
     * @return InboundEvent
     */
    public InboundEvent updateWith(DepthMarketDataReport event) {
        this.epochMicros = micros();
        this.type = InboundEventType.DEPTH_MARKET_DATA;
        this.depthMarketDataReport.copyOf(event);
        return this;
    }

    /**
     * @param event OrderEvent
     * @return InboundEvent
     */
    public InboundEvent updateWith(OrderReport event) {
        this.epochMicros = micros();
        this.type = InboundEventType.ORDER_REPORT;
        this.orderReport.copyOf(event);
        return this;
    }

    /**
     * @param event PositionsEvent
     * @return InboundEvent
     */
    public InboundEvent updateWith(PositionsReport event) {
        this.epochMicros = micros();
        this.type = InboundEventType.POSITIONS_REPORT;
        this.positionsReport.copyOf(event);
        return this;
    }

    /**
     * @param event BalanceEvent
     * @return InboundEvent
     */
    public InboundEvent updateWith(BalanceReport event) {
        this.epochMicros = micros();
        this.type = InboundEventType.BALANCE_REPORT;
        this.balanceReport.copyOf(event);
        return this;
    }

    /**
     * @param event AdaptorEvent
     * @return InboundEvent
     */
    public InboundEvent updateWith(AdaptorReport event) {
        this.epochMicros = micros();
        this.type = InboundEventType.ADAPTOR_STATUS_REPORT;
        this.adaptorReport.copyOf(event);
        return this;
    }

    /**
     * @param event MarketDataSubscribeEvent
     * @return InboundEvent
     */
    public InboundEvent updateWith(InstrumentStatusReport event) {
        this.epochMicros = micros();
        this.type = InboundEventType.INSTRUMENT_STATUS_REPORT;
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
                    case RAW_MARKET_DATA -> marketDataReport;
                    case DEPTH_MARKET_DATA -> depthMarketDataReport;
                    case ORDER_REPORT -> orderReport;
                    case POSITIONS_REPORT -> positionsReport;
                    case BALANCE_REPORT -> balanceReport;
                    case ADAPTOR_STATUS_REPORT -> adaptorReport;
                    case INSTRUMENT_STATUS_REPORT -> instrumentStatusReport;
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
    public InboundEvent logging() {
        if (isLogging.get())
            log.info("InboundEvent logging -> {}", this);
        return this;
    }

}
