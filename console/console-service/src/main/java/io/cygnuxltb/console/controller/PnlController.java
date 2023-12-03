package io.cygnuxltb.console.controller;

import io.cygnuxltb.console.controller.base.ResponseStatus;
import io.cygnuxltb.console.controller.util.ControllerUtil;
import io.cygnuxltb.console.persistence.entity.TblTrdPnl;
import io.cygnuxltb.console.service.PnlService;
import io.cygnuxltb.protocol.http.response.PnlDTO;
import io.cygnuxltb.protocol.http.response.PnlSettlementDTO;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static io.cygnuxltb.console.controller.base.HttpParam.STRATEGY_ID;
import static io.cygnuxltb.console.controller.base.HttpParam.TRADING_DAY;
import static io.mercury.common.http.MimeType.APPLICATION_JSON_UTF8;

/**
 * PNL(盈亏)服务
 */
@RestController
@RequestMapping(path = "/pnl", produces = APPLICATION_JSON_UTF8)
public final class PnlController {

    private static final Logger log = Log4j2LoggerFactory.getLogger(PnlController.class);

    @Resource
    private PnlService service;

    /**
     * 查询PNL (查询盈亏)
     *
     * @param tradingDay 交易日
     * @param strategyId 策略ID
     * @return List<PnlDTO>
     */
    @GetMapping
    public List<PnlDTO> getPnl(@RequestParam(TRADING_DAY) int tradingDay,
                               @RequestParam(STRATEGY_ID) int strategyId) {
        if (ControllerUtil.paramIsNull(tradingDay))
            throw new IllegalArgumentException("get pnl param error -> " + tradingDay);
        return service.getPnl(strategyId, tradingDay);
    }

    /**
     * 更新PNL (内部接口, 策略引擎调用)
     *
     * @param request HttpServletRequest
     * @return ResponseStatus
     */
    @PutMapping(consumes = APPLICATION_JSON_UTF8)
    public ResponseStatus putPnl(@RequestBody HttpServletRequest request) {
        var pnl = ControllerUtil.bodyToObject(request, TblTrdPnl.class);
        return pnl == null
                ? ResponseStatus.BAD_REQUEST : service.putPnl(pnl)
                ? ResponseStatus.OK : ResponseStatus.INTERNAL_ERROR;
    }

    /**
     * 查询结算PNL (查询结算盈亏)
     *
     * @param tradingDay 交易日
     * @param strategyId 策略ID
     * @return List<PnlSettlementDTO>
     */
    @GetMapping("/settlement")
    public List<PnlSettlementDTO> getPnlSettlement(@RequestParam(TRADING_DAY) int tradingDay,
                                                   @RequestParam(STRATEGY_ID) int strategyId) {
        if (ControllerUtil.illegalTradingDay(tradingDay, log))
            throw new IllegalArgumentException("tradingDay");
        return service.getPnlSettlement(strategyId, tradingDay);
    }

}
