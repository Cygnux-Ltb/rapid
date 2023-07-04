package io.cygnuxltb.adaptor.ctp;

import io.mercury.common.log4j2.Log4j2Configurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CtpChannelApplication {

    static {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>[CTP CHANNEL START]<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        Log4j2Configurator.setLogFolder("cytrader");
        Log4j2Configurator.setLogFilename("console");
        Log4j2Configurator.setLogLevel(Log4j2Configurator.LogLevel.INFO);
    }

    public static void main(String[] args) {
        SpringApplication.run(CtpChannelApplication.class, args);
    }

}
