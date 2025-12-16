package io.cygnux.rapid.core.shared;

import com.lmax.disruptor.EventFactory;
import io.cygnux.rapid.core.shared.event.AdapterReport;
import io.cygnux.rapid.core.shared.event.BalanceReport;
import io.cygnux.rapid.core.shared.event.ControlCommand;
import io.cygnux.rapid.core.shared.event.DepthMarketData;
import io.cygnux.rapid.core.shared.event.FastMarketData;
import io.cygnux.rapid.core.shared.event.InstrumentStatusReport;
import io.cygnux.rapid.core.shared.event.OrderReport;
import io.cygnux.rapid.core.shared.event.PositionsReport;
import io.cygnux.rapid.core.shared.event.StrategySignal;
import io.cygnux.rapid.core.strategy.StrategySlotCounter;
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
 * 核心事件模型
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

    @Getter
    private final Payload payload = new Payload(
            new ControlCommand(),
            new FastMarketData(),
            new DepthMarketData(),
            new InstrumentStatusReport(),
            new AdapterReport(),
            new PositionsReport(),
            new BalanceReport(),
            new OrderReport()
    );

    /**
     * 设置策略信号槽位, 多个策略可同时进行读写, 每个策略只写入自己的槽位
     */
    @Getter
    private final StrategySignal[] strategySignals;

    /**
     * For EventFactory Call
     */
    private SharedEvent() {
        this.strategySignals = new StrategySignal[StrategySlotCounter.getCurrentCount()];
    }

    /**
     * @param event FastMarketData
     * @return SharedEvent
     */
    public SharedEvent updateWith(FastMarketData event) {
        this.epochMicros = micros();
        this.type = SharedEventType.FAST_MARKET_DATA;
        this.payload.fastMarketData.copyOf(event);
        return this;
    }

    /**
     * @param event DepthMarketData
     * @return SharedEvent
     */
    public SharedEvent updateWith(DepthMarketData event) {
        this.epochMicros = micros();
        this.type = SharedEventType.DEPTH_MARKET_DATA;
        this.payload.depthMarketData.copyOf(event);
        return this;
    }

    /**
     * @param event InstrumentStatusReport
     * @return SharedEvent
     */
    public SharedEvent updateWith(InstrumentStatusReport event) {
        this.epochMicros = micros();
        this.type = SharedEventType.INSTRUMENT_STATUS_REPORT;
        this.payload.instrumentStatusReport.copyOf(event);
        return this;
    }

    /**
     * @param event AdaptorReport
     * @return SharedEvent
     */
    public SharedEvent updateWith(AdapterReport event) {
        this.epochMicros = micros();
        this.type = SharedEventType.ADAPTOR_STATUS_REPORT;
        this.payload.adapterReport.copyOf(event);
        return this;
    }

    /**
     * @param event PositionsReport
     * @return SharedEvent
     */
    public SharedEvent updateWith(PositionsReport event) {
        this.epochMicros = micros();
        this.type = SharedEventType.POSITIONS_REPORT;
        this.payload.positionsReport.copyOf(event);
        return this;
    }

    /**
     * @param event BalanceReport
     * @return SharedEvent
     */
    public SharedEvent updateWith(BalanceReport event) {
        this.epochMicros = micros();
        this.type = SharedEventType.BALANCE_REPORT;
        this.payload.balanceReport.copyOf(event);
        return this;
    }

    /**
     * @param event OrderReport
     * @return SharedEvent
     */
    public SharedEvent updateWith(OrderReport event) {
        this.epochMicros = micros();
        this.type = SharedEventType.ORDER_REPORT;
        this.payload.orderReport.copyOf(event);
        return this;
    }

    /**
     *
     * @param slot  int
     * @param event StrategySignal
     * @return SharedEvent
     */
    public SharedEvent updateWith(int slot, StrategySignal event) {
        this.epochMicros = micros();
        this.type = SharedEventType.STRATEGY_SIGNALS;
        this.strategySignals[slot].copyOf(event);
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
                    case CONTROL_COMMAND -> payload.controlCommand;
                    case FAST_MARKET_DATA -> payload.fastMarketData;
                    case DEPTH_MARKET_DATA -> payload.depthMarketData;
                    case ORDER_REPORT -> payload.orderReport;
                    case POSITIONS_REPORT -> payload.positionsReport;
                    case BALANCE_REPORT -> payload.balanceReport;
                    case ADAPTOR_STATUS_REPORT -> payload.adapterReport;
                    case INSTRUMENT_STATUS_REPORT -> payload.instrumentStatusReport;
                    case STRATEGY_SIGNALS -> strategySignals;
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

    public record Payload(
            ControlCommand controlCommand,
            FastMarketData fastMarketData,
            DepthMarketData depthMarketData,
            InstrumentStatusReport instrumentStatusReport,
            AdapterReport adapterReport,
            PositionsReport positionsReport,
            BalanceReport balanceReport,
            OrderReport orderReport) {
    }

}
