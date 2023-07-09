package io.cygnuxltb.jcts.core.trader.order.enums;

import io.cygnuxltb.jcts.core.trader.order.TdxProvider;
import io.cygnuxltb.jcts.core.trader.serialization.avro.enums.AvroOrdLevel;
import io.horizon.trader.order.TdxProvider;
import io.horizon.trader.serialization.avro.enums.AvroOrdLevel;

public enum OrdLevel implements TdxProvider<AvroOrdLevel> {

    /**
     * 子订单
     */
    Child('C', AvroOrdLevel.CHILD),

    /**
     * 父订单
     */
    Parent('P', AvroOrdLevel.PARENT),

    /**
     * 策略订单
     */
    Strategy('S', AvroOrdLevel.STRATEGY),

    /**
     * 组订单
     */
    Group('G', AvroOrdLevel.GROUP);

    private final char code;
    private final AvroOrdLevel tdxValue;

    OrdLevel(char code, AvroOrdLevel tdxValue) {
        this.code = code;
        this.tdxValue = tdxValue;
    }

    public char getCode() {
        return code;
    }

    @Override
    public AvroOrdLevel getTdxValue() {
        return tdxValue;
    }
}