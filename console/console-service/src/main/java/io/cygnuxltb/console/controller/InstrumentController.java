package io.cygnuxltb.console.controller;

import io.cygnuxltb.console.controller.base.ResponseStatus;
import io.cygnuxltb.console.controller.util.ControllerUtil;
import io.cygnuxltb.console.service.InstrumentService;
import io.cygnuxltb.protocol.http.request.InstrumentPrice;
import io.cygnuxltb.protocol.http.response.InstrumentDTO;
import io.cygnuxltb.protocol.http.response.InstrumentSettlementDTO;
import io.mercury.common.lang.Throws;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.mercury.common.util.StringSupport;
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

import static io.cygnuxltb.console.controller.base.HttpParam.INSTRUMENT_CODE;
import static io.cygnuxltb.console.controller.base.HttpParam.TRADING_DAY;
import static io.mercury.common.http.MimeType.APPLICATION_JSON_UTF8;

/**
 * 交易标的接口
 */
@RestController
@RequestMapping(path = "/instrument", produces = APPLICATION_JSON_UTF8)
public final class InstrumentController {

    private static final Logger log = Log4j2LoggerFactory.getLogger(InstrumentController.class);

    @Resource
    private InstrumentService service;

    /**
     * 获取结算信息
     *
     * @param instrumentCode 交易标的 [查询多个标的使用','分割]
     * @param tradingDay     int
     * @return List<InstrumentSettlementDTO>
     */
    @GetMapping(path = "/settlement")
    public List<InstrumentSettlementDTO> getSettlementPrice(
            @RequestParam(TRADING_DAY) int tradingDay,
            @RequestParam(INSTRUMENT_CODE) String instrumentCode) {
        if (ControllerUtil.paramIsNull(tradingDay, instrumentCode))
            return null;
        return service.getInstrumentSettlement(tradingDay, instrumentCode);
    }

    /**
     * 获取最新价格
     *
     * @param instrumentCode 交易标的 [查询多个标的使用','分割]
     * @return List<InstrumentPrice>
     */
    @GetMapping(path = "/price")
    public List<InstrumentPrice> getLastPrice(
            @RequestParam(INSTRUMENT_CODE) String instrumentCode) {
        if (StringSupport.isNullOrEmpty(instrumentCode))
            Throws.illegalArgument("instrumentCodes");
        return service.getLastPrice(instrumentCode.split(","));
    }

    /**
     * 更新最新价格 (內部接口)
     *
     * @param request HttpServletRequest
     * @return ResponseStatus
     */
    @PutMapping(path = "/price", produces = APPLICATION_JSON_UTF8)
    public ResponseStatus putLastPrice(@RequestBody HttpServletRequest request) {
        var price = ControllerUtil.bodyToObject(request, InstrumentPrice.class);
        if (price == null)
            return ResponseStatus.BAD_REQUEST;
        service.putLastPrice(price.getInstrumentCode(), price.getLastPrice());
        return ResponseStatus.OK;
    }

    /**
     * 获取交易费用
     *
     * @param instrumentCode 交易标的 [查询多个标的使用','分割]
     * @return List<InstrumentDTO>
     */
    @GetMapping(path = "/fee")
    public List<InstrumentDTO> getSymbolTradingFeeByName(
            @RequestParam(INSTRUMENT_CODE) String instrumentCode) {
        return service.getInstrument(instrumentCode);
    }


    /**
     * 获取可交易标的
     *
     * @param tradingDay     交易日
     * @param instrumentCode 交易标的 [查询多个标的使用','分割]
     * @return ResponseStatus
     */
    @GetMapping(path = "/tradable")
    public ResponseStatus getTradableInstrument(@RequestParam(TRADING_DAY) int tradingDay,
                                                @RequestParam(INSTRUMENT_CODE) String instrumentCode) {
        return ResponseStatus.OK;
    }

}
