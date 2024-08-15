package io.rapid.adaptor.ctp.converter;

import io.mercury.common.datetime.EpochTime;
import io.mercury.common.log4j2.Log4j2Configurator;
import io.mercury.serialization.json.JsonWriter;
import io.rapid.core.serializable.avro.enums.OrdStatus;
import io.rapid.core.serializable.avro.enums.TrdAction;
import io.rapid.core.serializable.avro.enums.TrdDirection;
import io.rapid.core.serializable.avro.inbound.OrderEvent;
import org.junit.Test;

public class OrderEventConverterTest {

    static {
        Log4j2Configurator.useInfoLogLevel();
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
        System.out.println(JsonWriter.toJson(event));

    }

}
