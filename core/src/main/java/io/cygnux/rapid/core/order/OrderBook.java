package io.cygnux.rapid.core.order;

import org.eclipse.collections.api.map.primitive.MutableLongObjectMap;

import javax.annotation.Nullable;

/**
 * 用于存储订单的组件
 *
 * @author yellow013
 */
public interface OrderBook {

    /**
     * 返回全部订单
     *
     * @return MutableLongObjectMap<Order>
     */
    MutableLongObjectMap<io.cygnux.rapid.core.types.order.Order> getAllOrders();

    /**
     * 返回活动状态订单
     *
     * @return MutableLongObjectMap<Order>
     */
    MutableLongObjectMap<io.cygnux.rapid.core.types.order.Order> getAllActiveOrders();

    /**
     * 返回全部多单
     *
     * @return MutableLongObjectMap<Order>
     */
    MutableLongObjectMap<io.cygnux.rapid.core.types.order.Order> getLongOrders();

    /**
     * 返回活动状态多单
     *
     * @return MutableLongObjectMap<Order>
     */
    MutableLongObjectMap<io.cygnux.rapid.core.types.order.Order> getLongActiveOrders();

    /**
     * 返回全部空单
     *
     * @return MutableLongObjectMap<Order>
     */
    MutableLongObjectMap<io.cygnux.rapid.core.types.order.Order> getShortOrders();

    /**
     * 返回活动状态空单
     *
     * @return MutableLongObjectMap<Order>
     */
    MutableLongObjectMap<io.cygnux.rapid.core.types.order.Order> getShortActiveOrders();

    /**
     * 存储订单
     *
     * @param order Order 订单对象
     */
    void putOrder(io.cygnux.rapid.core.types.order.Order order);

    /**
     *
     * @param ordSysId long
     * @return Order
     */
    @Nullable
    io.cygnux.rapid.core.types.order.Order getOrder(long ordSysId);

    /**
     * 是否存在指定订单
     *
     * @param ordSysId long
     * @return boolean
     */
    default boolean isExists(long ordSysId) {
        return getOrder(ordSysId) != null;
    }

    /**
     * @param order Order
     * @throws io.cygnux.rapid.core.types.order.OrdStatusException ose
     */
    default void onCompleted(io.cygnux.rapid.core.types.order.Order order) throws io.cygnux.rapid.core.types.order.OrdStatusException {
        switch (order.getDirection()) {
            case LONG -> getLongActiveOrders().remove(order.getOrdSysId());
            case SHORT -> getShortActiveOrders().remove(order.getOrdSysId());
            case INVALID ->
                    throw new io.cygnux.rapid.core.types.order.OrdStatusException("ordSysId: [" + order.getOrdSysId() + "], direction is invalid.");
        }
        getAllActiveOrders().remove(order.getOrdSysId());
    }


}
