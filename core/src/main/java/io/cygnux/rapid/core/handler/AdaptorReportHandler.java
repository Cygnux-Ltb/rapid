package io.cygnux.rapid.core.handler;

import io.cygnux.rapid.core.event.inbound.AdaptorReport;

@FunctionalInterface
public interface AdaptorReportHandler {

    void onAdaptorReport(final AdaptorReport event);

}
