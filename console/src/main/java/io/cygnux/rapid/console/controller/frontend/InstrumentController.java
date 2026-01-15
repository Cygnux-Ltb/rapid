package io.cygnux.rapid.console.controller.frontend;

import io.cygnux.rapid.console.controller.ControllerUtil;
import io.cygnux.rapid.console.controller.ResponseStatus;
import io.cygnux.rapid.infra.dto.resp.InstrumentRsp;
import io.cygnux.rapid.infra.dto.resp.InstrumentSettlementRsp;
import io.cygnux.rapid.infra.dto.resp.LastPriceRsp;
import io.cygnux.rapid.infra.dto.resp.LastPriceRsp.LastPriceObj;
import io.cygnux.rapid.infra.service.InstrumentService;
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

import static io.cygnux.rapid.console.controller.HttpParam.INSTRUMENT_CODE;
import static io.cygnux.rapid.console.controller.HttpParam.TRADING_DAY;
import static io.cygnux.rapid.console.controller.ResponseStatus.BAD_REQUEST;
import static io.cygnux.rapid.console.controller.ResponseStatus.OK;

/**
 * [REST] - 交易标的服务
 *
 * @apiNote
 */
@Tag(name = "交易标的服务")
@RestController("instrumentControllerV1")
@RequestMapping(path = "/instrument/v1", produces = "application/json;charset=utf-8")
public class InstrumentController {

    private static final Logger log = Log4j2LoggerFactory.getLogger(InstrumentController.class);

    @Resource
    private InstrumentService instrumentService;

    /**
     * 获取交易标的
     *
     * @return List<InstrumentRsp>
     */
    @GetMapping(path = "/all")
    public List<InstrumentRsp> getAllInstrument() {
        return instrumentService.getAllInstrument();
    }

    /**
     * 获取交易标的
     *
     * @param instrumentCode 交易标的 [查询多个交易标的使用','分割]
     * @return List<InstrumentRsp>
     */
    @GetMapping
    public List<InstrumentRsp> getInstrument(@RequestParam(INSTRUMENT_CODE) String instrumentCode) {
        if (ControllerUtil.paramIsNull(instrumentCode))
            return null;
        return instrumentService.getInstrument(instrumentCode);
    }


    /**
     * 获取结算信息
     *
     * @param tradingDay     int
     * @param instrumentCode 交易标的 [查询多个交易标的使用','分割]
     * @return List<InstrumentSettlementRsp>
     */
    @GetMapping(path = "/settlement")
    public List<InstrumentSettlementRsp> getSettlement(@RequestParam(TRADING_DAY) int tradingDay,
                                                       @RequestParam(INSTRUMENT_CODE) String instrumentCode) {
        if (ControllerUtil.paramIsNull(tradingDay, instrumentCode))
            return null;
        return instrumentService.getInstrumentSettlement(tradingDay, instrumentCode);
    }

    /**
     * 获取最新价格
     *
     * @param instrumentCode 交易标的 [查询多个交易标的使用','分割]
     * @return LastPriceRsp
     */
    @GetMapping(path = "/price")
    public LastPriceRsp getLastPrice(@RequestParam(INSTRUMENT_CODE) String instrumentCode) {
        if (ValueValidator.illegalInstrumentCode(instrumentCode, log))
            throw new IllegalArgumentException("[instrumentCode]不可为空");
        return instrumentService.getLastPrice(instrumentCode.split(","));
    }

    /**
     * 更新最新价格 (內部接口)
     *
     * @param request HttpServletRequest
     * @return ResponseStatus
     */
    @PutMapping(path = "/price", produces = "application/json;charset=utf-8")
    public ResponseStatus putLastPrice(@RequestBody HttpServletRequest request) {
        var price = ControllerUtil.bodyToObject(request, LastPriceObj.class);
        if (price == null)
            return BAD_REQUEST;
        instrumentService.putLastPrice(price.getInstrumentCode(), price.getLastPrice());
        return OK;
    }

    /**
     * 获取交易费用
     *
     * @param instrumentCode 交易标的 [查询多个标的使用','分割]
     * @return List<InstrumentRsp>
     */
    @GetMapping(path = "/fee")
    public List<InstrumentRsp> getInstrumentFee(@RequestParam(INSTRUMENT_CODE) String instrumentCode) {
        return instrumentService.getInstrument(instrumentCode);
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
