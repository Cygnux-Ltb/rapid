package io.cygnuxltb.adaptor.ctp;

import com.typesafe.config.Config;
import io.cygnuxltb.adaptor.ctp.gateway.msg.FtdcRspMsg;
import io.cygnuxltb.jcts.core.account.Account;
import io.cygnuxltb.jcts.core.adaptor.AbstractAdaptor;
import io.cygnuxltb.jcts.core.adaptor.AdaptorType;
import io.cygnuxltb.jcts.core.instrument.Instrument;
import io.cygnuxltb.jcts.core.serialization.avro.request.AvCancelOrderRequest;
import io.cygnuxltb.jcts.core.serialization.avro.request.AvNewOrderRequest;
import io.cygnuxltb.jcts.core.serialization.avro.request.AvQueryBalanceRequest;
import io.cygnuxltb.jcts.core.serialization.avro.request.AvQueryOrderRequest;
import io.cygnuxltb.jcts.core.serialization.avro.request.AvQueryPositionsRequest;
import io.mercury.common.concurrent.queue.ScQueue;
import io.mercury.common.concurrent.queue.ScQueueWithJCT;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.mercury.serialization.avro.msg.AvroBinaryMsg;
import io.mercury.transport.zmq.ZmqConfigurator;
import io.mercury.transport.zmq.ZmqPublisher;
import io.mercury.transport.zmq.ZmqSubscriber;
import org.slf4j.Logger;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * CTP Adaptor, 用于连接上期CTP柜台
 *
 * @author yellow013
 */
public class AsyncCtpAdaptor extends AbstractAdaptor {

    private static final Logger log = Log4j2LoggerFactory.getLogger(AsyncCtpAdaptor.class);

    private final CtpAdaptor adaptor;

    private final ZmqSubscriber source;
    private final ZmqPublisher<FtdcRspMsg> target;

    private static final String ClassName = AsyncCtpAdaptor.class.getSimpleName();

    /**
     * 传入MarketDataHandler, OrderReportHandler, AdaptorReportHandler实现,
     * 由构造函数内部转换为MPSC队列缓冲区
     *
     * @param account Account
     * @param config  Config
     */
    public AsyncCtpAdaptor(@Nonnull Account account, @Nonnull Config config) {
        super(ClassName, account);
        this.source = ZmqConfigurator.withConfig("adaptor.source", config)
                .newSubscriber((topic, msg) -> {
                    try {
                        AvroBinaryMsg.fromByteBuffer(ByteBuffer.wrap(msg));
                    } catch (IOException e) {
                        log.error("{}", e.getMessage(), e);
                    }
                });

        this.target = ZmqConfigurator.withConfig("adaptor.target", config)
                .newPublisher("", msg -> null);
        ScQueue<FtdcRspMsg> queue = ScQueueWithJCT
                .mpscQueue(ClassName + "-Buf").capacity(32)
                .process(target::publish);
        this.adaptor = new CtpAdaptor(account, CtpConfiguration.with(config), queue);
    }

    @Override
    protected boolean startup0() {
        try {
            return adaptor.startup();
        } catch (Exception e) {
            log.error("Gateway exception -> {}", e.getMessage(), e);
            return false;
        }
    }

    @Override
    public AdaptorType getAdaptorType() {
        return CtpAdaptorType.INSTANCE;
    }

    /**
     * 订阅行情实现
     */
    @Override
    public boolean subscribeMarketData(@Nonnull Instrument... instruments) {
        return false;
    }

    @Override
    public boolean newOrder(@Nonnull AvNewOrderRequest request) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean cancelOrder(@Nonnull AvCancelOrderRequest request) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean queryOrder(@Nonnull AvQueryOrderRequest request) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean queryPositions(@Nonnull AvQueryPositionsRequest request) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean queryBalance(@Nonnull AvQueryBalanceRequest request) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void close() throws IOException {
        source.close();
        target.close();
        adaptor.close();
    }

}
