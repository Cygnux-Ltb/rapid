package io.cygnux.rapid.console.controller.frontend;

import io.cygnux.rapid.infra.dto.req.NewOrderBatchReq;
import io.cygnux.rapid.infra.dto.req.NewOrderReq;
import io.cygnux.rapid.infra.dto.resp.OrderEventRsp;
import io.cygnux.rapid.infra.dto.resp.OrderRsp;
import io.cygnux.rapid.console.controller.ControllerUtil;
import io.cygnux.rapid.console.controller.HttpParam;
import io.cygnux.rapid.console.controller.ResponseStatus;
import io.cygnux.rapid.infra.service.OrderService;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static io.cygnux.rapid.console.controller.ResponseStatus.BAD_REQUEST;
import static io.cygnux.rapid.console.controller.ResponseStatus.INTERNAL_ERROR;
import static io.cygnux.rapid.console.controller.ResponseStatus.OK;

/**
 * [REST] - 订单服务
 */
@Tag(name = "订单服务")
@RestController("orderControllerV1")
@RequestMapping(path = "/order/v1", produces = "application/json;charset=utf-8")
public class OrderController {

    private static final Logger log = Log4j2LoggerFactory.getLogger(OrderController.class);

    @Resource
    private OrderService orderService;

    /**
     * 查询订单
     *
     * @param accountId      用户ID    [int 必须项]
     * @param strategyId     策略ID    [int 可选项]
     * @param tradingDay     交易日    [int 可选项, 8位日期格式:YYYYMMDD]
     * @param instrumentCode 交易标的  [String 可选项, 股票代码/期货代码]
     * @param status         订单状态  [int 可选项, 1:委托状态, 2:成交状态, 0或不传为全部状态订单]
     * @return List<OrderRsp>
     */
    @GetMapping
    public List<OrderRsp> getOrder(@RequestParam(HttpParam.USERID) int userid,
                                   @RequestParam(HttpParam.ACCOUNT_ID) int accountId,
                                   @RequestParam(HttpParam.STRATEGY_ID) int strategyId,
                                   @RequestParam(HttpParam.INSTRUMENT_CODE) String instrumentCode,
                                   @RequestParam(HttpParam.TRADING_DAY) int tradingDay,
                                   @RequestParam(HttpParam.STATUS) int status) {
        if (ControllerUtil.paramIsNull(accountId))
            return null;
        return orderService.getOrders(userid, accountId, strategyId, instrumentCode, tradingDay);
    }

    /**
     * 获取订单最新状态列表
     *
     * @param strategyId 策略ID [int 必须项]
     * @param tradingDay 交易日
     * @return List<OrderEventRsp>
     */
    @GetMapping(path = "/event")
    public List<OrderEventRsp> getOrderEvents(@RequestParam(HttpParam.STRATEGY_ID) int strategyId,
                                              @RequestParam(HttpParam.TRADING_DAY) int tradingDay) {
        if (ControllerUtil.paramIsNull(strategyId, tradingDay))
            return null;
        // TODO 过滤最后的订单
        return orderService.getOrderEventsByTradingDay(tradingDay);
    }


    /**
     * 委托 [前端调用: 开仓, 平仓, 一键平仓, 手动单笔]
     *
     * @param req NewOrderReq
     * @return ResponseStatus
     */
    @PostMapping(path = "/new", consumes = "application/json;charset=utf-8")
    public ResponseStatus newOrder(@RequestBody NewOrderReq req) {
        return OK;
    }

    /**
     * 批量委托
     *
     * @param req NewBatchOrderReq
     * @return ResponseStatus
     */
    @PostMapping(path = "/batch", consumes = "application/json;charset=utf-8")
    public ResponseStatus newOrder(@RequestBody NewOrderBatchReq req) {
        return OK;
    }

    /**
     * [X]新增订单 [非前端界面调用]
     *
     * @param request HttpServletRequest
     * @return ResponseStatus
     */
    @PutMapping(consumes = "application/json;charset=utf-8")
    public ResponseStatus putOrder(@RequestBody HttpServletRequest request) {
        var order = ControllerUtil.bodyToObject(request, NewOrderReq.class);
        return order == null
                ? BAD_REQUEST : orderService.putOrder(order)
                ? OK : INTERNAL_ERROR;
    }

}
