package io.rapid.core.position;

import io.mercury.common.collections.MutableMaps;
import io.rapid.core.account.AccountPosition;
import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public abstract class BasePositionManager<P extends Position> implements PositionManager<P> {

    private final MutableIntObjectMap<AccountPosition<P>> accountPositionMap = MutableMaps.newIntObjectMap();

    private final PositionProducer<P> producer;

    protected BasePositionManager(@Nonnull PositionProducer<P> producer) {
        this.producer = producer;
    }

    @Override
    public AccountPosition<P> getAccountPosition(int accountId) {
        return accountPositionMap.getIfAbsentPut(accountId,
                () -> new AccountPosition<>(accountId, producer));
    }

}
