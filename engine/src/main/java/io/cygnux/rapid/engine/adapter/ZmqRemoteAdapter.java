package io.cygnux.rapid.engine.adapter;

import io.cygnux.rapid.core.types.account.Account;
import io.cygnux.rapid.core.adapter.AbstractAdapter;
import io.cygnux.rapid.core.types.adapter.event.CancelOrder;
import io.cygnux.rapid.core.types.adapter.event.NewOrder;
import io.cygnux.rapid.core.types.adapter.event.QueryBalance;
import io.cygnux.rapid.core.types.adapter.event.QueryOrder;
import io.cygnux.rapid.core.types.adapter.event.QueryPosition;
import io.cygnux.rapid.core.types.adapter.event.SubscribeMarketData;
import io.cygnux.rapid.core.event.SharedEventHandler;
import io.mercury.serialization.json.JsonObjectExt;
import io.mercury.transport.zmq.ZmqCfg;
import io.mercury.transport.zmq.ZmqPublisher;
import org.apache.fory.Fory;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;
import java.io.IOException;

import static org.apache.fory.config.Language.JAVA;

@NotThreadSafe
public class ZmqRemoteAdapter extends AbstractAdapter {

    private final Fory fory = Fory.builder().withLanguage(JAVA)
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
    public ZmqRemoteAdapter(@Nonnull Account account, SharedEventHandler inboundHandler) {
        super(account, false, inboundHandler);
        this.publisher = ZmqCfg
                .ipc(publishPath)
                .createPublisherWithBinary();
    }

    @Override
    protected boolean startup0() {
        return false;
    }

    // TODO 性能扩展使用
    // private final ForyMsg foryMsg = new ForyMsg();

    private final JsonObjectExt record = new JsonObjectExt();

    @Override
    protected boolean directSubscribeMarketData(@Nonnull SubscribeMarketData subscribeMarketData) {
        return false;
    }

    @Override
    protected boolean directNewOrder(@Nonnull NewOrder order) {
        return false;
    }

    @Override
    protected boolean directCancelOrder(@Nonnull CancelOrder order) {
        return false;
    }

    @Override
    protected boolean directQueryOrder(@Nonnull QueryOrder query) {
        return false;
    }

    @Override
    protected boolean directQueryPosition(@Nonnull QueryPosition query) {
        return false;
    }

    @Override
    protected boolean directQueryBalance(@Nonnull QueryBalance query) {
        return false;
    }

    @Override
    public void close() throws IOException {
        publisher.close();
    }

}
