package io.rapid.core.position;

import io.rapid.core.account.AccountPosition;
import io.rapid.core.instrument.Instrument;
import io.rapid.core.order.ChildOrder;

import java.util.function.IntFunction;

/**
 * 持仓管理接口
 *
 * @param <P>
 * @author yellow013
 */
@FunctionalInterface
public interface PositionManager<P extends Position> extends IntFunction<AccountPosition<P>> {

    /**
     * 获取实际账户持仓集合
     *
     * @param accountId int
     * @return AccountPosition<P>
     */
    AccountPosition<P> getAccountPosition(int accountId);

    /**
     * @param accountId the function argument
     * @return AccountPosition<P>
     */
    @Override
    default AccountPosition<P> apply(int accountId) {
        return getAccountPosition(accountId);
    }

    /**
     * 为实际账户和instrument分配初始持仓
     *
     * @param accountId  int
     * @param instrument Instrument
     * @return P
     */
    default P acquirePosition(int accountId, Instrument instrument) {
        return getAccountPosition(accountId).acquirePosition(instrument);
    }

    /**
     * 获取指定实际账户和instrument的持仓, 并以订单回报更新持仓
     *
     * @param accountId  int
     * @param instrument Instrument
     * @param order      ChildOrder
     */
    default void onChildOrder(int accountId, Instrument instrument, ChildOrder order) {
        acquirePosition(accountId, instrument).updateWithOrder(order);
    }

}
