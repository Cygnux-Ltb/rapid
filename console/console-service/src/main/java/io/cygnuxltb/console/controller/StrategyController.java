package io.cygnuxltb.console.controller;

import io.cygnuxltb.console.controller.util.ControllerUtil;
import io.cygnuxltb.console.persistence.entity.TblSysParam;
import io.cygnuxltb.console.service.ParamService;
import io.cygnuxltb.console.service.StrategyService;
import io.cygnuxltb.protocol.http.response.ParamDTO;
import io.cygnuxltb.protocol.http.response.StrategyDTO;
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

import static io.cygnuxltb.console.controller.base.HttpParam.STRATEGY_ID;
import static io.mercury.common.http.MimeType.APPLICATION_JSON_UTF8;

/**
 * 策略服务
 */
@RestController
@RequestMapping(path = "/strategy", produces = APPLICATION_JSON_UTF8)
public final class StrategyController {

    private static final Logger log = Log4j2LoggerFactory.getLogger(StrategyController.class);

    @Resource
    private StrategyService strategyService;

    @Resource
    private ParamService paramService;

    /**
     * 获取全部策略
     *
     * @return List<StrategyDTO>
     */
    @GetMapping
    public List<StrategyDTO> getAllStrategy() {
        return strategyService.getAllStrategy();
    }

    /**
     * 获取策略
     *
     * @param strategyId 策略ID
     * @return StrategyDTO
     */
    @GetMapping(path = "/get")
    public StrategyDTO getStrategyById(@RequestParam(STRATEGY_ID) int strategyId) {
        return strategyService.getStrategy(strategyId);
    }

    /**
     * 根据策略名称获取策略相关参数
     *
     * @param strategyName String
     * @return List<ParamDTO>
     */
    @GetMapping("/param")
    public List<ParamDTO> getParamsByStrategyId(@RequestParam("strategyName") String strategyName) {
        return paramService.getStrategyParams(strategyName);
    }

    /**
     * 添加策略参数 (内部接口)
     *
     * @param strategyId int
     * @return HttpServletRequest
     */
    @PostMapping(path = "/param", consumes = APPLICATION_JSON_UTF8)
    public boolean putParamsByStrategyId(@RequestParam(STRATEGY_ID) int strategyId,
                                         @RequestBody HttpServletRequest request) {
        var params = ControllerUtil.bodyToObject(request, TblSysParam.class);

        log.info("putParamsByStrategyId recv : {}", params);
        return params != null && paramService.putStrategyParam(params);
    }

}
