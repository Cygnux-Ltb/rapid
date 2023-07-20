package io.cygnuxltb.jcts.core.serialization.wire.event;

import net.openhft.chronicle.core.io.IORuntimeException;
import net.openhft.chronicle.wire.Marshallable;
import net.openhft.chronicle.wire.WireIn;
import net.openhft.chronicle.wire.WireOut;
import org.jetbrains.annotations.NotNull;

public class WireOrderEvent implements Marshallable {

    private long epochMicros;
    private long ordSysId;
    private int tradingDay;
    private java.lang.String brokerId;
    private java.lang.String investorId;
    private java.lang.String orderRef;
    private java.lang.String brokerOrdSysId;
    private java.lang.String exchangeCode;
    private java.lang.String instrumentCode;
    private io.cygnuxltb.jcts.core.serialization.avro.enums.AEnumOrdStatus status;
    private io.cygnuxltb.jcts.core.serialization.avro.enums.AEnumTrdDirection direction;
    private io.cygnuxltb.jcts.core.serialization.avro.enums.AEnumTrdAction action;
    private int offerQty;
    private int filledQty;
    private long offerPrice;
    private long tradePrice;
    private java.lang.String offerTime;
    private java.lang.String updateTime;
    private java.lang.String msg;


    @Override
    public void readMarshallable(@NotNull WireIn wire) throws IORuntimeException {

    }

    @Override
    public void writeMarshallable(@NotNull WireOut wire) {

    }
}
