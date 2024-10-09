package io.rapid.engine;

import io.mercury.common.collections.MutableLists;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.rapid.core.EventScheduler;
import io.rapid.core.adaptor.AdaptorManager;
import io.rapid.core.event.InboundEvent;
import io.rapid.core.event.OutboundEvent;
import io.rapid.core.event.container.InboundEventLoop;
import io.rapid.core.event.container.OutboundEventLoop;
import io.rapid.core.event.inbound.AdaptorReport;
import io.rapid.core.event.inbound.BalanceReport;
import io.rapid.core.event.inbound.DepthMarketData;
import io.rapid.core.event.inbound.FastMarketData;
import io.rapid.core.event.inbound.MarketDataSubscribeReport;
import io.rapid.core.event.inbound.OrderReport;
import io.rapid.core.event.inbound.PositionsReport;
import io.rapid.core.event.outbound.CancelOrder;
import io.rapid.core.event.outbound.NewOrder;
import io.rapid.core.event.outbound.QueryBalance;
import io.rapid.core.event.outbound.QueryOrder;
import io.rapid.core.event.outbound.QueryPosition;
import io.rapid.core.event.outbound.StrategySignal;
import io.rapid.core.event.outbound.SubscribeMarketData;
import io.rapid.core.order.OrderManager;
import io.rapid.core.strategy.StrategyManager;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.eclipse.collections.api.list.MutableList;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service("eventScheduler")
public class EventSchedulerService implements EventScheduler {

    private final MutableList<StrategySignal> trdSignalList = MutableLists.newFastList(256);

    private static final Logger log = Log4j2LoggerFactory.getLogger(EventSchedulerService.class);

    @Resource
    private OrderManager orderManager;

    @Resource
    private AdaptorManager adaptorManager;

    @Resource
    private StrategyManager strategyManager;

    private final InboundEventLoop inboundEventLoop = new InboundEventLoop() {
        @Override
        protected void process(InboundEvent event) {
            switch (event.getType()) {
                case FastMarketData -> handleFastMarketData(event.getFastMarketData());
                case DepthMarketData -> handleDepthMarketData(event.getDepthMarketData());
                case OrderReport -> handleOrderReport(event.getOrderReport());
                case PositionsReport -> handlePositionsReport(event.getPositionsReport());
                case BalanceReport -> handleBalanceReport(event.getBalanceReport());
                case AdaptorReport -> handleAdaptorReport(event.getAdaptorReport());
                case MarketDataSubscribeReport -> handleMarketDataSubscribeReport(event.getMarketDataSubscribeReport());
                case Invalid -> log.error("NOTE Unknown InboundEvent -> {}", event);
            }
        }
    };

    private final OutboundEventLoop outboundEventLoop = new OutboundEventLoop() {
        @Override
        protected void process(OutboundEvent event) {
            switch (event.getType()) {
                case StrategySignal -> handleStrategySignal(event.getStrategySignal());
                case NewOrder -> handleNewOrder(event.getNewOrder());
                case CancelOrder -> handleCancelOrder(event.getCancelOrder());
                case SubscribeMarketData -> handleSubscribeMarketData(event.getSubscribeMarketData());
                case QueryOrder -> handleQueryOrder(event.getQueryOrder());
                case QueryPosition -> handleQueryPosition(event.getQueryPosition());
                case QueryBalance -> handleQueryBalance(event.getQueryBalance());
                case Invalid -> log.error("NOTE Unknown OutboundEvent -> {}", event);
            }
        }
    };

    public boolean addTradeSignal(StrategySignal signal) {
        return trdSignalList.add(signal);
    }


    @PostConstruct
    public void init() {

    }


    /**
     * 行情处理
     *
     * @param event DepthMarketData
     */
    @Override
    public void handleDepthMarketData(DepthMarketData event) {

    }

    /**
     * 行情处理
     *
     * @param event FastMarketData
     */
    @Override
    public void handleFastMarketData(FastMarketData event) {

    }

    /**
     * 行情订阅回报处理
     *
     * @param event MarketDataSubscribeReport
     */
    @Override
    public void handleMarketDataSubscribeReport(MarketDataSubscribeReport event) {

    }

    /**
     * 订单回报处理
     *
     * @param event OrderReport
     */
    @Override
    public void handleOrderReport(OrderReport event) {

    }

    /**
     * Adaptor回报处理
     *
     * @param event AdaptorReport
     */
    @Override
    public void handleAdaptorReport(AdaptorReport event) {

    }

    /**
     * 余额回报处理
     *
     * @param event BalanceReport
     */
    @Override
    public void handleBalanceReport(BalanceReport event) {

    }

    /**
     * 持仓回报处理
     *
     * @param event PositionsReport
     */
    @Override
    public void handlePositionsReport(PositionsReport event) {

    }

    /**
     * 处理撤单
     *
     * @param event CancelOrder
     */
    @Override
    public void handleCancelOrder(CancelOrder event) {

    }

    /**
     * 处理新订单
     *
     * @param event NewOrder
     */
    @Override
    public void handleNewOrder(NewOrder event) {

    }

    /**
     * 处理余额查询
     *
     * @param event QueryBalance
     */
    @Override
    public void handleQueryBalance(QueryBalance event) {
        adaptorManager.sendQueryBalance(event);
    }

    /**
     * 处理订单查询
     *
     * @param event QueryOrder
     */
    @Override
    public void handleQueryOrder(QueryOrder event) {
        adaptorManager.sendQueryOrder(event);
    }

    /**
     * 处理仓位查询
     *
     * @param event QueryPosition
     */
    @Override
    public void handleQueryPosition(QueryPosition event) {
        adaptorManager.sendQueryPositions(event);
    }

    /**
     * 处理策略信号
     *
     * @param event StrategySignal
     */
    @Override
    public void handleStrategySignal(StrategySignal event) {
        int orderWatermark = event.getOrderWatermark();

        switch (orderWatermark) {
            case 1 -> {
                switch (event.getDirection()) {
                    case LONG:

                        break;
                    case SHORT:

                        break;

                    default:
                        break;
                }
            }
            case -1 -> {
                switch (event.getDirection()) {
                    case LONG:

                        break;

                    case SHORT:

                        break;

                    default:
                        break;
                }
            }
            default -> {
            }
        }
    }

    /**
     * 处理行情订阅
     *
     * @param event SubscribeMarketData
     */
    @Override
    public void handleSubscribeMarketData(SubscribeMarketData event) {

    }


    /**
     * Closes this stream and releases any system resources associated
     * with it. If the stream is already closed then invoking this
     * method has no effect.
     *
     * <p> As noted in {@link AutoCloseable#close()}, cases where the
     * close may fail require careful attention. It is strongly advised
     * to relinquish the underlying resources and to internally
     * <em>mark</em> the {@code Closeable} as closed, prior to throwing
     * the {@code IOException}.
     *
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void close() throws IOException {

    }


}
