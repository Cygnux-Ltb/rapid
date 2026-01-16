package io.cygnux.rapid.core.event;

import com.lmax.disruptor.EventFactory;
import io.cygnux.rapid.core.strategy.StrategySlotCounter;
import io.cygnux.rapid.core.types.event.enums.SharedEventType;
import io.cygnux.rapid.core.types.event.received.AdapterReport;
import io.cygnux.rapid.core.types.event.received.BalanceReport;
import io.cygnux.rapid.core.types.event.received.DepthMarketData;
import io.cygnux.rapid.core.types.event.received.FastMarketData;
import io.cygnux.rapid.core.types.event.received.InstrumentStatusReport;
import io.cygnux.rapid.core.types.event.received.OrderReport;
import io.cygnux.rapid.core.types.event.received.PositionsReport;
import io.cygnux.rapid.core.types.event.sent.ControlCommand;
import io.cygnux.rapid.core.types.event.sent.StrategySignal;
import io.mercury.common.epoch.EpochUnit;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.mercury.common.serialization.specific.JsonSerializable;
import io.mercury.serialization.json.JsonObjectExt;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.slf4j.Logger;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;
import java.util.concurrent.atomic.AtomicBoolean;

import static io.mercury.common.epoch.HighResolutionEpoch.micros;

/**
 * 核心事件模型
 */
@NotThreadSafe
@Accessors(fluent = true, chain = true)
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

    /**
     * 快速行情数据
     */
    @Getter
    private final FastMarketData fastMarketData = new FastMarketData();

    /**
     * 深度行情数据
     */
    @Getter
    private final DepthMarketData depthMarketData = new DepthMarketData();

    /**
     * 标的状态报告
     */
    @Getter
    private final InstrumentStatusReport instrumentStatusReport = new InstrumentStatusReport();

    /**
     * 适配器报告
     */
    @Getter
    private final AdapterReport adapterReport = new AdapterReport();

    /**
     * 仓位报告
     */
    @Getter
    private final PositionsReport positionsReport = new PositionsReport();

    /**
     * 资金报告
     */
    @Getter
    private final BalanceReport balanceReport = new BalanceReport();

    /**
     * 订单报告
     */
    @Getter
    private final OrderReport orderReport = new OrderReport();

    /**
     * 控制命令
     */
    @Getter
    private final ControlCommand controlCommand = new ControlCommand();

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
    public SharedEvent updateWith(AdapterReport event) {
        this.epochMicros = micros();
        this.type = SharedEventType.ADAPTER_STATUS_REPORT;
        this.adapterReport.copyOf(event);
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
                    case SharedEventType.FAST_MARKET_DATA -> fastMarketData;
                    case SharedEventType.DEPTH_MARKET_DATA -> depthMarketData;
                    case SharedEventType.INSTRUMENT_STATUS_REPORT -> instrumentStatusReport;
                    case SharedEventType.ADAPTER_STATUS_REPORT -> adapterReport;
                    case SharedEventType.POSITIONS_REPORT -> positionsReport;
                    case SharedEventType.BALANCE_REPORT -> balanceReport;
                    case SharedEventType.ORDER_REPORT -> orderReport;
                    case SharedEventType.CONTROL_COMMAND -> controlCommand;
                    case SharedEventType.STRATEGY_SIGNALS -> strategySignals;
                    case SharedEventType.SKIP -> "SKIP";
                    case SharedEventType.INVALID -> null;
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
     * @return SharedEvent
     */
    public SharedEvent logging() {
        if (isLogging.get())
            log.info("SharedEvent logging -> {}", this);
        return this;
    }

}
