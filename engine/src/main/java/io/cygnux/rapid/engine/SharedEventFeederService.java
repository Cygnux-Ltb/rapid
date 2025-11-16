package io.cygnux.rapid.engine;

import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.mercury.common.state.StartupException;
import io.mercury.serialization.json.JsonReader;
import io.mercury.serialization.json.JsonObjectExt;
import io.mercury.transport.zmq.ZmqConfigurator;
import io.mercury.transport.zmq.ZmqSubscriber;
import io.cygnux.rapid.core.adaptor.Adaptor;
import io.cygnux.rapid.core.shared.SharedEventType;
import io.cygnux.rapid.core.shared.SharedEventFeeder;
import io.cygnux.rapid.core.shared.SharedEventbus;
import io.cygnux.rapid.core.shared.event.AdaptorReport;
import io.cygnux.rapid.core.shared.event.BalanceReport;
import io.cygnux.rapid.core.shared.event.DepthMarketData;
import io.cygnux.rapid.core.shared.event.InstrumentStatusReport;
import io.cygnux.rapid.core.shared.event.OrderReport;
import io.cygnux.rapid.core.shared.event.PositionsReport;
import io.cygnux.rapid.core.shared.event.FastMarketData;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static io.mercury.common.thread.Threads.startNewMaxPriorityThread;

@Service
public class SharedEventFeederService implements SharedEventFeeder {

    private static final Logger log = Log4j2LoggerFactory.getLogger(SharedEventFeederService.class);

    private final ZmqSubscriber subscriber = ZmqConfigurator
            .ipc(Adaptor.publishPath())
            .createSubscriber(this::handleMsg);

    private SharedEventbus loop;

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
    public void addEventbus(SharedEventbus eventbus) {
        this.loop = eventbus;
    }

}
