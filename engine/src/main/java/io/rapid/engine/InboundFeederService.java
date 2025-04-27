package io.rapid.engine;

import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.mercury.common.state.StartupException;
import io.mercury.serialization.json.JsonParser;
import io.mercury.serialization.json.JsonObjectExt;
import io.mercury.transport.zmq.ZmqConfigurator;
import io.mercury.transport.zmq.ZmqSubscriber;
import io.rapid.core.adaptor.Adaptor;
import io.rapid.core.event.InboundEventType;
import io.rapid.core.event.InboundFeeder;
import io.rapid.core.event.InboundEventLoop;
import io.rapid.core.event.inbound.AdaptorReport;
import io.rapid.core.event.inbound.BalanceReport;
import io.rapid.core.event.inbound.DepthMarketData;
import io.rapid.core.event.inbound.InstrumentStatusReport;
import io.rapid.core.event.inbound.OrderReport;
import io.rapid.core.event.inbound.PositionsReport;
import io.rapid.core.event.inbound.RawMarketData;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static io.mercury.common.thread.Threads.startNewMaxPriorityThread;

@Service
public class InboundFeederService implements InboundFeeder {

    private static final Logger log = Log4j2LoggerFactory.getLogger(InboundFeederService.class);

    private final ZmqSubscriber subscriber = ZmqConfigurator
            .ipc(Adaptor.publishPath())
            .createSubscriber(this::handleMsg);

    private InboundEventLoop loop;

    @Override
    public void startup() throws StartupException {
        startNewMaxPriorityThread("InboundEventFeeder", subscriber::subscribe);
    }

    private void handleMsg(byte[] topicBytes, byte[] bodyBytes) {
        String topic = new String(topicBytes);
        String body = new String(bodyBytes);
        JsonObjectExt record = JsonParser.toObject(body, JsonObjectExt.class);
        long epochTime = record.getEpochTime();
        String title = record.getTitle();
        switch (InboundEventType.valueOf(title)) {
            case RAW_MARKET_DATA -> loop.put(record.getWith(RawMarketData.class));
            case DEPTH_MARKET_DATA -> loop.put(record.getWith(DepthMarketData.class));
            case ORDER_REPORT -> loop.put(record.getWith(OrderReport.class));
            case POSITIONS_REPORT -> loop.put(record.getWith(PositionsReport.class));
            case BALANCE_REPORT -> loop.put(record.getWith(BalanceReport.class));
            case ADAPTOR_STATUS_REPORT -> loop.put(record.getWith(AdaptorReport.class));
            case INSTRUMENT_STATUS_REPORT -> loop.put(record.getWith(InstrumentStatusReport.class));
            case INVALID -> log.error("Invalid event received -> {}", record);
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
