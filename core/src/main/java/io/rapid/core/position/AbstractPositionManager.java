package io.rapid.core.position;

import io.mercury.common.collections.MutableMaps;
import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public abstract non-sealed class AbstractPositionManager implements PositionManager {

    private final MutableIntObjectMap<AccountPosition> accountPositionMap = MutableMaps.newIntObjectMap();

    private final PositionProducer producer;

    protected AbstractPositionManager(@Nonnull PositionProducer producer) {
        this.producer = producer;
    }

    @Override
    public AccountPosition acquireAccountPosition(int accountId) {
        return accountPositionMap.getIfAbsentPut(accountId,
                // 当前MAP不存在则创建
                () -> new AccountPosition(accountId, producer));
    }

}
