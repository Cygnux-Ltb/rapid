package io.cygnuxltb.console.controller;

import io.cygnuxltb.console.service.PositionService;
import io.cygnuxltb.protocol.http.response.PositionsDTO;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static io.cygnuxltb.console.controller.base.HttpParam.USER_ID;
import static io.cygnuxltb.protocol.http.ServiceURI.POSITION;
import static io.mercury.common.http.MimeType.APPLICATION_JSON_UTF8;

/**
 * 仓位服务
 */
@RestController
@RequestMapping(path = POSITION, produces = APPLICATION_JSON_UTF8)
public class PositionController {

    private static final Logger log = Log4j2LoggerFactory.getLogger(PositionController.class);

    @Resource
    private PositionService service;

    /**
     * 查询当前持仓 ([page1.jpg]-持仓板块)
     *
     * @param userId 用户ID
     * @return PositionDTO
     */
    @GetMapping("/current")
    public PositionsDTO getCurrentPosition(@RequestParam(USER_ID) int userId) {
        return service.getPosition(userId);
    }



}
