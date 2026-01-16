package io.cygnux.rapid.engine.strategy;

import io.cygnux.rapid.core.types.account.SubAccount;
import io.cygnux.rapid.core.types.instrument.Instrument;
import org.slf4j.Logger;

import static io.mercury.common.log4j2.Log4j2LoggerFactory.getLogger;

public abstract class SingleInstrumentStrategy extends AbstractStrategy {

    private static final Logger log = getLogger(SingleInstrumentStrategy.class);

    protected Instrument instrument;

    /**
     * @param strategyId   int
     * @param strategyName String
     * @param subAccount   SubAccount
     * @param instrument   Instrument
     */
    protected SingleInstrumentStrategy(int strategyId, String strategyName,
                                       SubAccount subAccount, Instrument instrument) {
        super(strategyId, strategyName, subAccount, instrument);
        this.instrument = instrument;
    }

}
