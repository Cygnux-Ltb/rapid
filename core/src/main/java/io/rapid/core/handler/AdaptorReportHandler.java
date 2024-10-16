package io.rapid.core.handler;

import io.rapid.core.event.inbound.AdaptorReport;

@FunctionalInterface
public interface AdaptorReportHandler {

    void onAdaptorReport(final AdaptorReport event);

}
