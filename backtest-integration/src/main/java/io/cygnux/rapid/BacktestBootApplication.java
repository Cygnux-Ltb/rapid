package io.cygnux.rapid;

import io.mercury.common.log4j2.Log4j2Configurator;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableMBeanExport;

import java.time.LocalDateTime;

@EnableMBeanExport
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
