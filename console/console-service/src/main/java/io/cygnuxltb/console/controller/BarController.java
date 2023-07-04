package io.cygnuxltb.console.controller;

import io.cygnuxltb.console.controller.base.ResponseStatus;
import io.cygnuxltb.console.controller.util.ControllerUtil;
import io.cygnuxltb.console.persistence.entity.TblBar;
import io.cygnuxltb.console.service.BarService;
import io.cygnuxltb.protocol.http.outbound.BarDTO;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static io.cygnuxltb.console.controller.base.HttpParam.INSTRUMENT_CODE;
import static io.cygnuxltb.console.controller.base.HttpParam.TRADING_DAY;
import static io.mercury.common.http.MimeType.APPLICATION_JSON_UTF8;

/**
 * 历史行情接口
 */
@RestController
@RequestMapping(path = "/bar", produces = APPLICATION_JSON_UTF8)
public final class BarController {

    private static final Logger log = Log4j2LoggerFactory.getLogger(BarController.class);

    @Resource
    private BarService service;

    /**
     * 获取1分钟BAR
     *
     * @param tradingDay     交易日
     * @param instrumentCode 标的代码 (不支持查询多个标的)
     * @return List<BarEntity>
     * @apiNote 获取1分钟BAR
     */
    @GetMapping
    public List<BarDTO> getBars(@RequestParam(TRADING_DAY) int tradingDay,
                                @RequestParam(INSTRUMENT_CODE) String instrumentCode) {
        log.info("get bars with : tradingDay -> {}, instrumentCode -> {}",
                tradingDay, instrumentCode);
        return service.getBars(instrumentCode, tradingDay);
    }

    /**
     * 更新BAR (內部接口)
     *
     * @param request HttpServletRequest
     * @return ResponseStatus
     * @apiNote 更新BAR (內部接口)
     */
    @PostMapping(consumes = APPLICATION_JSON_UTF8)
    public ResponseStatus putBar(@RequestBody HttpServletRequest request) {
        var bar = ControllerUtil.bodyToObject(request, TblBar.class);
        log.info("put bar -> {}", bar);
        return bar == null ? ResponseStatus.BAD_REQUEST
                : service.putBar(bar)
                ? ResponseStatus.CREATED
                : ResponseStatus.INTERNAL_ERROR;
    }

}
