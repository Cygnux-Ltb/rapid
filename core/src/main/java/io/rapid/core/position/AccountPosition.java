package io.rapid.core.position;

import io.mercury.common.collections.MutableMaps;
import io.rapid.core.instrument.Instrument;
import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/**
 * 管理一个实际账户的持仓
 *
 * @author yellow013
 * @creation 2018年5月14日
 */
@NotThreadSafe
public final class AccountPosition {

    private final int accountId;

    /**
     * Map<instrumentId, Position>
     */
    private final MutableIntObjectMap<Position> positionMap = MutableMaps.newIntObjectMap();

    /**
     * 仓位对象生产者
     */
    private final PositionProducer producer;

    /**
     * @param accountId int
     */
    public AccountPosition(int accountId) {
        this(accountId, PositionProducer.DEFAULT);
    }

    /**
     * @param accountId int
     * @param producer  PositionProducer<P>
     */
    public AccountPosition(int accountId, PositionProducer producer) {
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
     * @return Position
     */
    @Nonnull
    public Position acquirePosition(Instrument instrument) {
        return positionMap.getIfAbsentPut(instrument.getInstrumentId(),
                // 创建头寸
                () -> producer.produce(accountId, instrument));
    }


}
