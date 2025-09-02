package io.cygnux.rapid.core.handler;

import io.cygnux.rapid.core.stream.event.AdaptorReport;

@FunctionalInterface
public interface AdaptorReportHandler {

    void onAdaptorReport(final AdaptorReport event);

}
