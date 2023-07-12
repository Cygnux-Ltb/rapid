package io.cygnuxltb.jcts.engine.strategy;

import io.cygnuxltb.jcts.core.account.SubAccount;
import io.cygnuxltb.jcts.core.adaptor.Adaptor;
import io.cygnuxltb.jcts.core.instrument.Instrument;
import io.cygnuxltb.jcts.core.mkd.MarketData;
import io.cygnuxltb.jcts.core.serialization.avro.event.AvAdaptorEvent;
import io.cygnuxltb.jcts.core.strategy.Strategy;
import io.mercury.common.datetime.EpochTime;
import io.mercury.common.lang.Asserter;
import io.mercury.common.param.ParamKey;
import io.mercury.common.param.Params;
import org.eclipse.collections.api.map.primitive.ImmutableIntObjectMap;
import org.slf4j.Logger;

import javax.annotation.Nonnull;

import static io.mercury.common.collections.ImmutableMaps.getIntObjectMapFactory;
import static io.mercury.common.log4j2.Log4j2LoggerFactory.getLogger;

public abstract class SingleInstrumentStrategy<M extends MarketData, K extends ParamKey>
        extends BaseStrategy<M, K> {

    private static final Logger log = getLogger(SingleInstrumentStrategy.class);

    private Adaptor adaptor;

    /**
     * @param strategyId   int
     * @param strategyName String
     * @param subAccount   SubAccount
     * @param instrument   Instrument
     */
    protected SingleInstrumentStrategy(int strategyId,
                                       @Nonnull String strategyName,
                                       @Nonnull SubAccount subAccount,
                                       @Nonnull Instrument instrument) {
        this(strategyId, strategyName, subAccount, null, instrument);
    }

    /**
     * @param strategyId   int
     * @param strategyName String
     * @param subAccount   SubAccount
     * @param params       Params<K>
     * @param instrument   Instrument
     */
    protected SingleInstrumentStrategy(int strategyId, @Nonnull String strategyName, SubAccount subAccount,
                                       @Nonnull Params<K> params, Instrument instrument) {
        super(strategyId, strategyName, subAccount, params);
        this.instrument = instrument;
        this.instruments = getIntObjectMapFactory().of(instrument.getInstrumentId(), instrument);
    }


    @Override
    @Nonnull
    public ImmutableIntObjectMap<Instrument> getInstruments() {
        return instruments;
    }

    public Strategy<M> addAdaptor(@Nonnull Adaptor adaptor) {
        Asserter.nonNull(adaptor, "adaptor");
        log.info("added adaptor, strategyId -> {}, strategyName -> {}, adaptorId -> {}", id, name,
                adaptor.getAdaptorId());
        this.adaptor = adaptor;
        return this;
    }

    protected Adaptor getAdaptor(@Nonnull Instrument instrument) {
        return adaptor;
    }

    @Override
    public void onAdaptorEvent(@Nonnull AvAdaptorEvent event) {
        log.info("{} :: On adaptor status callback, adaptorId==[{}], status==[{}]", getName(),
                event.getAdaptorId(), event.getStatus());
        switch (event.getStatus()) {
            case MD_ENABLE -> {
                log.info("{} :: Handle adaptor MdEnable, adaptorId==[{}]", getName(), event.getAdaptorId());
                adaptor.subscribeMarketData(instrument);
                log.info("{} :: Call subscribeMarketData, instrument -> {}", getName(), instrument);
            }
            case TRADER_ENABLE -> {
                log.info("{} :: Handle adaptor TdEnable, adaptorId==[{}]", getName(), event.getAdaptorId());
                // TODO
//			adaptor.queryOrder(null);
//			log.info("{} :: Call queryOrder, adaptorId==[{}], account is default", getStrategyName(),
//					event.getAdaptorId());
                adaptor.queryPositions(queryPositions.setExchangeCode(instrument.getExchangeCode())
                        .setInstrumentCode(instrument.getInstrumentCode()).setGenerateTime(EpochTime.getEpochMillis()));
                log.info("{} :: Call queryPositions, adaptorId==[{}], account is default", getName(),
                        event.getAdaptorId());
                adaptor.queryBalance(queryBalance.setGenerateTime(EpochTime.getEpochMillis()));
                log.info("{} :: Call queryBalance, adaptorId==[{}], account is default", getName(),
                        event.getAdaptorId());
            }
            default -> log.warn("{} unhandled event received {}", getName(), event);
        }
    }

}
