package io.cygnux.rapid.core.adapter;

import io.cygnux.rapid.core.account.Account;
import io.cygnux.rapid.core.shared.SharedEvent;
import io.cygnux.rapid.core.shared.SharedEventbus;
import io.cygnux.rapid.core.shared.SharedEventHandler;
import io.cygnux.rapid.core.shared.enums.AdapterType;
import io.cygnux.rapid.core.adapter.event.CancelOrder;
import io.cygnux.rapid.core.adapter.event.NewOrder;
import io.cygnux.rapid.core.adapter.event.QueryBalance;
import io.cygnux.rapid.core.adapter.event.QueryOrder;
import io.cygnux.rapid.core.adapter.event.QueryPosition;
import io.cygnux.rapid.core.adapter.event.SubscribeMarketData;
import io.mercury.common.annotation.AbstractFunction;
import io.mercury.common.concurrent.disruptor.RingEventbus;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.mercury.common.state.EnableableComponent;
import io.mercury.common.state.StartupException;
import org.slf4j.Logger;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.ThreadSafe;
import java.io.IOException;

import static io.mercury.common.concurrent.disruptor.base.CommonStrategy.Yielding;
import static io.mercury.common.lang.Asserter.nonNull;

/**
 * @author yellow013
 */
@ThreadSafe
public abstract non-sealed class AbstractAdapter extends EnableableComponent implements Adapter {

    protected final Logger log = Log4j2LoggerFactory.getLogger(this.getClass());

    /**
     * Adaptor标识
     */
    protected final String adaptorId;

    /**
     * 托管投资账户
     */
    protected final Account account;

    /**
     * Normal
     */
    protected AdapterRunningMode runningMode = AdapterRunningMode.FULL;

    /**
     * 当前状态
     */
    protected AdapterStatus status = new AdapterStatus()
            .setMarketDataEnabled(false)
            .setTraderEnabled(false);

    /**
     * 是否为异步调用
     */
    private final boolean isAsync;

    /**
     * 接收队列
     */
    private RingEventbus<SentEvent> receiveQueue;

    // 入站队列处理器
    protected final SharedEventHandler inboundHandler;

    protected final SharedEventbus aggregateEventbus = new SharedEventbus() {
        @Override
        public void onEvent(SharedEvent event, long sequence, boolean endOfBatch) throws Exception {
            inboundHandler.onEvent(event, sequence, endOfBatch);
        }
    };

    /**
     * @param account Account
     * @param isAsync boolean
     */
    protected AbstractAdapter(@Nonnull Account account, boolean isAsync, SharedEventHandler inboundHandler) {
        nonNull(account, "account");
        this.account = account;
        this.isAsync = isAsync;
        this.adaptorId = this.getClass().getSimpleName() +
                "-" + account.getBrokerCode() + ":" + account.getInvestorCode();
        this.inboundHandler = inboundHandler;
        if (isAsync) {
            receiveQueue = RingEventbus
                    .singleProducer(SentEvent.EVENT_FACTORY)
                    .name(adaptorId + "-receive-queue")
                    .size(32)
                    .waitStrategy(Yielding.get())
                    .process(this::handleOutboundEvent);
        }
    }

    /**
     * @param event SendEvent
     */
    private void handleOutboundEvent(SentEvent event) {
        switch (event.getType()) {
            case SUBSCRIBE_MARKET_DATA -> {
                log.info("{} -> Call directSubscribeMarketData, event -> {} ", adaptorId, event);
                directSubscribeMarketData(event.getSubscribeMarketData());
            }
            case NEW_ORDER -> {
                log.info("{} -> Call directNewOrder, event -> {} ", adaptorId, event);
                directNewOrder(event.getNewOrder());
            }
            case CANCEL_ORDER -> {
                log.info("{} -> Call directCancelOrder, event -> {} ", adaptorId, event);
                directCancelOrder(event.getCancelOrder());
            }
            case QUERY_ORDER -> {
                log.info("{} -> Call directQueryOrder, event -> {} ", adaptorId, event);
                directQueryOrder(event.getQueryOrder());
            }
            case QUERY_POSITIONS -> {
                log.info("{} -> Call directQueryPositions, event -> {} ", adaptorId, event);
                directQueryPosition(event.getQueryPosition());
            }
            case QUERY_BALANCE -> {
                log.info("{} -> Call directQueryBalance, event -> {} ", adaptorId, event);
                directQueryBalance(event.getQueryBalance());
            }
            default -> log.warn("{} -> Unprocessable event -> {}", adaptorId, event);
        }
    }

    @Nonnull
    @Override
    public String getAdaptorId() {
        return adaptorId;
    }

    @Nonnull
    @Override
    public Account getBoundAccount() {
        return account;
    }

    /**
     * 更新Adaptor状态
     *
     * @param adapterType ChannelType
     * @param isEnabled   boolean
     */
    @Override
    public void updateStatus(AdapterType adapterType, boolean isEnabled) {
        switch (adapterType) {
            case MARKET_DATA -> status.setMarketDataEnabled(isEnabled);
            case TRADING -> status.setTraderEnabled(isEnabled);
            case FULL -> status.setMarketDataEnabled(isEnabled)
                    .setTraderEnabled(isEnabled);
            case null -> status.setMarketDataEnabled(isEnabled)
                    .setTraderEnabled(isEnabled);
        }
    }

    /**
     * 获取当前Adaptor状态
     *
     * @return AdaptorStatus
     */
    @Override
    public AdapterStatus currentStatus() {
        return status;
    }

    @Override
    public boolean subscribeMarketData(@Nonnull SubscribeMarketData subscribeMarketData) {
        if (isAsync) {
            log.info("{} Async SubscribeMarketData -> {}", adaptorId, subscribeMarketData);
            receiveQueue.publish((event, sequence) -> event.updateWith(subscribeMarketData));
            return true;
        } else {
            log.info("{} Sync SubscribeMarketData -> {}", adaptorId, subscribeMarketData);
            return directSubscribeMarketData(subscribeMarketData);
        }
    }

    @Override
    public boolean newOrder(@Nonnull NewOrder order) {
        if (isAsync) {
            log.info("{} Async NewOrder -> {}", adaptorId, order);
            receiveQueue.publish((event, sequence) -> event.updateWith(order));
            return true;
        } else {
            log.info("{} Sync NewOrder -> {}", adaptorId, order);
            return directNewOrder(order);
        }
    }

    @Override
    public boolean cancelOrder(@Nonnull CancelOrder order) {
        if (isAsync) {
            log.info("{} Async CancelOrder -> {}", adaptorId, order);
            receiveQueue.publish((event, sequence) -> event.updateWith(order));
            return true;
        } else {
            log.info("{} Sync CancelOrder -> {}", adaptorId, order);
            return directCancelOrder(order);
        }
    }

    @Override
    public boolean queryOrder(@Nonnull QueryOrder query) {
        if (isAsync) {
            log.info("{} Async QueryOrder -> {}", adaptorId, query);
            receiveQueue.publish((event, sequence) -> event.updateWith(query));
            return true;
        } else {
            log.info("{} Sync QueryOrder -> {}", adaptorId, query);
            return directQueryOrder(query);
        }
    }

    @Override
    public boolean queryPosition(@Nonnull QueryPosition query) {
        if (isAsync) {
            log.info("{} Async QueryPositions -> {}", adaptorId, query);
            receiveQueue.publish((event, sequence) -> event.updateWith(query));
            return true;
        } else {
            log.info("{} Sync QueryPositions -> {}", adaptorId, query);
            return directQueryPosition(query);
        }
    }

    @Override
    public boolean queryBalance(@Nonnull QueryBalance query) {
        if (isAsync) {
            log.info("{} Async QueryBalance -> {}", adaptorId, query);
            receiveQueue.publish((event, sequence) -> event.updateWith(query));
            return true;
        } else {
            log.info("{} Sync QueryBalance -> {}", adaptorId, query);
            return directQueryBalance(query);
        }
    }

    @AbstractFunction
    protected abstract boolean directSubscribeMarketData(@Nonnull SubscribeMarketData subscribeMarketData);

    @AbstractFunction
    protected abstract boolean directNewOrder(@Nonnull NewOrder order);

    @AbstractFunction
    protected abstract boolean directCancelOrder(@Nonnull CancelOrder order);

    @AbstractFunction
    protected abstract boolean directQueryOrder(@Nonnull QueryOrder query);

    @AbstractFunction
    protected abstract boolean directQueryPosition(@Nonnull QueryPosition query);

    @AbstractFunction
    protected abstract boolean directQueryBalance(@Nonnull QueryBalance query);

    @Override
    public boolean startup() throws IOException, IllegalStateException, StartupException {
        try {
            if (!isEnabled())
                throw new IllegalStateException("[" + adaptorId + "] currently unavailable");
            return startup0();
        } catch (Exception e) {
            throw new StartupException("[" + adaptorId + "] unable to start because: " + e.getMessage(), e);
        }
    }

    @AbstractFunction
    protected abstract boolean startup0();

}
