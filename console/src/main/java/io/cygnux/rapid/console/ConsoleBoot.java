package io.cygnux.rapid.console;

import io.mercury.common.log4j2.Log4j2Configurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static io.mercury.common.log4j2.Log4j2Configurator.LogLevel.INFO;

//@EnableScheduling
@SpringBootApplication
public class ConsoleBoot {

    static {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>[CONSOLE START]<<<<<<<<<<<<<<<<<<<<<");
        Log4j2Configurator.setLogFolder("rapid");
        Log4j2Configurator.setLogFilename("console");
        Log4j2Configurator.setLogLevel(INFO);
    }

    static void main(String[] args) {
        SpringApplication.run(ConsoleBoot.class, args);
    }

}
