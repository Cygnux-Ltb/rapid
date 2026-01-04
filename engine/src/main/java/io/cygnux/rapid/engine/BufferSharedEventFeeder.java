package io.cygnux.rapid.engine;

import io.cygnux.rapid.core.adapter.Adapter;
import io.cygnux.rapid.core.event.SharedEventFeeder;
import io.cygnux.rapid.core.event.SharedEventType;
import io.cygnux.rapid.core.event.SharedEventbus;
import io.cygnux.rapid.core.event.received.AdapterStatusReport;
import io.cygnux.rapid.core.event.received.BalanceReport;
import io.cygnux.rapid.core.event.received.DepthMarketData;
import io.cygnux.rapid.core.event.received.FastMarketData;
import io.cygnux.rapid.core.event.received.InstrumentStatusReport;
import io.cygnux.rapid.core.event.received.OrderReport;
import io.cygnux.rapid.core.event.received.PositionsReport;
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
public class BufferSharedEventFeeder implements SharedEventFeeder {

    private static final Logger log = Log4j2LoggerFactory.getLogger(BufferSharedEventFeeder.class);

    private final ZmqSubscriber subscriber = ZmqConfigurator
            .ipc(Adapter.publishPath)
            .createSubscriber(this::handleMsg);

    private SharedEventbus eventbus;

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
            case FAST_MARKET_DATA -> eventbus.put(record.getWith(FastMarketData.class));
            case DEPTH_MARKET_DATA -> eventbus.put(record.getWith(DepthMarketData.class));
            case ORDER_REPORT -> eventbus.put(record.getWith(OrderReport.class));
            case POSITIONS_REPORT -> eventbus.put(record.getWith(PositionsReport.class));
            case BALANCE_REPORT -> eventbus.put(record.getWith(BalanceReport.class));
            case ADAPTER_STATUS_REPORT -> eventbus.put(record.getWith(AdapterStatusReport.class));
            case INSTRUMENT_STATUS_REPORT -> eventbus.put(record.getWith(InstrumentStatusReport.class));
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
    public void addEventbus(SharedEventbus eventbus) {
        this.eventbus = eventbus;
    }

}
