package io.rapid.core.order.enums;

import io.rapid.core.order.TdxProvider;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum OrdLevel implements TdxProvider<io.rapid.core.serializable.avro.enums.OrdLevel> {

    /**
     * 子订单
     */
    Child('C', io.rapid.core.serializable.avro.enums.OrdLevel.CHILD),

    /**
     * 父订单
     */
    Parent('P', io.rapid.core.serializable.avro.enums.OrdLevel.PARENT),

    /**
     * 策略订单
     */
    Strategy('S', io.rapid.core.serializable.avro.enums.OrdLevel.STRATEGY),

    /**
     * 组订单
     */
    Group('G', io.rapid.core.serializable.avro.enums.OrdLevel.GROUP);

    private final char code;
    private final io.rapid.core.serializable.avro.enums.OrdLevel protocolValue;

    public char getCode() {
        return code;
    }

    public io.rapid.core.serializable.avro.enums.OrdLevel getProtocolValue() {
        return protocolValue;
    }

}