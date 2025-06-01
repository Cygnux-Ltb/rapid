package io.cygnux.rapid.engine.adaptor;

import io.mercury.serialization.json.JsonObjectExt;
import io.mercury.transport.zmq.ZmqConfigurator;
import io.mercury.transport.zmq.ZmqPublisher;
import io.cygnux.rapid.core.account.Account;
import io.cygnux.rapid.core.adaptor.AbstractAdaptor;
import io.cygnux.rapid.core.event.outbound.CancelOrder;
import io.cygnux.rapid.core.event.outbound.NewOrder;
import io.cygnux.rapid.core.event.outbound.QueryBalance;
import io.cygnux.rapid.core.event.outbound.QueryOrder;
import io.cygnux.rapid.core.event.outbound.QueryPosition;
import io.cygnux.rapid.core.event.outbound.SubscribeMarketData;
import org.apache.fury.Fury;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;
import java.io.IOException;

import static io.cygnux.rapid.core.adaptor.Adaptor.publishPath;
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
        super(account, false);
        this.publisher = ZmqConfigurator
                .ipc(publishPath())
                .createPublisherWithBinary();
    }

    @Override
    protected boolean startup0() {
        return false;
    }

    // TODO 性能扩展使用
    // private final FuryMsg furyMsg = new FuryMsg();

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
