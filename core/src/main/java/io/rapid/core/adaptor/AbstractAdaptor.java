package io.rapid.core.upstream;

import io.mercury.common.annotation.AbstractFunction;
import io.mercury.common.state.EnableableComponent;
import io.mercury.common.state.StartupException;
import io.rapid.core.account.Account;
import io.rapid.core.instrument.InstrumentKeeper;

import javax.annotation.Nonnull;
import java.io.IOException;

import static io.mercury.common.lang.Asserter.nonNull;

/**
 * @author yellow013
 */
public abstract class AbstractAdaptor extends EnableableComponent implements Adaptor {

    /**
     * Adaptor标识
     */
    protected final String adaptorId;

    /**
     * 托管投资账户
     */
    protected final Account account;

    /**
     * Normal
     */
    protected AdaptorRunningMode runningMode = AdaptorRunningMode.FULL;

    /**
     * @param account Account
     */
    protected AbstractAdaptor(@Nonnull Account account) {
        nonNull(account, "account");
        this.account = account;
        this.adaptorId = this.getClass().getSimpleName() + "-[" + account.getBrokerName() + ":" + account.getInvestorId() + "]";
    }

    @Nonnull
    @Override
    public String getAdaptorId() {
        return adaptorId;
    }

    @Nonnull
    @Override
    public Account getBoundAccount() {
        return account;
    }

    @Override
    public boolean startup() throws IOException, IllegalStateException, StartupException {
        if (!InstrumentKeeper.isInitialized())
            throw new IllegalStateException("Instrument Keeper uninitialized");
        try {
            return startup0();
        } catch (IOException ioe) {
            throw ioe;
        } catch (Exception e) {
            throw new StartupException(adaptorId + "->" + e.getMessage(), e);
        }
    }

    @AbstractFunction
    protected abstract boolean startup0() throws Exception;

}
