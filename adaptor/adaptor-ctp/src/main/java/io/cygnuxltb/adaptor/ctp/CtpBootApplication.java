package io.cygnuxltb.adaptor.ctp;

import io.mercury.common.log4j2.Log4j2Configurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CtpBootApplication {

    static {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>[CTP ADAPTOR START]<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        Log4j2Configurator.setLogFolder("rapid");
        Log4j2Configurator.setLogFilename("adaptor-ctp");
        Log4j2Configurator.setLogLevel(Log4j2Configurator.LogLevel.INFO);
    }

    public static void main(String[] args) {
        SpringApplication.run(CtpBootApplication.class, args);
    }

}
