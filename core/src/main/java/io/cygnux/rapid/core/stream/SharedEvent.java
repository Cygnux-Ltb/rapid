package io.cygnux.rapid.core.stream;

import com.lmax.disruptor.EventFactory;
import io.cygnux.rapid.core.strategy.StrategySlotCounter;
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
import javax.annotation.concurrent.NotThreadSafe;
import java.util.concurrent.atomic.AtomicBoolean;

import static io.mercury.common.epoch.HighResolutionEpoch.micros;

/**
 * 核心共享事件
 */
@NotThreadSafe
public final class SharedEvent implements JsonSerializable {

    private static final Logger log = Log4j2LoggerFactory.getLogger(SharedEvent.class);

    public static final EventFactory<SharedEvent> EVENT_FACTORY = SharedEvent::new;

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
    private SharedEventType type = SharedEventType.INVALID;

    /// EVENT INSTANCE ///
    @Getter
    private final FastMarketData fastMarketData = new FastMarketData();

    @Getter
    private final DepthMarketData depthMarketData = new DepthMarketData();

    @Getter
    private final InstrumentStatusReport instrumentStatusReport = new InstrumentStatusReport();

    @Getter
    private final AdaptorReport adaptorReport = new AdaptorReport();

    @Getter
    private final PositionsReport positionsReport = new PositionsReport();

    @Getter
    private final BalanceReport balanceReport = new BalanceReport();

    @Getter
    private final OrderReport orderReport = new OrderReport();

    @Getter
    private final StrategySignal[] strategySignalSlot;
    /// EVENT INSTANCE ///

    /**
     * For EventFactory Call
     */
    private SharedEvent() {
        this.strategySignalSlot = new StrategySignal[StrategySlotCounter.getCurrentValue()];
    }

    /**
     * @param event FastMarketData
     * @return SharedEvent
     */
    public SharedEvent updateWith(FastMarketData event) {
        this.epochMicros = micros();
        this.type = SharedEventType.FAST_MARKET_DATA;
        this.fastMarketData.copyOf(event);
        return this;
    }

    /**
     * @param event DepthMarketData
     * @return SharedEvent
     */
    public SharedEvent updateWith(DepthMarketData event) {
        this.epochMicros = micros();
        this.type = SharedEventType.DEPTH_MARKET_DATA;
        this.depthMarketData.copyOf(event);
        return this;
    }

    /**
     * @param event InstrumentStatusReport
     * @return SharedEvent
     */
    public SharedEvent updateWith(InstrumentStatusReport event) {
        this.epochMicros = micros();
        this.type = SharedEventType.INSTRUMENT_STATUS_REPORT;
        this.instrumentStatusReport.copyOf(event);
        return this;
    }

    /**
     * @param event AdaptorReport
     * @return SharedEvent
     */
    public SharedEvent updateWith(AdaptorReport event) {
        this.epochMicros = micros();
        this.type = SharedEventType.ADAPTOR_STATUS_REPORT;
        this.adaptorReport.copyOf(event);
        return this;
    }

    /**
     * @param event PositionsReport
     * @return SharedEvent
     */
    public SharedEvent updateWith(PositionsReport event) {
        this.epochMicros = micros();
        this.type = SharedEventType.POSITIONS_REPORT;
        this.positionsReport.copyOf(event);
        return this;
    }

    /**
     * @param event BalanceReport
     * @return SharedEvent
     */
    public SharedEvent updateWith(BalanceReport event) {
        this.epochMicros = micros();
        this.type = SharedEventType.BALANCE_REPORT;
        this.balanceReport.copyOf(event);
        return this;
    }

    /**
     * @param event OrderReport
     * @return SharedEvent
     */
    public SharedEvent updateWith(OrderReport event) {
        this.epochMicros = micros();
        this.type = SharedEventType.ORDER_REPORT;
        this.orderReport.copyOf(event);
        return this;
    }

    /**
     * @param event OrderReport
     * @return SharedEvent
     */
    public SharedEvent updateWith(int slot, StrategySignal event) {
        this.epochMicros = micros();
        this.type = SharedEventType.STRATEGY_SIGNAL;
        this.strategySignalSlot[slot].copyOf(event);
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
                    case STRATEGY_SIGNAL -> strategySignalSlot;
                    case SKIP -> "SKIP";
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
    public SharedEvent logging() {
        if (isLogging.get())
            log.info("InboundEvent logging -> {}", this);
        return this;
    }

}
