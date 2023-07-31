package io.cygnuxltb.jcts.core.handler;

import io.cygnuxltb.jcts.core.adaptor.Adaptor;
import io.cygnuxltb.jcts.core.instrument.Instrument;
import io.cygnuxltb.jcts.core.mkd.MarketData;
import io.cygnuxltb.jcts.core.mkd.impl.BasicMarketData;
import io.cygnuxltb.jcts.core.ser.event.AdaptorEvent;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import org.slf4j.Logger;

import javax.annotation.Nonnull;
import java.io.Closeable;
import java.io.IOException;

/**
 * 行情记录器接口
 *
 * @param <M>
 * @author yellow013
 */
public interface MarketDataRecorder<M extends MarketData> extends MarketDataHandler<M>, Closeable {

    /**
     * MarketDataRecorder base implements
     *
     * @param <M>
     * @author yellow013
     */
    abstract class BaseMarketDataRecorder<M extends MarketData>
            implements MarketDataRecorder<M>, AdaptorEventHandler {

        private static final Logger log = Log4j2LoggerFactory.getLogger(BaseMarketDataRecorder.class);

        protected final Instrument[] instruments;

        protected final Adaptor adaptor;

        protected BaseMarketDataRecorder(@Nonnull Adaptor adaptor,
                                         @Nonnull Instrument... instruments) {
            this.adaptor = adaptor;
            this.instruments = instruments;
        }

        @Override
        public void onAdaptorEvent(@Nonnull AdaptorEvent event) {
            log.info("Received event -> {}", event);
            switch (event.getStatus()) {
                case MD_ENABLE -> adaptor.subscribeMarketData(instruments);
                case MD_DISABLE -> log.info("Adaptor -> {} market data is disable", adaptor.getAdaptorId());
                default -> log.warn("Event no processing, AdaptorEvent -> {}", event);
            }
        }


    }

    /**
     * @author yellow013
     */
    class LoggerMarketDataRecorder extends BaseMarketDataRecorder<BasicMarketData> {

        private final Logger log = Log4j2LoggerFactory.getLogger(getClass());

        public LoggerMarketDataRecorder(Adaptor adaptor, Instrument... instruments) {
            super(adaptor, instruments);
        }

        @Override
        public void onMarketData(@Nonnull BasicMarketData marketData) {
            log.info("LoggerMarketDataRecorder written -> {}", marketData);
        }

        @Override
        public void close() throws IOException {
            log.info("LoggerMarketDataRecorder is closed");
        }

    }

}
