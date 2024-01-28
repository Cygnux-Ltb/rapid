package io.cygnuxltb.console.controller;

import io.cygnuxltb.console.persistence.entity.SysParamEntity;
import io.cygnuxltb.console.service.ParamService;
import io.cygnuxltb.console.service.StrategyService;
import io.cygnuxltb.protocol.http.response.dto.ParamDTO;
import io.cygnuxltb.protocol.http.response.dto.StrategyDTO;
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
import static io.cygnuxltb.console.controller.util.ControllerUtil.bodyToObject;
import static io.cygnuxltb.protocol.http.ServiceURI.strategy;
import static io.mercury.common.http.MimeType.APPLICATION_JSON_UTF8;
import static io.mercury.common.log4j2.Log4j2LoggerFactory.getLogger;

/**
 * 策略服务
 */
@RestController
@RequestMapping(path = strategy, produces = APPLICATION_JSON_UTF8)
public final class StrategyController {

    private static final Logger log = getLogger(StrategyController.class);

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
        return paramService.getStrategyParams(strategyName, null);
    }

    /**
     * 添加策略参数 (内部接口)
     *
     * @param strategyId int
     * @return boolean
     */
    @PostMapping(path = "/param", consumes = APPLICATION_JSON_UTF8)
    public boolean putParamsByStrategyId(@RequestParam(STRATEGY_ID) int strategyId,
                                         @RequestBody HttpServletRequest request) {
        var params = bodyToObject(request, SysParamEntity.class);
        log.info("func -> putParamsByStrategyId received : {}", params);
        return params != null && paramService.putStrategyParam(params);
    }

}
