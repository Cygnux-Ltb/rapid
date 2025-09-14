package io.cygnux.rapid.engine;

import io.cygnux.rapid.core.adaptor.Adaptor;
import io.cygnux.rapid.core.stream.StreamEventFeeder;
import io.cygnux.rapid.core.stream.SharedEventType;
import io.cygnux.rapid.core.stream.StreamEventbus;
import io.cygnux.rapid.core.stream.event.AdaptorReport;
import io.cygnux.rapid.core.stream.event.BalanceReport;
import io.cygnux.rapid.core.stream.event.DepthMarketData;
import io.cygnux.rapid.core.stream.event.FastMarketData;
import io.cygnux.rapid.core.stream.event.InstrumentStatusReport;
import io.cygnux.rapid.core.stream.event.OrderReport;
import io.cygnux.rapid.core.stream.event.PositionsReport;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.mercury.common.state.StartupException;
import io.mercury.serialization.json.JsonObjectExt;
import io.mercury.serialization.json.JsonReader;
import io.mercury.transport.zmq.ZmqConfigurator;
import io.mercury.transport.zmq.ZmqSubscriber;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static io.mercury.common.thread.Threads.startNewMaxPriorityThread;

@Service
public class BufferStreamEventFeeder implements StreamEventFeeder {

    private static final Logger log = Log4j2LoggerFactory.getLogger(BufferStreamEventFeeder.class);

    private final ZmqSubscriber subscriber = ZmqConfigurator
            .ipc(Adaptor.publishPath())
            .createSubscriber(this::handleMsg);

    private StreamEventbus loop;

    @Override
    public void startup() throws StartupException {
        startNewMaxPriorityThread("InboundEventFeeder", subscriber::subscribe);
    }

    private void handleMsg(byte[] topicBytes, byte[] bodyBytes) {
        String topic = new String(topicBytes);
        String body = new String(bodyBytes);
        JsonObjectExt record = JsonReader.toObject(body, JsonObjectExt.class);
        long epochTime = record.getEpochTime();
        String title = record.getTitle();
        switch (SharedEventType.valueOf(title)) {
            case FAST_MARKET_DATA -> loop.put(record.getWith(FastMarketData.class));
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
    public void addEventbus(StreamEventbus eventbus) {
        this.loop = eventbus;
    }

}
