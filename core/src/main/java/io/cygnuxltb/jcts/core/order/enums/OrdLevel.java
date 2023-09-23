package io.cygnuxltb.jcts.core.order.enums;


import io.cygnuxltb.jcts.core.order.TdxProvider;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum OrdLevel implements TdxProvider<io.cygnuxltb.jcts.core.ser.enums.OrdLevel> {

    /**
     * 子订单
     */
    Child('C', io.cygnuxltb.jcts.core.ser.enums.OrdLevel.CHILD),

    /**
     * 父订单
     */
    Parent('P', io.cygnuxltb.jcts.core.ser.enums.OrdLevel.PARENT),

    /**
     * 策略订单
     */
    Strategy('S', io.cygnuxltb.jcts.core.ser.enums.OrdLevel.STRATEGY),

    /**
     * 组订单
     */
    Group('G', io.cygnuxltb.jcts.core.ser.enums.OrdLevel.GROUP);

    private final char code;
    private final io.cygnuxltb.jcts.core.ser.enums.OrdLevel tdxValue;

    public char getCode() {
        return code;
    }

    @Override
    public io.cygnuxltb.jcts.core.ser.enums.OrdLevel getTdxValue() {
        return tdxValue;
    }

}