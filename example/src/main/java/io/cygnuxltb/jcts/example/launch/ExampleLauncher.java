package io.cygnuxltb.jcts.example.launch;

import io.mercury.common.datetime.DateTimeUtil;
import io.mercury.common.log4j2.Log4j2Configurator;
import io.mercury.common.log4j2.Log4j2Configurator.LogLevel;

public class ExampleLauncher {

	static {
		Log4j2Configurator.setLogFilename(STR."example-\{DateTimeUtil.datetimeOfSecond()}");
		Log4j2Configurator.setLogLevel(LogLevel.INFO);
	}

	public static void main(String[] args) {
		// 此处可以以多种方式读取配置
	}

}
