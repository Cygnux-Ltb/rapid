package io.rapid.core.event;

import com.lmax.disruptor.EventFactory;
import io.mercury.common.epoch.EpochUnit;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.mercury.common.serialization.specific.JsonSerializable;
import io.mercury.serialization.json.JsonRecord;
import io.rapid.core.event.outbound.CancelOrder;
import io.rapid.core.event.outbound.NewOrder;
import io.rapid.core.event.outbound.QueryBalance;
import io.rapid.core.event.outbound.QueryOrder;
import io.rapid.core.event.outbound.QueryPosition;
import io.rapid.core.event.outbound.SubscribeMarketData;
import lombok.Getter;
import org.slf4j.Logger;

import javax.annotation.Nonnull;
import java.util.concurrent.atomic.AtomicBoolean;

import static io.mercury.common.epoch.HighResolutionEpoch.micros;

public final class OutboundEvent implements JsonSerializable {

    private static final Logger log = Log4j2LoggerFactory.getLogger(OutboundEvent.class);

    public static final EventFactory<OutboundEvent> EVENT_FACTORY = OutboundEvent::new;

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
    private OutboundEventType type = null;

    /// EVENT INSTANCE ///
    @Getter
    private final NewOrder newOrder = new NewOrder();
    @Getter
    private final CancelOrder cancelOrder = new CancelOrder();
    @Getter
    private final QueryOrder queryOrder = new QueryOrder();
    @Getter
    private final QueryPosition queryPosition = new QueryPosition();
    @Getter
    private final QueryBalance queryBalance = new QueryBalance();
    @Getter
    private final SubscribeMarketData subscribeMarketData = new SubscribeMarketData();
    /// EVENT INSTANCE ///

    /**
     * For EventFactory Call
     */
    OutboundEvent() {
    }


    /**
     * @param newOrder NewOrder
     * @return SendEvent
     */
    public OutboundEvent updateWith(NewOrder newOrder) {
        this.epochMicros = micros();
        this.type = OutboundEventType.NewOrder;
        this.newOrder.copyFrom(newOrder);
        return this;
    }

    /**
     * @param cancelOrder CancelOrder
     * @return SendEvent
     */
    public OutboundEvent updateWith(CancelOrder cancelOrder) {
        this.epochMicros = micros();
        this.type = OutboundEventType.CancelOrder;
        this.cancelOrder.copyFrom(cancelOrder);
        return this;
    }

    /**
     * @param queryOrder QueryOrder
     * @return SendEvent
     */
    public OutboundEvent updateWith(QueryOrder queryOrder) {
        this.epochMicros = micros();
        this.type = OutboundEventType.QueryOrder;
        this.queryOrder.copyFrom(queryOrder);
        return this;
    }

    /**
     * @param queryPosition QueryPositions
     * @return SendEvent
     */
    public OutboundEvent updateWith(QueryPosition queryPosition) {
        this.epochMicros = micros();
        this.type = OutboundEventType.QueryPosition;
        this.queryPosition.copyFrom(queryPosition);
        return this;
    }

    /**
     * @param queryBalance QueryBalance
     * @return SendEvent
     */
    public OutboundEvent updateWith(QueryBalance queryBalance) {
        this.epochMicros = micros();
        this.type = OutboundEventType.QueryBalance;
        this.queryBalance.copyFrom(queryBalance);
        return this;
    }

    /**
     * @param subscribeMarketData SubscribeMarketData
     * @return SendEvent
     */
    public OutboundEvent updateWith(SubscribeMarketData subscribeMarketData) {
        this.epochMicros = micros();
        this.type = OutboundEventType.SubscribeMarketData;
        this.subscribeMarketData.copyFrom(subscribeMarketData);
        return this;
    }

    @Override
    public String toString() {
        return toJson();
    }

    @Nonnull
    @Override
    public String toJson() {
        return new JsonRecord()
                .setTitle(type.name())
                .setEpochUnit(EpochUnit.MICROS)
                .setEpochTime(epochMicros)
                .setRecord(switch (type) {
                    case NewOrder -> newOrder;
                    case CancelOrder -> cancelOrder;
                    case QueryOrder -> queryOrder;
                    case QueryPosition -> queryPosition;
                    case QueryBalance -> queryBalance;
                    case SubscribeMarketData -> subscribeMarketData;
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

    /**
     * 输出日志
     *
     * @return OutboundEvent
     */
    public OutboundEvent logging() {
        if (isLogging.get())
            log.info("OutboundEvent logging -> {}", this);
        return this;
    }

}
