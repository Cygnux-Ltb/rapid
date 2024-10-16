package io.rapid.core.event;

import com.lmax.disruptor.EventFactory;
import io.mercury.common.epoch.EpochUnit;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.mercury.common.serialization.specific.JsonSerializable;
import io.mercury.serialization.json.JsonRecord;
import io.rapid.core.event.inbound.AdaptorReport;
import io.rapid.core.event.inbound.BalanceReport;
import io.rapid.core.event.inbound.DepthMarketData;
import io.rapid.core.event.inbound.RawMarketData;
import io.rapid.core.event.inbound.MarketDataSubscribeReport;
import io.rapid.core.event.inbound.OrderReport;
import io.rapid.core.event.inbound.PositionsReport;
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
    private InboundEventType type = InboundEventType.Invalid;

    /// EVENT INSTANCE ///
    @Getter
    private final RawMarketData rawMarketData = new RawMarketData();
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
    private final MarketDataSubscribeReport marketDataSubscribeReport = new MarketDataSubscribeReport();
    /// EVENT INSTANCE ///

    /**
     * For EventFactory Call
     */
    private InboundEvent() {
    }

    /**
     * @param event FastMarketDataEvent
     * @return InboundEvent
     */
    public InboundEvent updateWith(RawMarketData event) {
        this.epochMicros = micros();
        this.type = InboundEventType.RawMarketData;
        this.rawMarketData.copyFrom(event);
        return this;
    }

    /**
     * @param event DepthMarketDataEvent
     * @return InboundEvent
     */
    public InboundEvent updateWith(DepthMarketData event) {
        this.epochMicros = micros();
        this.type = InboundEventType.DepthMarketData;
        this.depthMarketData.copyFrom(event);
        return this;
    }

    /**
     * @param event OrderEvent
     * @return InboundEvent
     */
    public InboundEvent updateWith(OrderReport event) {
        this.epochMicros = micros();
        this.type = InboundEventType.OrderReport;
        this.orderReport.copyFrom(event);
        return this;
    }

    /**
     * @param event PositionsEvent
     * @return InboundEvent
     */
    public InboundEvent updateWith(PositionsReport event) {
        this.epochMicros = micros();
        this.type = InboundEventType.PositionsReport;
        this.positionsReport.copyFrom(event);
        return this;
    }

    /**
     * @param event BalanceEvent
     * @return InboundEvent
     */
    public InboundEvent updateWith(BalanceReport event) {
        this.epochMicros = micros();
        this.type = InboundEventType.BalanceReport;
        this.balanceReport.copyFrom(event);
        return this;
    }

    /**
     * @param event AdaptorEvent
     * @return InboundEvent
     */
    public InboundEvent updateWith(AdaptorReport event) {
        this.epochMicros = micros();
        this.type = InboundEventType.AdaptorReport;
        this.adaptorReport.copyFrom(event);
        return this;
    }

    /**
     * @param event MarketDataSubscribeEvent
     * @return InboundEvent
     */
    public InboundEvent updateWith(MarketDataSubscribeReport event) {
        this.epochMicros = micros();
        this.type = InboundEventType.MarketDataSubscribeReport;
        this.marketDataSubscribeReport.copyFrom(event);
        return this;
    }

    @Override
    public String toString() {
        return toJson();
    }

    private final JsonRecord record = new JsonRecord().setEpochUnit(EpochUnit.MICROS);

    @Nonnull
    @Override
    public String toJson() {
        return record.setTitle(type.name())
                .setEpochTime(epochMicros)
                .setRecord(switch (type) {
                    case RawMarketData -> rawMarketData;
                    case DepthMarketData -> depthMarketData;
                    case OrderReport -> orderReport;
                    case PositionsReport -> positionsReport;
                    case BalanceReport -> balanceReport;
                    case AdaptorReport -> adaptorReport;
                    case MarketDataSubscribeReport -> marketDataSubscribeReport;
                    case Invalid -> "Invalid";
                }).toJson();
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

    public InboundEvent logging() {
        if (isLogging.get())
            log.info("InboundEvent logging -> {}", this);
        return this;
    }

}
