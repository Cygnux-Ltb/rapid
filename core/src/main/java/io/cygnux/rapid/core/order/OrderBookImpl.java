package io.cygnux.rapid.core.order;

import io.mercury.common.collections.Capacity;
import org.eclipse.collections.api.map.primitive.MutableLongObjectMap;

import javax.annotation.Nullable;

import static io.mercury.common.collections.MutableMaps.newLongObjectMap;

/**
 * 用于存储订单的组件
 *
 * @author yellow013
 */
public final class OrderBookImpl implements OrderBook {

    /**
     * 存储当前OrderBook里的所有订单, 以ordSysId索引
     */
    private final MutableLongObjectMap<io.cygnux.rapid.core.types.order.Order> orderMap;

    /**
     * 存储当前OrderBook里的所有long订单, 以ordSysId索引
     */
    private final MutableLongObjectMap<io.cygnux.rapid.core.types.order.Order> longOrderMap;

    /**
     * 存储当前OrderBook里的所有short订单, 以ordSysId索引
     */
    private final MutableLongObjectMap<io.cygnux.rapid.core.types.order.Order> shortOrderMap;

    /**
     * 存储当前OrderBook里的所有活动状态的订单, 以ordSysId索引
     */
    private final MutableLongObjectMap<io.cygnux.rapid.core.types.order.Order> activeOrderMap;

    /**
     * 存储本OrderBook里的所有活动状态的long订单, 以ordSysId索引
     */
    private final MutableLongObjectMap<io.cygnux.rapid.core.types.order.Order> activeLongOrderMap;

    /**
     * 存储本OrderBook里的所有活动状态的short订单, 以ordSysId索引
     */
    private final MutableLongObjectMap<io.cygnux.rapid.core.types.order.Order> activeShortOrderMap;

    /**
     * Use default Capacity.L07_SIZE, Size == 128
     */
    public OrderBookImpl() {
        this(Capacity.HEX_80);
    }

    /**
     * @param capacity Capacity
     */
    public OrderBookImpl(Capacity capacity) {
        this.orderMap = newLongObjectMap(capacity.size());
        this.longOrderMap = newLongObjectMap(capacity.size());
        this.shortOrderMap = newLongObjectMap(capacity.size());
        this.activeOrderMap = newLongObjectMap(capacity.halfSize().size());
        this.activeLongOrderMap = newLongObjectMap(capacity.halfSize().size());
        this.activeShortOrderMap = newLongObjectMap(capacity.halfSize().size());
    }

    public MutableLongObjectMap<io.cygnux.rapid.core.types.order.Order> getAllOrders() {
        return orderMap;
    }

    public MutableLongObjectMap<io.cygnux.rapid.core.types.order.Order> getLongOrders() {
        return longOrderMap;
    }

    public MutableLongObjectMap<io.cygnux.rapid.core.types.order.Order> getShortOrders() {
        return shortOrderMap;
    }

    public MutableLongObjectMap<io.cygnux.rapid.core.types.order.Order> getAllActiveOrders() {
        return activeOrderMap;
    }

    public MutableLongObjectMap<io.cygnux.rapid.core.types.order.Order> getLongActiveOrders() {
        return activeLongOrderMap;
    }

    public MutableLongObjectMap<io.cygnux.rapid.core.types.order.Order> getShortActiveOrders() {
        return activeShortOrderMap;
    }

    /**
     * @param order Order
     */
    public void putOrder(io.cygnux.rapid.core.types.order.Order order) {
        switch (order.getDirection()) {
            case LONG -> {
                longOrderMap.put(order.getOrdSysId(), order);
                activeLongOrderMap.put(order.getOrdSysId(), order);
            }
            case SHORT -> {
                shortOrderMap.put(order.getOrdSysId(), order);
                activeShortOrderMap.put(order.getOrdSysId(), order);
            }
            default ->
                    throw new IllegalStateException("ordSysId -> [" + order.getOrdSysId() + "], direction is invalid");
        }
        if (!order.getStatus().isFinished())
            activeOrderMap.put(order.getOrdSysId(), order);
        orderMap.put(order.getOrdSysId(), order);
    }

    @Nullable
    public io.cygnux.rapid.core.types.order.Order getOrder(long ordSysId) {
        return orderMap.get(ordSysId);
    }

}
