package io.cygnux.rapid.core.types.account;


import io.mercury.common.collections.MutableMaps;
import lombok.Getter;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;

import java.util.stream.Stream;

public class SubAccountMapping {

    @Getter
    private final int subAccountId;

    @Getter
    private final MutableIntObjectMap<Account> accountMap = MutableMaps.newIntObjectMap();

    SubAccountMapping(int subAccountId) {
        this.subAccountId = subAccountId;
    }

    void saveAccount(Account account) {
        accountMap.put(account.getAccountId(), account);
    }

    public Account getAccount(int accountId) {
        return accountMap.get(accountId);
    }

    public void each(Procedure<Account> procedure) {
        accountMap.each(procedure);
    }

    public Stream<Account> stream() {
        return accountMap.values().stream();
    }

    @Override
    public String toString() {
        return "{\"subAccountId\" : " + subAccountId
                + ", \"accountIds\" : [" + accountMap.keySet()
                + "]}";
    }

}
