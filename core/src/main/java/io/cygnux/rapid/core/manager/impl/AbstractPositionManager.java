package io.cygnux.rapid.core.manager.impl;

import io.cygnux.rapid.core.manager.PositionManager;
import io.cygnux.rapid.core.position.AccountPosition;
import io.cygnux.rapid.core.position.PositionProducer;
import io.mercury.common.collections.MutableMaps;
import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public abstract class AbstractPositionManager implements PositionManager {

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
