package io.cygnux.rapid.console.component;

import io.mercury.transport.zmq.ZmqCfg;
import jakarta.annotation.PostConstruct;

public class MsgReceiver {

    @PostConstruct
    private void init() {
        ZmqCfg.ipc("");
    }

}
