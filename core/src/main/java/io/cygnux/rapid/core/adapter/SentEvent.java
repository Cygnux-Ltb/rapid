package io.cygnux.rapid.core.adapter;

import com.lmax.disruptor.EventFactory;
import io.cygnux.rapid.core.adapter.event.CancelOrder;
import io.cygnux.rapid.core.adapter.event.NewOrder;
import io.cygnux.rapid.core.adapter.event.QueryBalance;
import io.cygnux.rapid.core.adapter.event.QueryOrder;
import io.cygnux.rapid.core.adapter.event.QueryPosition;
import io.cygnux.rapid.core.adapter.event.SubscribeMarketData;
import io.mercury.common.epoch.EpochUnit;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.mercury.common.serialization.specific.JsonSerializable;
import io.mercury.serialization.json.JsonObjectExt;
import lombok.Getter;
import org.slf4j.Logger;

import javax.annotation.Nonnull;
import java.util.concurrent.atomic.AtomicBoolean;

import static io.mercury.common.epoch.HighResolutionEpoch.micros;

public final class SentEvent implements JsonSerializable {

    private static final Logger log = Log4j2LoggerFactory.getLogger(SentEvent.class);

    public static final EventFactory<SentEvent> EVENT_FACTORY = SentEvent::new;

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
    private SentEventType type = SentEventType.INVALID;

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
    SentEvent() {
    }


    /**
     * @param newOrder NewOrder
     * @return SendEvent
     */
    public SentEvent updateWith(NewOrder newOrder) {
        this.epochMicros = micros();
        this.type = SentEventType.NEW_ORDER;
        this.newOrder.copyOf(newOrder);
        return this;
    }

    /**
     * @param cancelOrder CancelOrder
     * @return SendEvent
     */
    public SentEvent updateWith(CancelOrder cancelOrder) {
        this.epochMicros = micros();
        this.type = SentEventType.CANCEL_ORDER;
        this.cancelOrder.copyOf(cancelOrder);
        return this;
    }

    /**
     * @param queryOrder QueryOrder
     * @return SendEvent
     */
    public SentEvent updateWith(QueryOrder queryOrder) {
        this.epochMicros = micros();
        this.type = SentEventType.QUERY_ORDER;
        this.queryOrder.copyOf(queryOrder);
        return this;
    }

    /**
     * @param queryPosition QueryPositions
     * @return SendEvent
     */
    public SentEvent updateWith(QueryPosition queryPosition) {
        this.epochMicros = micros();
        this.type = SentEventType.QUERY_POSITIONS;
        this.queryPosition.copyOf(queryPosition);
        return this;
    }

    /**
     * @param queryBalance QueryBalance
     * @return SendEvent
     */
    public SentEvent updateWith(QueryBalance queryBalance) {
        this.epochMicros = micros();
        this.type = SentEventType.QUERY_BALANCE;
        this.queryBalance.copyOf(queryBalance);
        return this;
    }

    /**
     * @param subscribeMarketData SubscribeMarketData
     * @return SendEvent
     */
    public SentEvent updateWith(SubscribeMarketData subscribeMarketData) {
        this.epochMicros = micros();
        this.type = SentEventType.SUBSCRIBE_MARKET_DATA;
        this.subscribeMarketData.copyOf(subscribeMarketData);
        return this;
    }

    @Override
    public String toString() {
        return toJson();
    }

    @Nonnull
    @Override
    public String toJson() {
        return new JsonObjectExt()
                .setTitle(type.name())
                .setEpochUnit(EpochUnit.MICROS)
                .setEpochTime(epochMicros)
                .setObject(switch (type) {
                    case NEW_ORDER -> newOrder;
                    case CANCEL_ORDER -> cancelOrder;
                    case QUERY_ORDER -> queryOrder;
                    case QUERY_POSITIONS -> queryPosition;
                    case QUERY_BALANCE -> queryBalance;
                    case SUBSCRIBE_MARKET_DATA -> subscribeMarketData;
                    case INVALID -> "INVALID";
                    case NEXT -> "NEXT";
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
    public SentEvent logging() {
        if (isLogging.get())
            log.info("OutboundEvent logging -> {}", this);
        return this;
    }

}
