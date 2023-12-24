package io.rapid.core.adaptor;

import io.rapid.core.account.Account;
import io.rapid.core.instrument.InstrumentKeeper;
import io.mercury.common.annotation.AbstractFunction;
import io.mercury.common.fsm.EnableableComponent;
import io.mercury.common.lang.Asserter;
import io.mercury.common.lang.exception.ComponentStartupException;

import javax.annotation.Nonnull;
import java.io.IOException;

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
    protected ConnectionMode mode = ConnectionMode.ALL;

    /**
     * @param prefix  String
     * @param account Account
     */
    protected AbstractAdaptor(@Nonnull String prefix, @Nonnull Account account) {
        Asserter.nonNull(prefix, "prefix");
        Asserter.nonNull(account, "account");
        this.account = account;
        this.adaptorId = STR."\{prefix}[\{account.getBrokerName()}:\{account.getInvestorId()}]";
        AdaptorStorage.putAdaptor(this);
    }

    @Nonnull
    @Override
    public String getAdaptorId() {
        return adaptorId;
    }

    @Nonnull
    @Override
    public Account account() {
        return account;
    }

    @Override
    public boolean startup() throws IOException, IllegalStateException, ComponentStartupException {
        if (!InstrumentKeeper.isInitialized())
            throw new IllegalStateException("Instrument Keeper uninitialized");
        try {
            return startup0();
        } catch (IOException ioe) {
            throw ioe;
        } catch (Exception e) {
            throw new ComponentStartupException(STR."\{adaptorId} -> \{e.getMessage()}", e);
        }
    }

    @AbstractFunction
    protected abstract boolean startup0() throws Exception;

}
