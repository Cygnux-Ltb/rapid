package io.rapid.engine.adaptor;

import io.mercury.serialization.json.JsonRecord;
import io.mercury.transport.zmq.ZmqConfigurator;
import io.mercury.transport.zmq.ZmqPublisher;
import io.rapid.core.account.Account;
import io.rapid.core.adaptor.AbstractAdaptor;
import io.rapid.core.event.send.CancelOrder;
import io.rapid.core.event.send.NewOrder;
import io.rapid.core.event.send.QueryBalance;
import io.rapid.core.event.send.QueryOrder;
import io.rapid.core.event.send.QueryPositions;
import io.rapid.core.event.send.SubscribeMarketData;
import org.apache.fury.Fury;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

import static org.apache.fury.config.Language.JAVA;

@NotThreadSafe
public class ZmqRemoteAdaptor extends AbstractAdaptor {

    private final Fury fury = Fury.builder().withLanguage(JAVA)
            .requireClassRegistration(true)
            .build();

    /**
     *
     */
    private final ZmqPublisher<byte[]> publisher;

    private final byte[] topic = "".getBytes();

    /**
     * @param account Account
     */
    public ZmqRemoteAdaptor(@Nonnull Account account) {
        super(account);
        this.publisher = ZmqConfigurator
                .ipc("upstream/" + account.getBrokerCode() + "/" + account.getAccountId())
                .createPublisherWithBinary();
    }

    @Override
    protected boolean startup0() {
        return false;
    }

    // TODO 性能扩展使用
    // private final FuryMsg furyMsg = new FuryMsg();

    private final JsonRecord record = new JsonRecord();

    /**
     * 订阅行情
     *
     * @param subscribeMarketData SubscribeMarketData
     */
    @Override
    public boolean subscribeMarketData(@Nonnull SubscribeMarketData subscribeMarketData) {


        return false;
    }

    /**
     * 发送订单请求
     *
     * @param order NewOrder
     * @return boolean
     */
    @Override
    public boolean newOrder(@Nonnull NewOrder order) {
        return false;
    }

    /**
     * 发送撤单请求
     *
     * @param order CancelOrder
     * @return boolean
     */
    @Override
    public boolean cancelOrder(@Nonnull CancelOrder order) {
        return false;
    }

    /**
     * 查询订单
     *
     * @param query QueryOrder
     * @return boolean
     */
    @Override
    public boolean queryOrder(@Nonnull QueryOrder query) {
        return false;
    }

    /**
     * 查询持仓
     *
     * @param query QueryPositions
     * @return boolean
     */
    @Override
    public boolean queryPositions(@Nonnull QueryPositions query) {
        return false;
    }

    /**
     * 查询余额
     *
     * @param query QueryBalance
     * @return boolean
     */
    @Override
    public boolean queryBalance(@Nonnull QueryBalance query) {
        return false;
    }

    @Override
    public void close() throws Exception {
        publisher.close();
    }

}
