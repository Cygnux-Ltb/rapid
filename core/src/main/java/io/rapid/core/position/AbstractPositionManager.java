package io.rapid.core.position;

import io.rapid.core.account.AccountPosition;
import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

import static io.mercury.common.collections.MutableMaps.newIntObjectHashMap;

@NotThreadSafe
public abstract class AbstractPositionManager<P extends Position> implements PositionManager<P> {

    private final MutableIntObjectMap<AccountPosition<P>> accountPositionMap = newIntObjectHashMap();

    private final PositionProducer<P> producer;

    protected AbstractPositionManager(@Nonnull PositionProducer<P> producer) {
        this.producer = producer;
    }

    @Override
    public AccountPosition<P> getAccountPosition(int accountId) {
        return accountPositionMap.getIfAbsentPut(accountId,
                () -> new AccountPosition<>(accountId, producer));
    }

}
