package io.cygnuxltb.console.controller;

import io.cygnuxltb.console.controller.base.ResponseStatus;
import io.cygnuxltb.protocol.http.request.SimulateDTO;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static io.cygnuxltb.console.controller.base.ResponseStatus.OK;
import static io.cygnuxltb.protocol.http.ServiceURI.simulate;
import static io.mercury.common.http.MimeType.APPLICATION_JSON_UTF8;
import static io.mercury.common.log4j2.Log4j2LoggerFactory.getLogger;

/**
 * 模拟测试
 */
@RestController
@RequestMapping(path = simulate, produces = APPLICATION_JSON_UTF8)
public class SimulateController {

    private static final Logger log = getLogger(SimulateController.class);

    /**
     * 提交测试请求
     *
     * @return ResponseStatus
     */
    @PostMapping("/start")
    public ResponseStatus start(SimulateDTO dto) {
        return OK;
    }

}
