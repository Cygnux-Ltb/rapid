package io.cygnux.rapid.adapter.ctp.component;

import io.cygnux.rapid.core.account.Account;
import io.cygnux.rapid.core.adapter.AdapterRunningMode;
import io.cygnux.rapid.core.adapter.SentEventType;
import io.cygnux.rapid.core.adapter.event.CancelOrder;
import io.cygnux.rapid.core.adapter.event.NewOrder;
import io.cygnux.rapid.core.adapter.event.QueryBalance;
import io.cygnux.rapid.core.adapter.event.QueryOrder;
import io.cygnux.rapid.core.adapter.event.QueryPosition;
import io.cygnux.rapid.core.adapter.event.SubscribeMarketData;
import io.cygnux.rapid.core.instrument.Instrument;
import io.cygnux.rapid.core.instrument.InstrumentKeeper;
import io.cygnux.rapid.core.event.SharedEventHandler;
import io.cygnux.rapid.core.event.enums.MarketDataType;
import io.cygnux.rapid.adapter.ctp.CtpAdapter;
import io.cygnux.rapid.adapter.ctp.FtdcRspHandler;
import io.cygnux.rapid.gateway.ctp.FtdcParams;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.mercury.common.thread.Sleep;
import io.mercury.common.thread.Threads;
import io.mercury.transport.attr.Topics;
import io.mercury.transport.zmq.ZmqConfigurator;
import io.mercury.transport.zmq.ZmqSubscriber;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static io.cygnux.rapid.core.adapter.Adapter.subscribePath;
import static io.mercury.serialization.json.JsonReader.toObject;

@Service
public class CtpAdapterService {

    private static final Logger log = Log4j2LoggerFactory.getLogger(CtpAdapterService.class);

    @Value("${adaptor.running.mode:FULL}")
    private String mode;

    @Resource(name = "ipcInboundHandler")
    private SharedEventHandler inboundHandler;

    @Resource(name = "ipcFtdcHandler")
    private FtdcRspHandler ftdcRspHandler;

    @Resource(name = "account")
    private Account account;

    @Resource(name = "params")
    private FtdcParams params;

    private CtpAdapter adapter;

    private ZmqSubscriber adapterSubscriber;

    @PostConstruct
    public void init() {
        this.adapter = CtpAdapter.builder(account, params)
                .asyncMode()
                .setRunningMode(AdapterRunningMode.valueOf(mode))
                .build(inboundHandler, ftdcRspHandler);
        this.adapterSubscriber = ZmqConfigurator.ipc(subscribePath)
                .createSubscriber(Topics.with(adapter.subscribeTopic()),
                        this::handleSubscribeMsg);
        start();
    }

    /**
     * @param bytesTopic byte[]
     * @param bytesBody  byte[]
     */
    private void handleSubscribeMsg(byte[] bytesTopic, byte[] bytesBody) {
        String topic = new String(bytesTopic);
        String body = new String(bytesBody);
        log.info("Adaptor received message, topic -> {}, body -> {}", topic, body);
        switch (SentEventType.valueOf(topic)) {
            case SUBSCRIBE_MARKET_DATA -> adapter
                    .subscribeMarketData(toObject(body, SubscribeMarketData.class));
            case NEW_ORDER -> adapter
                    .newOrder(toObject(body, NewOrder.class));
            case CANCEL_ORDER -> adapter
                    .cancelOrder(toObject(body, CancelOrder.class));
            case QUERY_ORDER -> adapter
                    .queryOrder(toObject(body, QueryOrder.class));
            case QUERY_POSITIONS -> adapter
                    .queryPosition(toObject(body, QueryPosition.class));
            case QUERY_BALANCE -> adapter
                    .queryBalance(toObject(body, QueryBalance.class));
            default -> log
                    .warn("Adaptor received Unprocessable message, topic -> {}, body -> {}", topic, body);
        }
    }

    private void start() {
        try {
            adapter.startup();
            log.info("Adaptor -> {} STARTED, wait 2 seconds.", adapter.getAdapterId());
            Sleep.millis(2000);
            SubscribeMarketData subscribeMarketData = new SubscribeMarketData()
                    .setType(MarketDataType.FAST)
                    .setInstrumentCodes(InstrumentKeeper
                            .getInstrumentByCode("IF2412", "CF501", "rb2501", "ru2501", "i2501")
                            .stream()
                            .map(Instrument::getInstrumentCode)
                            .toList())
                    .setReceivedAddr("");
            adapter.subscribeMarketData(subscribeMarketData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Threads.startNewThread("adaptor-subscriber-worker", adapterSubscriber::subscribe);
    }

    private void stop() {
        try {
            adapter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
