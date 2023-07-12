package io.cygnuxltb.adaptor.ctp.converter;

import io.cygnuxltb.jcts.core.order.enums.OrdStatus;
import io.cygnuxltb.jcts.core.order.enums.TrdAction;
import io.cygnuxltb.jcts.core.order.enums.TrdDirection;
import io.cygnuxltb.jcts.core.serialization.avro.event.AvOrderEvent;
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
        var builder = AvOrderEvent.newBuilder();
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
        builder.setStatus(OrdStatus.NewRejected.getTdxValue());
        builder.setDirection(TrdDirection.Long.getTdxValue());
        builder.setAction(TrdAction.Open.getTdxValue());
        AvOrderEvent report = builder.build();
        System.out.println(JsonWrapper.toJson(report));

    }

}
