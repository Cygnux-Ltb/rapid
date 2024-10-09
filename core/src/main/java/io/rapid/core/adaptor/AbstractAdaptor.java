package io.rapid.core.adaptor;

import io.mercury.common.annotation.AbstractFunction;
import io.mercury.common.concurrent.disruptor.RingEventbus;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.mercury.common.state.EnableableComponent;
import io.mercury.common.state.StartupException;
import io.rapid.core.account.Account;
import io.rapid.core.event.OutboundEvent;
import io.rapid.core.event.enums.AdaptorStatus;
import io.rapid.core.event.outbound.CancelOrder;
import io.rapid.core.event.outbound.NewOrder;
import io.rapid.core.event.outbound.QueryBalance;
import io.rapid.core.event.outbound.QueryOrder;
import io.rapid.core.event.outbound.QueryPosition;
import io.rapid.core.event.outbound.SubscribeMarketData;
import org.slf4j.Logger;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.ThreadSafe;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

import static io.mercury.common.concurrent.disruptor.base.CommonStrategy.Yielding;
import static io.mercury.common.lang.Asserter.nonNull;

/**
 * @author yellow013
 */
@ThreadSafe
public abstract non-sealed class AbstractAdaptor extends EnableableComponent implements Adaptor {

    private static final Logger log = Log4j2LoggerFactory.getLogger(AbstractAdaptor.class);

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
    protected AdaptorRunningMode runningMode = AdaptorRunningMode.FULL;

    /**
     * 当前状态
     */
    protected AtomicReference<AdaptorStatus> status = new AtomicReference<>(AdaptorStatus.ALL_DISABLE);

    /**
     * 是否为异步调用
     */
    private final boolean isAsync;

    /**
     * 接收队列
     */
    private RingEventbus<OutboundEvent> receiveQueue;

    /**
     * @param account Account
     */
    protected AbstractAdaptor(@Nonnull Account account, boolean isAsync) {
        nonNull(account, "account");
        this.account = account;
        this.isAsync = isAsync;
        this.adaptorId = this.getClass().getSimpleName() +
                "[" + account.getBrokerCode() + ":" + account.getInvestorCode() + "]";
        if (isAsync) {
            receiveQueue = RingEventbus
                    .singleProducer(OutboundEvent.EVENT_FACTORY)
                    .name("receive-queue")
                    .size(64)
                    .waitStrategy(Yielding.get())
                    .process(this::handleSendEvent);
        }
    }

    /**
     * @param event SendEvent
     */
    private void handleSendEvent(OutboundEvent event) {
        switch (event.getType()) {
            case SubscribeMarketData -> {
                log.info("{} -> Call directSubscribeMarketData, event -> {} ", adaptorId, event);
                directSubscribeMarketData(event.getSubscribeMarketData());
            }
            case NewOrder -> {
                log.info("{} -> Call directNewOrder, event -> {} ", adaptorId, event);
                directNewOrder(event.getNewOrder());
            }
            case CancelOrder -> {
                log.info("{} -> Call directCancelOrder, event -> {} ", adaptorId, event);
                directCancelOrder(event.getCancelOrder());
            }
            case QueryOrder -> {
                log.info("{} -> Call directQueryOrder, event -> {} ", adaptorId, event);
                directQueryOrder(event.getQueryOrder());
            }
            case QueryPosition -> {
                log.info("{} -> Call directQueryPositions, event -> {} ", adaptorId, event);
                directQueryPositions(event.getQueryPosition());
            }
            case QueryBalance -> {
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
     * @param status AdaptorStatus
     */
    @Override
    public void updateStatus(AdaptorStatus status) {
        this.status.set(status);
    }

    /**
     * 获取当前Adaptor状态
     *
     * @return AdaptorStatus
     */
    @Override
    public AdaptorStatus currentStatus() {
        return status.get();
    }

    @Override
    public boolean subscribeMarketData(@Nonnull SubscribeMarketData subscribeMarketData) {
        if (isAsync) {
            log.info("{} Enqueue::SubscribeMarketData -> {}", adaptorId, subscribeMarketData);
            receiveQueue.publish((event, sequence) -> event.updateWith(subscribeMarketData));
            return true;
        } else {
            log.info("{} Direct::SubscribeMarketData -> {}", adaptorId, subscribeMarketData);
            return directSubscribeMarketData(subscribeMarketData);
        }
    }

    @Override
    public boolean newOrder(@Nonnull NewOrder order) {
        if (isAsync) {
            log.info("{} Enqueue::NewOrder -> {}", adaptorId, order);
            receiveQueue.publish((event, sequence) -> event.updateWith(order));
            return true;
        } else {
            log.info("{} Direct::NewOrder -> {}", adaptorId, order);
            return directNewOrder(order);
        }
    }

    @Override
    public boolean cancelOrder(@Nonnull CancelOrder order) {
        if (isAsync) {
            log.info("{} Enqueue::CancelOrder -> {}", adaptorId, order);
            receiveQueue.publish((event, sequence) -> event.updateWith(order));
            return true;
        } else {
            log.info("{} Direct::CancelOrder -> {}", adaptorId, order);
            return directCancelOrder(order);
        }
    }

    @Override
    public boolean queryOrder(@Nonnull QueryOrder query) {
        if (isAsync) {
            log.info("{} Enqueue::QueryOrder -> {}", adaptorId, query);
            receiveQueue.publish((event, sequence) -> event.updateWith(query));
            return true;
        } else {
            log.info("{} Direct::QueryOrder -> {}", adaptorId, query);
            return directQueryOrder(query);
        }
    }

    @Override
    public boolean queryPosition(@Nonnull QueryPosition query) {
        if (isAsync) {
            log.info("{} Enqueue::QueryPositions -> {}", adaptorId, query);
            receiveQueue.publish((event, sequence) -> event.updateWith(query));
            return true;
        } else {
            log.info("{} Direct::QueryPositions -> {}", adaptorId, query);
            return directQueryPositions(query);
        }
    }

    @Override
    public boolean queryBalance(@Nonnull QueryBalance query) {
        if (isAsync) {
            log.info("{} Enqueue::QueryBalance -> {}", adaptorId, query);
            receiveQueue.publish((event, sequence) -> event.updateWith(query));
            return true;
        } else {
            log.info("{} Direct::QueryBalance -> {}", adaptorId, query);
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
    protected abstract boolean directQueryPositions(@Nonnull QueryPosition query);

    @AbstractFunction
    protected abstract boolean directQueryBalance(@Nonnull QueryBalance query);

    @Override
    public boolean startup() throws IOException, IllegalStateException, StartupException {
        try {
            return startup0();
        } catch (IOException ioe) {
            throw ioe;
        } catch (Exception e) {
            throw new StartupException(adaptorId + " -> " + e.getMessage(), e);
        }
    }

    @AbstractFunction
    protected abstract boolean startup0() throws Exception;

}
