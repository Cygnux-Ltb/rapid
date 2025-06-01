package io.cygnux.rapid.engine.strategy;

import io.mercury.common.collections.ImmutableMaps;
import io.mercury.common.param.Params;
import io.cygnux.rapid.core.account.SubAccount;
import io.cygnux.rapid.core.instrument.Instrument;
import org.slf4j.Logger;

import java.util.Collections;

import static io.mercury.common.log4j2.Log4j2LoggerFactory.getLogger;

public abstract class SingleInstrumentStrategy extends AbstractStrategy {

    private static final Logger log = getLogger(SingleInstrumentStrategy.class);

    protected Instrument instrument;

    /**
     * @param strategyId   int
     * @param strategyName String
     * @param subAccount   SubAccount
     * @param params       Params<K>
     * @param instrument   Instrument
     */
    protected SingleInstrumentStrategy(int strategyId, String strategyName,
                                       SubAccount subAccount, Params params, Instrument instrument) {
        super(strategyId, strategyName, subAccount, params,
                ImmutableMaps.newImmutableIntMap(Collections.singletonList(instrument), Instrument::getInstrumentId)
        );
        this.instrument = instrument;
    }


}
