package io.cygnuxltb.engine.actor;

import io.horizon.trader.handler.OrderReportHandler;
import io.horizon.trader.serialization.avro.outbound.AvroOrderReport;

import javax.annotation.Nonnull;

public class OrderReportActor implements OrderReportHandler {

    @Override
    public void onOrderReport(@Nonnull AvroOrderReport report) {
        // TODO Auto-generated method stub
    }

}
