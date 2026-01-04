package io.cygnux.rapid.core.handler;

import io.cygnux.rapid.core.event.received.AdapterStatusReport;

@FunctionalInterface
public interface AdapterReportHandler {

    void onAdapterReport(final AdapterStatusReport event);

}
