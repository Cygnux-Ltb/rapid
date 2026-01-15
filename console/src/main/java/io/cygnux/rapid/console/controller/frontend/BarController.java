package io.cygnux.rapid.console.controller.frontend;

import io.cygnux.rapid.infra.dto.resp.BarRsp;
import io.cygnux.rapid.console.controller.ControllerUtil;
import io.cygnux.rapid.console.controller.ResponseStatus;
import io.cygnux.rapid.infra.service.BarService;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.swagger.v3.oas.annotations.tags.Tag;
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

import static io.cygnux.rapid.core.types.ValueValidator.illegalInstrumentCode;
import static io.cygnux.rapid.console.controller.HttpParam.INSTRUMENT_CODE;
import static io.cygnux.rapid.console.controller.HttpParam.TRADING_DAY;
import static io.cygnux.rapid.console.controller.ResponseStatus.BAD_REQUEST;
import static io.cygnux.rapid.console.controller.ResponseStatus.CREATED;
import static io.cygnux.rapid.console.controller.ResponseStatus.INTERNAL_ERROR;

/**
 * [REST] - 历史行情服务
 */
@Tag(name = "历史行情服务")
@RestController("barControllerV1")
@RequestMapping(path = "/bar/v1", produces = "application/json;charset=utf-8")
public class BarController {

    private static final Logger log = Log4j2LoggerFactory.getLogger(BarController.class);

    @Resource
    private BarService barService;

    /**
     * 获取1分钟BAR
     *
     * @param instrumentCode 标的代码 (不支持查询多个标的)
     * @param tradingDay     交易日
     * @return ResponseBean
     * @apiNote 获取1分钟BAR
     */

    @GetMapping
    public List<BarRsp> getBars(@RequestParam(INSTRUMENT_CODE) String instrumentCode,
                                @RequestParam(TRADING_DAY) int tradingDay) {
        log.info("GET bars with : instrumentCode -> {}, tradingDay -> {}",
                instrumentCode, tradingDay);
        if (illegalInstrumentCode(instrumentCode, log))
            throw new IllegalArgumentException("[instrumentCode]不可为空");
        return barService.getBars(instrumentCode, tradingDay);
    }

    /**
     * 更新BAR [內部接口]
     *
     * @param request HttpServletRequest
     * @return ResponseStatus
     * @apiNote 更新BAR (內部接口)
     */
    @PostMapping(consumes = "application/json;charset=utf-8")
    public ResponseStatus putBar(@RequestBody HttpServletRequest request) {
        var bar = ControllerUtil.bodyToObject(request, BarRsp.class);
        log.info("put bar -> {}", bar);
        return bar == null ? BAD_REQUEST : barService.putBar(bar)
                ? CREATED : INTERNAL_ERROR;
    }

}
