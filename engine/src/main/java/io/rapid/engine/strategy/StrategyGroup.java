package io.rapid.engine.strategy;

import io.mercury.common.param.Params;
import io.rapid.core.account.SubAccount;
import io.rapid.core.instrument.Instrument;
import io.rapid.core.mkd.FastMarketData;
import io.rapid.core.order.Order;
import io.rapid.core.serializable.avro.inbound.AdaptorEvent;
import io.rapid.core.strategy.Strategy;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class StrategyGroup extends BaseStrategy implements Strategy {

    private CompletableFuture<Order> future = new CompletableFuture<>();

    protected StrategyGroup(int strategyId, @Nonnull String strategyName,
                            @Nonnull SubAccount subAccount, @Nonnull Params params, @Nonnull Instrument... instruments) {
        super(strategyId, strategyName, subAccount, params, instruments);
    }

    @Override
    protected void handleMarketData(FastMarketData marketData) {

    }

    @Override
    protected void handleOrder(Order order) {

    }

    @Override
    public void onAdaptorEvent(@Nonnull AdaptorEvent event) {

    }

    /**
     * Enable Account
     *
     * @param subAccountId int
     */
    @Override
    public void enableSubAccount(int subAccountId) {

    }

    /**
     * Disable Account
     *
     * @param subAccountId int
     */
    @Override
    public void disableSubAccount(int subAccountId) {

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
