package io.rapid.adaptor.ctp.component;

import io.cygnuxltb.console.client.HttpConsoleClient;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 使用IOC注入时需要提供的相关Bean
 */
@Configuration
public class ConsoleClientConfiguration {

    private static final Logger log = Log4j2LoggerFactory.getLogger(ConsoleClientConfiguration.class);

    @Value("${console.service.addr}")
    private String addr;

    @Value("${console.service.version}")
    private String version;

    @Bean(name = "httpConsoleClient")
    public HttpConsoleClient getHttpConsoleClient() {
        return new HttpConsoleClient(addr, version);
    }

    @PostConstruct
    private void init() {

    }

}
