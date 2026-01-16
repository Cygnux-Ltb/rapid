package io.cygnux.rapid.console.controller.frontend;

import io.cygnux.rapid.console.controller.ResponseStatus;
import io.cygnux.rapid.infra.dto.req.SimulateReq;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * [REST] - 模拟测试服务
 */
@Tag(name = "模拟测试服务")
@RestController("simulateControllerV1")
@RequestMapping(path = "/simulate/v1", produces = "application/json;charset=utf-8")
public class SimulateController {

    private static final Logger log = Log4j2LoggerFactory.getLogger(SimulateController.class);

    /**
     * 提交测试请求
     *
     * @return ResponseStatus
     */
    @PostMapping("/start")
    public ResponseStatus start(SimulateReq req) {
        return ResponseStatus.OK;
    }

}
