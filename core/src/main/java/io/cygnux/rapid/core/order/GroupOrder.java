package io.cygnux.rapid.core.order;

import io.cygnux.rapid.core.order.attr.OrdTimestamp;
import io.cygnux.rapid.core.order.impl.ChildOrder;
import lombok.Getter;
import org.eclipse.collections.api.set.ImmutableSet;

import static io.mercury.common.collections.ImmutableSets.newImmutableSet;

/**
 * @author yellow013
 */
public final class GroupOrder {

    /**
     * 订单组ID
     */
    @Getter
    private final long groupOrdSysId;

    /**
     * 订单组时间戳
     */
    @Getter
    private final OrdTimestamp groupTimestamp;

    /**
     * 包含的订单
     */
    @Getter
    private final ImmutableSet<ChildOrder> childOrders;

    public GroupOrder(long groupOrdSysId, ChildOrder... orders) {
        this.groupOrdSysId = groupOrdSysId;
        this.groupTimestamp = OrdTimestamp.now();
        this.childOrders = newImmutableSet(orders);
    }


}
