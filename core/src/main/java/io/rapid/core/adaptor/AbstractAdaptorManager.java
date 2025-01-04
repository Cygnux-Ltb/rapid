package io.rapid.core.adaptor;

import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.rapid.core.event.inbound.AdaptorReport;
import io.rapid.core.event.outbound.CancelOrder;
import io.rapid.core.event.outbound.NewOrder;
import io.rapid.core.event.outbound.QueryBalance;
import io.rapid.core.event.outbound.QueryOrder;
import io.rapid.core.event.outbound.QueryPosition;
import io.rapid.core.event.outbound.SubscribeMarketData;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;
import org.slf4j.Logger;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.ThreadSafe;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

import static io.mercury.common.collections.MutableMaps.newIntObjectMap;
import static io.mercury.common.collections.MutableMaps.newUnifiedMap;

/**
 * @author yellow013
 * @topic 存储Adaptor和Mapping关系<br>
 * <p>
 * 1.以[accountId]查找Adaptor<br>
 * TODO 修改为线程安全的管理器<br>
 * <p>
 * 如果程序运行中不修改Adaptor的引用则可以在多个线程中调用Get函数<br>
 * 如果运行中Adaptor崩溃, 重新创建Adaptor则需要重新Put<br>
 * 目前不保证这一过程的线程安全
 */
@ThreadSafe
public abstract non-sealed class AbstractAdaptorManager implements AdaptorManager {

    private static final Logger log = Log4j2LoggerFactory.getLogger(AbstractAdaptorManager.class);

    /**
     * 存储[Adaptor], 使用[accountId]索引
     */
    protected final MutableIntObjectMap<Adaptor> mapByAccountId = newIntObjectMap();

    /**
     * 存储[Adaptor], 使用[adaptorId]索引
     */
    protected final MutableMap<String, Adaptor> mapByAdaptorId = newUnifiedMap();

    protected AtomicBoolean isClosed = new AtomicBoolean(false);

    @Override
    public void putAdaptor(@Nonnull Adaptor... adaptors) {
        Stream.of(adaptors).forEach(adaptor -> {
            var account = adaptor.getBoundAccount();
            mapByAccountId.put(account.getAccountId(), adaptor);
            // mapByAdaptorId.put(adaptor.getAdaptorId(), adaptor);
            log.info("PUT [Adaptor] to AdaptorManager, accountId==[{}], adaptorId==[{}], remark==[{}]",
                    account.getAccountId(), adaptor.getAdaptorId(), account.getRemark());
        });
    }

    @Override
    public Adaptor getAdaptor(int accountId) {
        return mapByAccountId.get(accountId);
    }

    @Override
    public Adaptor getAdaptor(String adaptorId) {
        return mapByAdaptorId.get(adaptorId);
    }

    @Override
    public void onAdaptorEvent(AdaptorReport event) {
        var adaptor = getAdaptor(event.getAccountId());
        var currentStatus = adaptor.currentStatus();
        var channelType = event.getChannelType();
        log.info("Adaptor -> [{}] current status update to [{}]",
                adaptor.getAdaptorId(), currentStatus);
        adaptor.updateStatus(channelType, event.isAvailable());
    }


    /**
     * 提交行情订阅
     *
     * @param subscribeMarketData SubscribeMarketData
     */
    public boolean commitSubscribeMarketData(SubscribeMarketData subscribeMarketData) {
        var adaptor = getAdaptor(subscribeMarketData.getAccountId());
        if (adaptor == null) {
            log.error("AbstractAdaptorManager::commitSubscribeMarketData adaptor is NULL, accountId==[{}]",
                    subscribeMarketData.getAccountId());
            return false;
        }
        if (adaptor.isDisabled()) {
            log.error("AbstractAdaptorManager::commitSubscribeMarketData adaptor is DISABLED, accountId==[{}]",
                    subscribeMarketData.getAccountId());
            return false;
        }
        boolean ok = adaptor.subscribeMarketData(subscribeMarketData);
        if (!ok)
            log.error("{}::subscribeMarketData FAILED", adaptor.getClass().getSimpleName());
        return ok;
    }

    /**
     * 提交订单请求
     *
     * @param order NewOrder
     */
    public boolean commitNewOrder(NewOrder order) {
        var adaptor = getAdaptor(order.getAccountId());
        if (adaptor == null) {
            log.error("AbstractAdaptorManager::commitNewOrder adaptor is NULL, accountId==[{}]",
                    order.getAccountId());
            return false;
        }
        if (adaptor.isDisabled()) {
            log.error("AbstractAdaptorManager::commitNewOrder adaptor is DISABLED, accountId==[{}]",
                    order.getAccountId());
            return false;
        }
        boolean ok = adaptor.newOrder(order);
        if (!ok)
            log.error("{}::newOrder FAILED", adaptor.getClass().getSimpleName());
        return ok;
    }

    /**
     * 提交撤单请求
     *
     * @param order CancelOrder
     */
    public boolean commitCancelOrder(CancelOrder order) {
        var adaptor = getAdaptor(order.getAccountId());
        if (adaptor == null) {
            log.error("AbstractAdaptorManager::commitCancelOrder adaptor is NULL, accountId==[{}]",
                    order.getAccountId());
            return false;
        }
        if (adaptor.isDisabled()) {
            log.error("AbstractAdaptorManager::commitCancelOrder adaptor is DISABLED, accountId==[{}]",
                    order.getAccountId());
            return false;
        }
        boolean ok = adaptor.cancelOrder(order);
        if (!ok)
            log.error("{}::cancelOrder FAILED", adaptor.getClass().getSimpleName());
        return ok;
    }

    /**
     * 提交查询订单请求
     *
     * @param query QueryOrder
     */
    public boolean commitQueryOrder(QueryOrder query) {
        var adaptor = getAdaptor(query.getAccountId());
        if (adaptor == null) {
            log.error("AbstractAdaptorManager::commitQueryOrder adaptor is NULL, accountId==[{}]",
                    query.getAccountId());
            return false;
        }
        if (adaptor.isDisabled()) {
            log.error("AbstractAdaptorManager::commitQueryOrder adaptor is DISABLED, accountId==[{}]",
                    query.getAccountId());
            return false;
        }
        boolean ok = adaptor.queryOrder(query);
        if (!ok)
            log.error("{}::queryOrder FAILED", adaptor.getClass().getSimpleName());
        return ok;
    }

    /**
     * 提交查询持仓请求
     *
     * @param query QueryPositions
     */
    public boolean commitQueryPositions(QueryPosition query) {
        var adaptor = getAdaptor(query.getAccountId());
        if (adaptor == null) {
            log.error("AbstractAdaptorManager::commitQueryPositions adaptor is NULL, accountId==[{}]",
                    query.getAccountId());
            return false;
        }
        if (adaptor.isDisabled()) {
            log.error("AbstractAdaptorManager::commitQueryPositions adaptor is DISABLED, accountId==[{}]",
                    query.getAccountId());
            return false;
        }
        boolean ok = adaptor.queryPosition(query);
        if (!ok)
            log.error("{}::queryPosition FAILED", adaptor.getClass().getSimpleName());
        return ok;
    }

    /**
     * 提交查询余额请求
     *
     * @param query QueryBalance
     */
    public boolean commitQueryBalance(QueryBalance query) {
        var adaptor = getAdaptor(query.getAccountId());
        if (adaptor == null) {
            log.error("AbstractAdaptorManager::commitQueryBalance adaptor is NULL, accountId==[{}]",
                    query.getAccountId());
            return false;
        }
        if (adaptor.isDisabled()) {
            log.error("AbstractAdaptorManager::commitQueryBalance adaptor is DISABLED, accountId==[{}]",
                    query.getAccountId());
            return false;
        }
        boolean ok = adaptor.queryBalance(query);
        if (!ok)
            log.error("{}::queryBalance FAILED", adaptor.getClass().getSimpleName());
        return ok;
    }


    @Override
    public boolean isClosed() {
        return false;
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
        if (isClosed.compareAndSet(false, true)) {

        }
    }


}
