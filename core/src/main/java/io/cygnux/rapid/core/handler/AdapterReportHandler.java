package io.cygnux.rapid.core.handler;

import io.cygnux.rapid.core.types.event.received.AdapterReport;

@FunctionalInterface
public interface AdapterReportHandler {

    void onAdapterReport(final AdapterReport event);

}
