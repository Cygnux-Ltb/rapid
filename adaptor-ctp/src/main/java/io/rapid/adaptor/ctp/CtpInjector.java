package io.rapid.adaptor.ctp;

import io.mercury.common.concurrent.ring.RingEventbus;
import io.mercury.common.concurrent.ring.RingEventbus.MpRingEventbus;
import io.rapid.adaptor.ctp.gateway.event.FtdcEvent;
import io.rapid.core.account.Account;
import io.rapid.core.adaptor.ConnectionMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 */
@Configuration
public class CtpConnector {

    @Bean(name = "config")
    public CtpConfig getConfig() {
        return null;
    }

    @Bean(name = "account")
    public Account getAccount() {
        return null;
    }

    @Bean(name = "config")
    public ConnectionMode getMode() {
        return ConnectionMode.FULL;
    }

    @Bean(name = "eventbus")
    public MpRingEventbus<FtdcEvent> getEventbus() {
        return RingEventbus.multiProducer(FtdcEvent.EVENT_FACTORY)
                .name("")
                .size(2048)
                .process(this::eventHandle);
    }

    private void eventHandle(FtdcEvent event) {

    }


}