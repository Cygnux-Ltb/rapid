package io.cygnux.rapid.core.handler;

import io.cygnux.rapid.core.shared.event.AdaptorReport;

@FunctionalInterface
public interface AdaptorReportHandler {

    void onAdaptorReport(final AdaptorReport event);

}
