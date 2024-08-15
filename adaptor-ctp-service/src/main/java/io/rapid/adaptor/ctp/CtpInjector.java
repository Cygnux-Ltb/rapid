package io.rapid.adaptor.ctp;

import io.mercury.common.concurrent.disruptor.RingEventbus;
import io.mercury.common.concurrent.disruptor.RingEventbus.MultiProducerRingEventbus;
import io.rapid.adaptor.ctp.gateway.event.FtdcRspEvent;
import io.rapid.adaptor.ctp.param.CtpParams;
import io.rapid.core.account.Account;
import io.rapid.core.upstream.ConnectionMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 使用IOC注入时需要提供的相关Bean
 */
@Configuration
public class CtpInjector {

    @Bean(name = "param")
    public CtpParams getParam() {
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
    public MultiProducerRingEventbus<FtdcRspEvent> getEventbus() {
        return RingEventbus.multiProducer(FtdcRspEvent.EVENT_FACTORY)
                .name("")
                .size(2048)
                .process(this::eventHandle);
    }

    private void eventHandle(FtdcRspEvent event) {

    }


}
