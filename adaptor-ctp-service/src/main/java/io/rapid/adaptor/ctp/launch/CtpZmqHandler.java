package io.rapid.adaptor.ctp.launch;

import com.typesafe.config.Config;
import io.mercury.transport.zmq.ZmqComponent;
import io.rapid.adaptor.ctp.gateway.event.FtdcRspEvent;
import io.mercury.common.collections.queue.Queue;
import io.mercury.common.concurrent.queue.SingleConsumerQueueWithJCT;
import io.mercury.common.functional.Handler;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.mercury.common.thread.RunnableComponent.StartMode;
import io.mercury.serialization.json.JsonWriter;
import io.mercury.transport.zmq.ZmqPublisher;
import org.slf4j.Logger;

import java.io.Closeable;
import java.io.IOException;

public class CtpZmqHandler implements Closeable, Handler<FtdcRspEvent> {

    private static final Logger log = Log4j2LoggerFactory.getLogger(CtpZmqHandler.class);

    private final ZmqPublisher<String> publisher;

    private final Queue<FtdcRspEvent> queue;

    public CtpZmqHandler(Config config) {
        String topic = config.getString("zmq.topic");
        log.info("config zmq.topic == [{}]", topic);
        this.publisher = ZmqComponent.config(config).createPublisherWithString(topic);
        this.queue = SingleConsumerQueueWithJCT.mpscQueue("CtpZmqHandler-Queue").capacity(32)
                .startMode(StartMode.auto()).process(msg -> {
                    String json = JsonWriter.toJson(msg);
                    log.info("Received msg -> {}", json);
                    publisher.publish(json);
                });
    }

    @Override
    public void close() throws IOException {
        while (!queue.isEmpty())
            ;
        publisher.close();
    }

    @Override
    public void handle(FtdcRspEvent msg) {
        queue.enqueue(msg);
    }

}
