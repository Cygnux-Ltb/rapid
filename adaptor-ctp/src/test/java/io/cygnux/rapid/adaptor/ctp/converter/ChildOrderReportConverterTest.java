package io.cygnux.rapid.adaptor.ctp.converter;

import io.mercury.common.epoch.HighResolutionEpoch;
import io.mercury.common.log4j2.Log4j2Configurator;
import io.mercury.serialization.json.JsonWriter;
import io.cygnux.rapid.core.event.enums.OrdStatus;
import io.cygnux.rapid.core.event.enums.TrdAction;
import io.cygnux.rapid.core.event.enums.TrdDirection;
import io.cygnux.rapid.core.event.inbound.OrderReport;
import org.junit.Test;

public class ChildOrderReportConverterTest {

    static {
        Log4j2Configurator.useInfoLogLevel();
    }

    @Test
    public void test() {
        var builder = OrderReport.builder();
        // 微秒时间戳
        builder.epochMicros(HighResolutionEpoch.micros());
        // OrdSysId
        builder.ordSysId(0L)
                // 交易日
                // 投资者ID
                // 报单引用
                .orderRef("2221")
                .exchangeCode("").instrumentCode("")
                .brokerOrdSysId("");
        // 报单编号
        // 报单状态
        builder.status(OrdStatus.NEW_REJECTED);
        builder.direction(TrdDirection.LONG);
        builder.action(TrdAction.OPEN);
        OrderReport event = builder.build();
        System.out.println(JsonWriter.toJson(event));

    }

}
