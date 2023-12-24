package io.cygnuxltb.console.controller;

import io.cygnuxltb.console.controller.base.ResponseBean;
import io.cygnuxltb.console.controller.base.ResponseStatus;
import io.cygnuxltb.console.controller.util.ControllerUtil;
import io.cygnuxltb.console.persistence.entity.TrdOrderEntity;
import io.cygnuxltb.console.service.OrderService;
import io.cygnuxltb.protocol.http.request.NewOrderDTO;
import io.cygnuxltb.protocol.http.response.OrderEventDTO;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
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

import static io.cygnuxltb.console.controller.base.HttpParam.ACCOUNT_ID;
import static io.cygnuxltb.console.controller.base.HttpParam.INSTRUMENT_CODE;
import static io.cygnuxltb.console.controller.base.HttpParam.STRATEGY_ID;
import static io.cygnuxltb.console.controller.base.HttpParam.TRADING_DAY;
import static io.cygnuxltb.protocol.http.ServiceURI.ORDER;
import static io.mercury.common.http.MimeType.APPLICATION_JSON_UTF8;

/**
 * 订单服务
 */
@RestController
@RequestMapping(path = ORDER, produces = APPLICATION_JSON_UTF8)
public final class OrderController {

    private static final Logger log = Log4j2LoggerFactory.getLogger(OrderController.class);

    @Resource
    private OrderService service;

    /**
     * 查询订单
     *
     * @param accountId      交易账户ID [int 必须项]
     * @param strategyId     策略ID [int 可选项]
     * @param tradingDay     交易日 [int 可选项, 8位日期格式:YYYYMMDD]
     * @param instrumentCode 交易标的 [String 股票代码/期货代码]
     * @return List<OrderEntity>
     */
    @GetMapping
    public ResponseBean getOrder(@RequestParam(ACCOUNT_ID) int accountId,
                                 @RequestParam(STRATEGY_ID) int strategyId,
                                 @RequestParam(INSTRUMENT_CODE) String instrumentCode,
                                 @RequestParam(TRADING_DAY) int tradingDay) {
        if (ControllerUtil.paramIsNull(accountId))
            return ResponseStatus.BAD_REQUEST.response("参数错误, [accountId]不可为空.");
        return ResponseStatus.OK.responseOf(
                service.getOrders(accountId, strategyId, instrumentCode, tradingDay));
    }

    /**
     * 获取订单最新状态
     *
     * @param strategyId 策略ID [int 必须项]
     * @param tradingDay 交易日
     * @return List<OrderEventDTO>
     */
    @GetMapping(path = "/event")
    public List<OrderEventDTO> getOrderEvents(@RequestParam(STRATEGY_ID) int strategyId,
                                              @RequestParam(TRADING_DAY) int tradingDay) {
        if (ControllerUtil.paramIsNull(strategyId, tradingDay))
            return null;
        // TODO 过滤最后的订单
        return service.getOrderEventsByTradingDay(tradingDay);
    }


    /**
     * 创建订单 [前端调用: 开仓, 平仓, 一键平仓]
     *
     * @param newOrderDTO NewOrderDTO
     * @return ResponseStatus
     */
    @PostMapping(consumes = APPLICATION_JSON_UTF8)
    public ResponseStatus newOrder(NewOrderDTO newOrderDTO) {
        return ResponseStatus.OK;
    }

    /**
     * 新增订单 [非前端界面调用]
     *
     * @param request HttpServletRequest
     * @return ResponseEntity<Object>
     */
    @PutMapping(consumes = APPLICATION_JSON_UTF8)
    public ResponseStatus putOrder(@RequestBody HttpServletRequest request) {
        var order = ControllerUtil.bodyToObject(request, TrdOrderEntity.class);
        return order == null
                ? ResponseStatus.BAD_REQUEST : service.putOrder(order)
                ? ResponseStatus.OK : ResponseStatus.INTERNAL_ERROR;
    }

}
