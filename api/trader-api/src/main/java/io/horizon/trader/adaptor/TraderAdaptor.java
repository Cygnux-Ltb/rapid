package io.horizon.trader.adaptor;

import io.horizon.trader.account.Account;
import io.horizon.trader.serialization.avro.inbound.AvroCancelOrder;
import io.horizon.trader.serialization.avro.inbound.AvroQueryBalance;
import io.horizon.trader.serialization.avro.inbound.AvroQueryOrder;
import io.horizon.trader.serialization.avro.inbound.AvroQueryPositions;
import io.horizon.trader.serialization.avro.inbound.AvroNewOrder;
import io.mercury.common.fsm.Enableable;
import io.mercury.common.lang.exception.ComponentStartupException;

import javax.annotation.Nonnull;
import java.io.Closeable;
import java.io.IOException;

public interface TraderAdaptor extends Closeable, Enableable {

    /**
     * @return Adaptor ID
     */
    @Nonnull
    String getAdaptorId();

    /**
     * @return Account
     */
    @Nonnull
    Account getAccount();

    /**
     * Adaptor 启动函数
     *
     * @return boolean
     */
    boolean startup() throws IOException, IllegalStateException, ComponentStartupException;

    /**
     * 发送新订单
     *
     * @param order NewOrder
     * @return boolean
     */
    boolean newOrder(@Nonnull AvroNewOrder order);

    /**
     * 发送撤单请求
     *
     * @param order CancelOrder
     * @return boolean
     */
    boolean cancelOrder(@Nonnull AvroCancelOrder order);

    /**
     * 查询订单
     *
     * @param query QueryOrder
     * @return boolean
     */
    boolean queryOrder(@Nonnull AvroQueryOrder query);

    /**
     * 查询持仓
     *
     * @param query QueryPositions
     * @return boolean
     */
    boolean queryPositions(@Nonnull AvroQueryPositions query);

    /**
     * 查询余额
     *
     * @return boolean
     */
    boolean queryBalance(@Nonnull AvroQueryBalance query);

}
