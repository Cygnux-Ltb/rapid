package io.rapid.adaptor.ctp.gateway.event;

import io.mercury.common.functional.Handler;

@FunctionalInterface
public interface FtdcEventHandler extends Handler<FtdcEvent> {

}
