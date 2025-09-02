package io.cygnux.rapid.core.manager;

import io.cygnux.rapid.core.instrument.Instrument;
import io.cygnux.rapid.core.instrument.InstrumentKeeper;
import io.cygnux.rapid.core.order.impl.ChildOrder;
import io.cygnux.rapid.core.position.AccountPosition;
import io.cygnux.rapid.core.position.Position;
import io.cygnux.rapid.core.stream.StreamEventHandler;

/**
 * 持仓管理接口
 *
 * @author yellow013
 */
public interface PositionManager extends StreamEventHandler {

    /**
     * 获取实际账户持仓集合
     *
     * @param accountId int
     * @return AccountPosition<P>
     */
    AccountPosition acquireAccountPosition(int accountId);

    /**
     * 为实际账户和 <b>[Instrument]</b> 分配初始持仓
     *
     * @param accountId  int
     * @param instrument Instrument
     * @return P
     */
    default Position acquirePosition(int accountId, Instrument instrument) {
        return acquireAccountPosition(accountId).acquirePosition(instrument);
    }

    /**
     * 为实际账户和 <b>[Instrument]</b> 分配初始持仓
     *
     * @param accountId    int
     * @param instrumentId int
     * @return P
     */
    default Position acquirePosition(int accountId, int instrumentId) {
        return acquireAccountPosition(accountId).acquirePosition(InstrumentKeeper.getInstrumentById(instrumentId));
    }

    /**
     * 为实际账户和 <b>[Instrument]</b> 分配初始持仓
     *
     * @param accountId      int
     * @param instrumentCode String
     * @return P
     */
    default Position acquirePosition(int accountId, String instrumentCode) {
        return acquireAccountPosition(accountId).acquirePosition(InstrumentKeeper.getInstrumentByCode(instrumentCode));
    }

    /**
     * 获取指定实际账户和instrument的持仓, 并以订单回报更新持仓
     *
     * @param accountId  int
     * @param instrument Instrument
     * @param order      ChildOrder
     */
    default void onOrder(int accountId, Instrument instrument, ChildOrder order) {
        acquirePosition(accountId, instrument).updateWith(order);
    }

}
