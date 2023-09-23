package io.cygnuxltb.jcts.engine.strategy;

import io.cygnuxltb.jcts.core.account.SubAccount;
import io.cygnuxltb.jcts.core.adaptor.TraderAdaptor;
import io.cygnuxltb.jcts.core.instrument.Instrument;
import io.cygnuxltb.jcts.core.ser.event.AdaptorEvent;
import io.cygnuxltb.jcts.core.strategy.Strategy;
import io.mercury.common.collections.ImmutableSets;
import io.mercury.common.datetime.EpochTime;
import io.mercury.common.lang.Asserter;
import io.mercury.common.param.ParamKey;
import io.mercury.common.param.Params;
import org.eclipse.collections.api.set.ImmutableSet;
import org.slf4j.Logger;

import javax.annotation.Nonnull;

import static io.mercury.common.log4j2.Log4j2LoggerFactory.getLogger;

public abstract class SingleInstrumentStrategy<K extends ParamKey>
        extends BaseStrategy<K> {

    private static final Logger log = getLogger(SingleInstrumentStrategy.class);

    protected TraderAdaptor adaptor;

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
        this.instruments = ImmutableSets.fromStream();
    }


    @Override
    @Nonnull
    public ImmutableSet<Instrument> getInstruments() {
        return instruments;
    }


    public Strategy addAdaptor(@Nonnull TraderAdaptor adaptor) {
        Asserter.nonNull(adaptor, "adaptor");
        log.info("added adaptor, strategyId -> {}, strategyName -> {}, investorId -> {}",
                id, name, adaptor.account().getInvestorId());
        this.adaptor = adaptor;
        return this;
    }


    @Override
    public void onAdaptorEvent(@Nonnull AdaptorEvent event) {
        log.info("{} :: On adaptor status callback, adaptorId==[{}], status==[{}]", getStrategyName(),
                event.getAdaptorId(), event.getStatus());
        switch (event.getStatus()) {
            case MD_ENABLE -> {
                log.info("{} :: Handle adaptor MdEnable, adaptorId==[{}]", getStrategyName(), event.getAdaptorId());
                //agent.subscribeMarketData(instrument);
                log.info("{} :: Call subscribeMarketData, instrument -> {}", getStrategyName(), instrument);
            }
            case TRADER_ENABLE -> {
                log.info("{} :: Handle adaptor TdEnable, adaptorId==[{}]", getStrategyName(), event.getAdaptorId());
                // TODO
//			adaptor.queryOrder(null);
//			log.info("{} :: Call queryOrder, adaptorId==[{}], account is default", getStrategyName(),
//					event.getAdaptorId());
                adaptor.queryPositions(queryPositions.setExchangeCode(instrument.getExchangeCode())
                        .setInstrumentCode(instrument.getInstrumentCode()).setGenerateTime(EpochTime.getEpochMillis()));
                log.info("{} :: Call queryPositions, adaptorId==[{}], account is default", getStrategyName(),
                        event.getAdaptorId());
                adaptor.queryBalance(queryBalance.setGenerateTime(EpochTime.getEpochMillis()));
                log.info("{} :: Call queryBalance, adaptorId==[{}], account is default", getStrategyName(),
                        event.getAdaptorId());
            }
            default -> log.warn("{} unhandled event received {}", getStrategyName(), event);
        }
    }

}
