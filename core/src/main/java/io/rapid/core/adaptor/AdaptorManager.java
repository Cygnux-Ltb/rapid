package io.rapid.core.adaptor;

import io.rapid.core.account.Account;

import javax.annotation.Nonnull;

public interface AdaptorManager {

    void putAdaptor(@Nonnull Adaptor adaptor);

    Adaptor getAdaptor(int accountId);

    default Adaptor getAdaptor(@Nonnull Account account) {
        return getAdaptor(account.getAccountId());
    }

}
