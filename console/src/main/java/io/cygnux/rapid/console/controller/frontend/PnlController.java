package io.cygnux.rapid.console.controller.frontend;

import io.cygnux.rapid.console.controller.ResponseStatus;
import io.cygnux.rapid.infra.dto.resp.PnlRsp;
import io.cygnux.rapid.infra.dto.resp.PnlSettlementRsp;
import io.cygnux.rapid.infra.service.PnlService;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.swagger.v3.oas.annotations.tags.Tag;
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

import static io.cygnux.rapid.console.controller.ControllerUtil.bodyToObject;
import static io.cygnux.rapid.console.controller.ControllerUtil.paramIsNull;
import static io.cygnux.rapid.console.controller.HttpParam.STRATEGY_ID;
import static io.cygnux.rapid.console.controller.HttpParam.TRADING_DAY;
import static io.cygnux.rapid.core.types.ValueValidator.illegalTradingDay;

/**
 * [REST] - PNL(盈亏)服务
 */
@Tag(name = "PNL(盈亏)服务")
@RestController("pnlControllerV1")
@RequestMapping(path = "/pnl/v1", produces = "application/json;charset=utf-8")
public class PnlController {

    private static final Logger log = Log4j2LoggerFactory.getLogger(PnlController.class);

    @Resource
    private PnlService pnlService;

    /**
     * 查询PNL (查询盈亏)
     *
     * @param tradingDay 交易日
     * @param strategyId 策略ID
     * @return List<PnlRsp>
     */
    @GetMapping
    public List<PnlRsp> getPnl(@RequestParam(TRADING_DAY) int tradingDay,
                               @RequestParam(STRATEGY_ID) int strategyId) {
        if (paramIsNull(tradingDay))
            throw new IllegalArgumentException("get pnl param error -> " + tradingDay);
        return pnlService.getPnl(strategyId, tradingDay);
    }

    /**
     * 更新PNL (内部接口, 策略引擎调用)
     *
     * @param request HttpServletRequest
     * @return ResponseStatus
     */
    @PutMapping(consumes = "application/json;charset=utf-8")
    public ResponseStatus putPnl(@RequestBody HttpServletRequest request) {
        var pnl = bodyToObject(request, PnlRsp.class);
        return pnl == null
                ? ResponseStatus.BAD_REQUEST : pnlService.putPnl(pnl)
                ? ResponseStatus.OK : ResponseStatus.INTERNAL_ERROR;
    }

    /**
     * 查询结算PNL (查询结算盈亏)
     *
     * @param tradingDay [int] 交易日
     * @param strategyId [int] 策略ID
     * @return List<PnlSettlementRsp>
     */
    @GetMapping("/settlement")
    public List<PnlSettlementRsp> getPnlSettlement(@RequestParam(TRADING_DAY) int tradingDay,
                                                   @RequestParam(STRATEGY_ID) int strategyId) {
        if (illegalTradingDay(tradingDay, log))
            throw new IllegalArgumentException("tradingDay");
        return pnlService.getPnlSettlement(strategyId, tradingDay);
    }

}
