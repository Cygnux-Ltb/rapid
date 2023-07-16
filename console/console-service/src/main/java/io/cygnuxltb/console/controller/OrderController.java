package io.cygnuxltb.console.controller;

import io.cygnuxltb.console.controller.base.ResponseStatus;
import io.cygnuxltb.console.controller.util.ControllerUtil;
import io.cygnuxltb.console.persistence.entity.TblTOrder;
import io.cygnuxltb.console.service.OrderService;
import io.cygnuxltb.protocol.http.request.NewOrderDTO;
import io.cygnuxltb.protocol.http.response.OrderDTO;
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

import static io.cygnuxltb.console.controller.base.HttpParam.INSTRUMENT_CODE;
import static io.cygnuxltb.console.controller.base.HttpParam.INVESTOR_ID;
import static io.cygnuxltb.console.controller.base.HttpParam.STRATEGY_ID;
import static io.cygnuxltb.console.controller.base.HttpParam.TRADING_DAY;
import static io.mercury.common.http.MimeType.APPLICATION_JSON_UTF8;

/**
 * 订单服务接口
 */
@RestController
@RequestMapping(path = "/order", produces = APPLICATION_JSON_UTF8)
public final class OrderController {

    private static final Logger log = Log4j2LoggerFactory.getLogger(OrderController.class);

    @Resource
    private OrderService service;

    /**
     * 查询Order
     *
     * @param strategyId     策略ID
     * @param tradingDay     交易日
     * @param investorId     交易账户
     * @param instrumentCode 交易标的
     * @return List<OrderEntity>
     */
    @GetMapping
    public List<OrderDTO> getOrder(@RequestParam(TRADING_DAY) int tradingDay,
                                   @RequestParam(STRATEGY_ID) int strategyId,
                                   @RequestParam(INVESTOR_ID) String investorId,
                                   @RequestParam(INSTRUMENT_CODE) String instrumentCode) {
        if (ControllerUtil.paramIsNull(strategyId, tradingDay, investorId, instrumentCode))
            return null;
        return service.getOrders(strategyId, investorId, instrumentCode, tradingDay);
    }

    /**
     * 获取订单最新状态
     *
     * @param tradingDay 交易日
     * @param strategyId 策略ID
     * @return List<OrderEventDTO>
     */
    @GetMapping(path = "/event")
    public List<OrderEventDTO> getOrderEvents(@RequestParam(TRADING_DAY) int tradingDay,
                                              @RequestParam(STRATEGY_ID) int strategyId) {
        if (ControllerUtil.paramIsNull(strategyId, tradingDay))
            return null;
        // TODO 过滤最后的订单
        return service.getOrderEventsByTradingDay(tradingDay);
    }


    /**
     * 创建订单 [前端调用: 开仓, 平仓, 一键平仓都通过此接口]
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
        var order = ControllerUtil.bodyToObject(request, TblTOrder.class);
        return order == null
                ? ResponseStatus.BAD_REQUEST : service.putOrder(order)
                ? ResponseStatus.OK : ResponseStatus.INTERNAL_ERROR;
    }

}
