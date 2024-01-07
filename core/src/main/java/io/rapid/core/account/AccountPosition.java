package io.rapid.core.account;

import io.rapid.core.instrument.Instrument;
import io.rapid.core.position.Position;
import io.rapid.core.position.PositionProducer;
import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

import static io.mercury.common.collections.MutableMaps.newIntObjectHashMap;

/**
 * 管理一个实际账户的持仓集合
 *
 * @param <P>
 * @author yellow013
 * @creation 2018年5月14日
 */
@NotThreadSafe
public final class AccountPosition<P extends Position> {

    private final int accountId;

    /**
     * Map<instrumentId, Position>
     */
    private final MutableIntObjectMap<P> positionMap = newIntObjectHashMap();

    /**
     * 仓位对象生产者
     */
    private final PositionProducer<P> producer;

    /**
     * @param accountId int
     * @param producer  PositionProducer<P>
     */
    public AccountPosition(int accountId, PositionProducer<P> producer) {
        this.accountId = accountId;
        this.producer = producer;
    }

    public int getAccountId() {
        return accountId;
    }

    /**
     * 为指定Instrument分配仓位对象
     *
     * @param instrument Instrument
     * @return P
     */
    @Nonnull
    public P acquirePosition(Instrument instrument) {
        return positionMap.getIfAbsentPut(instrument.getInstrumentId(),
                // 创建头寸
                () -> producer.produce(accountId, instrument));
    }

}
