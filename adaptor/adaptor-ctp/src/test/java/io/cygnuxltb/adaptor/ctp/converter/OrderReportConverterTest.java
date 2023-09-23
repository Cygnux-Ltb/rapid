package io.cygnuxltb.adaptor.ctp.converter;

import io.cygnuxltb.jcts.core.ser.enums.OrdStatus;
import io.cygnuxltb.jcts.core.ser.enums.TrdAction;
import io.cygnuxltb.jcts.core.ser.enums.TrdDirection;
import io.cygnuxltb.jcts.core.ser.event.OrderEvent;
import io.mercury.common.datetime.EpochTime;
import io.mercury.common.log4j2.Log4j2Configurator;
import io.mercury.common.log4j2.Log4j2Configurator.LogLevel;
import io.mercury.serialization.json.JsonWrapper;
import org.junit.Test;

public class OrderReportConverterTest {

    static {
        Log4j2Configurator.setLogLevel(LogLevel.INFO);
    }

    @Test
    public void test() {
        var builder = OrderEvent.newBuilder();
        // 微秒时间戳
        builder.setEpochMicros(EpochTime.getEpochMicros());
        // OrdSysId
        builder.setOrdSysId(0L)
                // 交易日
                // 投资者ID
                // 报单引用
                .setOrderRef("2221")
                .setExchangeCode("").setInstrumentCode("")
                .setBrokerOrdSysId("");
        // 报单编号
        // 报单状态
        builder.setStatus(OrdStatus.NEW_REJECTED);
        builder.setDirection(TrdDirection.LONG);
        builder.setAction(TrdAction.OPEN);
        OrderEvent event = builder.build();
        System.out.println(JsonWrapper.toJson(event));

    }

}
