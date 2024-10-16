package io.rapid.engine;

import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.mercury.common.state.StartupException;
import io.mercury.common.thread.ThreadSupport;
import io.mercury.serialization.json.JsonParser;
import io.mercury.serialization.json.JsonRecord;
import io.mercury.transport.zmq.ZmqConfigurator;
import io.mercury.transport.zmq.ZmqSubscriber;
import io.rapid.core.adaptor.Adaptor;
import io.rapid.core.event.InboundEventFeeder;
import io.rapid.core.event.InboundEventType;
import io.rapid.core.event.container.InboundEventLoop;
import io.rapid.core.event.inbound.AdaptorReport;
import io.rapid.core.event.inbound.BalanceReport;
import io.rapid.core.event.inbound.DepthMarketData;
import io.rapid.core.event.inbound.MarketDataSubscribeReport;
import io.rapid.core.event.inbound.OrderReport;
import io.rapid.core.event.inbound.PositionsReport;
import io.rapid.core.event.inbound.RawMarketData;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class InboundEventFeederService implements InboundEventFeeder {

    private static final Logger log = Log4j2LoggerFactory.getLogger(InboundEventFeederService.class);

    private final ZmqSubscriber subscriber = ZmqConfigurator
            .ipc(Adaptor.publishPath()).createSubscriber(this::handleMsg);

    private InboundEventLoop loop;

    @Override
    public void startup() throws StartupException {
        ThreadSupport.startNewMaxPriorityThread("InboundEventFeeder", subscriber::subscribe);
    }

    private void handleMsg(byte[] topicBytes, byte[] bodyBytes) {
        String topic = new String(topicBytes);
        String body = new String(bodyBytes);
        JsonRecord record = JsonParser.toObject(body, JsonRecord.class);
        long epochTime = record.getEpochTime();
        String title = record.getTitle();
        switch (InboundEventType.valueOf(title)) {
            case RawMarketData -> loop.put(record.getRecordWith(RawMarketData.class));
            case DepthMarketData -> loop.put(record.getRecordWith(DepthMarketData.class));
            case OrderReport -> loop.put(record.getRecordWith(OrderReport.class));
            case PositionsReport -> loop.put(record.getRecordWith(PositionsReport.class));
            case BalanceReport -> loop.put(record.getRecordWith(BalanceReport.class));
            case AdaptorReport -> loop.put(record.getRecordWith(AdaptorReport.class));
            case MarketDataSubscribeReport -> loop.put(record.getRecordWith(MarketDataSubscribeReport.class));
            case Invalid -> log.error("Invalid event received -> {}", record);
        }
//        log.info("Received -> epochMicros == {}, title == {}, From topic -> {}", epochTime, title, topic);
//        log.info("Received -> record == {}", record.getRecord());
    }


    @Override
    public void shutdown() throws IOException {
        subscriber.close();
    }

    @Override
    public void addEventLoop(InboundEventLoop loop) {
        this.loop = loop;
    }

}
