package io.rapid.engine.strategy;

import io.mercury.common.param.Params;
import io.rapid.core.account.SubAccount;
import io.rapid.core.instrument.Instrument;
import org.slf4j.Logger;

import javax.annotation.Nonnull;

import static io.mercury.common.log4j2.Log4j2LoggerFactory.getLogger;

public abstract class SingleInstrumentStrategy extends BaseStrategy {

    private static final Logger log = getLogger(SingleInstrumentStrategy.class);

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


}
