package io.rapid.core.order;

import io.rapid.core.event.enums.TrdAction;

public record TradeCommand
        (// [多仓]开/平
         TrdAction longAction,
         // [多仓]数量
         int longQty,
         // [空仓]开/平
         TrdAction shortAction,
         // [空仓]数量
         int shortQty) {
}
