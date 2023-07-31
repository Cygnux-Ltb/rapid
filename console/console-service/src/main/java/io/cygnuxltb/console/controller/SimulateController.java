package io.cygnuxltb.console.controller;

import io.cygnuxltb.console.controller.base.ResponseStatus;
import io.cygnuxltb.protocol.http.request.SimulateDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 模拟测试
 */
@RestController("/simulate")
public class SimulateController {


    /**
     * @return ResponseStatus
     */
    @PostMapping("/start")
    public ResponseStatus start(SimulateDTO dto) {
        return ResponseStatus.OK;
    }
    
}
