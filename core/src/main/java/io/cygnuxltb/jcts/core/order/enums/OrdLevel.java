package io.cygnuxltb.jcts.core.order.enums;


import io.cygnuxltb.jcts.core.order.TdxProvider;
import io.cygnuxltb.jcts.core.serialization.avro.enums.AEnumOrdLevel;

public enum OrdLevel implements TdxProvider<AEnumOrdLevel> {

    /**
     * 子订单
     */
    Child('C', AEnumOrdLevel.CHILD),

    /**
     * 父订单
     */
    Parent('P', AEnumOrdLevel.PARENT),

    /**
     * 策略订单
     */
    Strategy('S', AEnumOrdLevel.STRATEGY),

    /**
     * 组订单
     */
    Group('G', AEnumOrdLevel.GROUP);

    private final char code;
    private final AEnumOrdLevel tdxValue;

    OrdLevel(char code, AEnumOrdLevel tdxValue) {
        this.code = code;
        this.tdxValue = tdxValue;
    }

    public char getCode() {
        return code;
    }

    @Override
    public AEnumOrdLevel getTdxValue() {
        return tdxValue;
    }
}