package io.cygnux.rapid.core.trade;

import io.mercury.common.sequence.OrderedObject;

public record TradeRecord(// 订单编号
                          long ordSysId,
                          // 序列
                          int sequence,
                          // Epoch微秒数
                          long epochMicros,
                          // 成交价格
                          double tradePrice,
                          // 成交数量
                          int tradeQty) implements OrderedObject<TradeRecord> {

    @Override
    public int compareTo(TradeRecord o) {
        if (this.ordSysId < o.ordSysId)
            return -1;
        if (this.ordSysId > o.ordSysId)
            return 1;
        return Integer.compare(this.sequence, o.sequence);
    }

    @Override
    public long orderNum() {
        return epochMicros;
    }

}