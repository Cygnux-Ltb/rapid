package io.cygnuxltb.console.controller;

import io.mercury.common.log4j2.Log4j2LoggerFactory;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static io.mercury.common.http.MimeType.APPLICATION_JSON_UTF8;

/**
 * 仓位查询接口
 */
@RestController
@RequestMapping(path = "/position", produces = APPLICATION_JSON_UTF8)
public class PositionController {

    private static final Logger log = Log4j2LoggerFactory.getLogger(PositionController.class);

}
