package io.cygnux.rapid.core.handler;

import io.cygnux.rapid.core.shared.event.AdapterReport;

@FunctionalInterface
public interface AdapterReportHandler {

    void onAdapterReport(final AdapterReport event);

}
