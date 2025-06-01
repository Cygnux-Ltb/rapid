package io.cygnux.rapid.launch;

import io.mercury.common.datetime.DateTimeUtil;
import io.mercury.common.log4j2.Log4j2Configurator;

public class ExampleLauncher {

    static {
        Log4j2Configurator.setLogFilename("example-" + DateTimeUtil.datetimeOfSecond());
        Log4j2Configurator.useInfoLogLevel();
    }

    public static void main(String[] args) {
        // 此处可以以多种方式读取配置
    }

}
