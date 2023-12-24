package io.cygnuxltb.console;

import io.mercury.common.log4j2.Log4j2Configurator;
import io.mercury.common.log4j2.Log4j2Configurator.LogLevel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableScheduling
@SpringBootApplication
public class ConsoleApplication {

    static {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>[CONSOLE START]<<<<<<<<<<<<<<<<<<<<<");
        Log4j2Configurator.setLogFolder("rapid");
        Log4j2Configurator.setLogFilename("console");
        Log4j2Configurator.setLogLevel(LogLevel.INFO);
    }

    public static void main(String[] args) {
        SpringApplication.run(ConsoleApplication.class, args);
    }

}
