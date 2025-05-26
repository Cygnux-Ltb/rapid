package io.cygnux.rapid.backtest;

import io.mercury.common.log4j2.Log4j2Configurator;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.LocalDateTime;

@EnableMBeanExport
@EntityScan("io.cygnuxltb.console.persistence.entity")
@EnableJpaRepositories(basePackages = {"io.cygnuxltb.console.persistence"})
@SpringBootApplication
public class BacktestBootApplication {

    static {
        Log4j2Configurator.setLogFolder("rapid");
        Log4j2Configurator.setLogFilename("backtest");
        Log4j2Configurator.useInfoLogLevel();
    }

    private static final Logger log = Log4j2LoggerFactory.getLogger(BacktestBootApplication.class);

    public static void main(String[] args) {
        
        log.info(">>>>>>>>>>>>>>>>>>>>>[BACKTEST START ({}) ]<<<<<<<<<<<<<<<<<<<<<", LocalDateTime.now());
        SpringApplication.run(BacktestBootApplication.class, args);
    }

}
