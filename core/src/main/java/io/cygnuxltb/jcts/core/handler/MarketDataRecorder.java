package io.cygnuxltb.jcts.core.handler;

import io.cygnuxltb.jcts.core.mkd.MarketData;
import io.cygnuxltb.jcts.core.mkd.impl.BasicMarketData;
import io.cygnuxltb.jcts.core.serialization.avro.enums.AvroAdaptorStatus;
import io.cygnuxltb.jcts.core.serialization.avro.receive.AvroAdaptorEvent;
import io.horizon.market.instrument.Instrument;
import io.horizon.trader.adaptor.Adaptor;
import io.horizon.trader.handler.AdaptorEventHandler;
import io.horizon.trader.serialization.avro.receive.AvroAdaptorEvent;
import io.horizon.trader.serialization.avro.receive.AvroOrderEvent;
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
    abstract class BaseMarketDataRecorder<M extends MarketData> implements MarketDataRecorder<M> , AdaptorEventHandler {

        private static final Logger log = Log4j2LoggerFactory.getLogger(BaseMarketDataRecorder.class);

        protected final Instrument[] instruments;

        protected final Adaptor adaptor;

        protected BaseMarketDataRecorder(@Nonnull Adaptor adaptor,
                                         @Nonnull Instrument... instruments) {
            this.adaptor = adaptor;
            this.instruments = instruments;
        }

        @Override
        public void onAdaptorEvent(@Nonnull AvroAdaptorEvent event) {
            log.info("Received event -> {}", event);
            switch (event.getStatus()) {
                case AvroAdaptorStatus.MD_ENABLE -> adaptor.subscribeMarketData(instruments);
                case AvroAdaptorStatus.MD_DISABLE -> log.info("Adaptor -> {} market data is disable", adaptor.getAdaptorId());
                default -> log.warn("Event no processing, AdaptorEvent -> {}", event);
            }
        }



    }

    /**
     * @author yellow013
     */
    class LoggerMarketDataRecorder extends BaseMarketDataRecorder<BasicMarketData> {

        private final Logger log = Log4j2LoggerFactory.getLogger(getClass());

        public LoggerMarketDataRecorder(Adaptor adaptor,  Instrument... instruments) {
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
