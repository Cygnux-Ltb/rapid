package io.cygnuxltb.websocket;

import io.javalin.Javalin;
import io.mercury.common.log4j2.Log4j2Configurator;

public class WebsocketStartup {

    static {
        Log4j2Configurator.setLogFolder("rapid");
        Log4j2Configurator.setLogFilename("websocket");
        Log4j2Configurator.setLogLevel(Log4j2Configurator.LogLevel.INFO);
    }

    public static void main(String[] args) {
        var app = Javalin.create(/*config*/)
                .get("/", ctx -> ctx.result("Hello World"))
                .start(7070);

    }

}