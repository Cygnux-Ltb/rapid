package io.rapid.engine.strategy;

import io.mercury.common.datetime.EpochTime;
import io.mercury.common.lang.Asserter;
import io.mercury.common.param.Params;
import io.rapid.core.account.SubAccount;
import io.rapid.core.upstream.TraderAdaptor;
import io.rapid.core.instrument.Instrument;
import io.rapid.core.serializable.avro.inbound.AdaptorEvent;
import io.rapid.core.strategy.Strategy;
import org.slf4j.Logger;

import javax.annotation.Nonnull;

import static io.mercury.common.log4j2.Log4j2LoggerFactory.getLogger;

public abstract class SingleInstrumentStrategy extends BaseStrategy {

    private static final Logger log = getLogger(SingleInstrumentStrategy.class);

    protected TraderAdaptor adaptor;

    protected Instrument instrument;

    /**
     * @param strategyId   int
     * @param strategyName String
     * @param subAccount   SubAccount
     * @param params       Params<K>
     * @param instrument   Instrument
     */
    protected SingleInstrumentStrategy(int strategyId, @Nonnull String strategyName, SubAccount subAccount,
                                       @Nonnull Params params, Instrument instrument) {
        super(strategyId, strategyName, subAccount, params, instrument);
        this.instrument = instrument;
    }


    public Strategy addAdaptor(@Nonnull TraderAdaptor adaptor) {
        Asserter.nonNull(adaptor, "adaptor");
        log.info("added adaptor, strategyId -> {}, strategyName -> {}, investorId -> {}",
                strategyId, strategyName, adaptor.getBoundAccount().getInvestorId());
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
