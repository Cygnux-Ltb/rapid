package io.cygnuxltb.console.controller;

import io.cygnuxltb.console.controller.base.ResponseBean;
import io.cygnuxltb.console.controller.base.ResponseStatus;
import io.cygnuxltb.console.service.InstrumentService;
import io.cygnuxltb.protocol.http.request.InstrumentPrice;
import io.cygnuxltb.protocol.http.response.dto.InstrumentDTO;
import io.cygnuxltb.protocol.http.response.dto.InstrumentSettlementDTO;
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

import static io.cygnuxltb.console.controller.base.HttpParam.INSTRUMENT_CODE;
import static io.cygnuxltb.console.controller.base.HttpParam.TRADING_DAY;
import static io.cygnuxltb.console.controller.base.ResponseStatus.BAD_REQUEST;
import static io.cygnuxltb.console.controller.base.ResponseStatus.OK;
import static io.cygnuxltb.console.controller.util.ControllerUtil.bodyToObject;
import static io.cygnuxltb.console.controller.util.ControllerUtil.illegalInstrumentCode;
import static io.cygnuxltb.console.controller.util.ControllerUtil.paramIsNull;
import static io.cygnuxltb.protocol.http.ServiceURI.instrument;
import static io.mercury.common.http.MimeType.APPLICATION_JSON_UTF8;

/**
 * 交易标的服务
 *
 * @apiNote
 */
@RestController
@RequestMapping(path = instrument, produces = APPLICATION_JSON_UTF8)
public final class InstrumentController {

    private static final Logger log = Log4j2LoggerFactory.getLogger(InstrumentController.class);

    @Resource
    private InstrumentService service;


    /**
     * 获取交易标的
     *
     * @return List<InstrumentDTO>
     */
    @GetMapping(path = "/all")
    public List<InstrumentDTO> getAllInstrument() {
        return service.getAllInstrument();
    }

    /**
     * 获取交易标的
     *
     * @param instrumentCode 交易标的 () [查询多个标的使用','分割]
     * @return List<InstrumentDTO>
     */
    @GetMapping
    public List<InstrumentDTO> getInstrument(
            @RequestParam(INSTRUMENT_CODE) String instrumentCode) {
        if (paramIsNull(instrumentCode))
            return null;
        return service.getInstrument(instrumentCode);
    }


    /**
     * 获取结算信息
     *
     * @param instrumentCode 交易标的 () [查询多个标的使用','分割]
     * @param tradingDay     int
     * @return List<InstrumentSettlementDTO>
     */
    @GetMapping(path = "/settlement")
    public List<InstrumentSettlementDTO> getSettlementPrice(
            @RequestParam(TRADING_DAY) int tradingDay,
            @RequestParam(INSTRUMENT_CODE) String instrumentCode) {
        if (paramIsNull(tradingDay, instrumentCode))
            return null;
        return service.getInstrumentSettlement(tradingDay, instrumentCode);
    }

    /**
     * 获取最新价格
     *
     * @param instrumentCode 交易标的 [查询多个标的使用','分割]
     * @return ResponseBean
     */
    @GetMapping(path = "/price")
    public ResponseBean getLastPrice(
            @RequestParam(INSTRUMENT_CODE) String instrumentCode) {
        if (illegalInstrumentCode(instrumentCode, log))
            return BAD_REQUEST.response("[instrumentCode]不可为空");
        return OK.responseOf(service.getLastPrice(instrumentCode.split(",")));
    }

    /**
     * 更新最新价格 (內部接口)
     *
     * @param request HttpServletRequest
     * @return ResponseStatus
     */
    @PutMapping(path = "/price", produces = APPLICATION_JSON_UTF8)
    public ResponseStatus putLastPrice(@RequestBody HttpServletRequest request) {
        var price = bodyToObject(request, InstrumentPrice.class);
        if (price == null)
            return BAD_REQUEST;
        service.putLastPrice(price.getInstrumentCode(), price.getLastPrice());
        return OK;
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
        return OK;
    }

}
