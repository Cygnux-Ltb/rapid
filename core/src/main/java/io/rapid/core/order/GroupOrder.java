package io.rapid.core.order;

import io.rapid.core.order.attribute.OrdTimestamp;
import io.rapid.core.order.impl.Order;
import org.eclipse.collections.api.set.ImmutableSet;

import static io.mercury.common.collections.ImmutableSets.newImmutableSet;

/**
 * @author yellow013
 */
public final class GroupOrder {

    /**
     * 订单组ID
     */
    private final long groupOrdId;

    /**
     * 订单组时间戳
     */
    private final OrdTimestamp groupTimestamp;

    /**
     * 包含的订单
     */
    private final ImmutableSet<Order> actualOrders;

    public GroupOrder(long groupOrdId, Order... orders) {
        this.groupOrdId = groupOrdId;
        this.groupTimestamp = OrdTimestamp.now();
        this.actualOrders = newImmutableSet(orders);
    }

    public long getGroupOrdId() {
        return groupOrdId;
    }

    public OrdTimestamp getGroupTimestamp() {
        return groupTimestamp;
    }

    public ImmutableSet<Order> getActualOrders() {
        return actualOrders;
    }

}
