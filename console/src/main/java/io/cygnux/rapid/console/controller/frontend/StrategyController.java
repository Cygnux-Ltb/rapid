package io.cygnux.rapid.console.controller.frontend;

import io.cygnux.rapid.console.controller.ControllerUtil;
import io.cygnux.rapid.infra.dto.req.StrategyParamReq;
import io.cygnux.rapid.infra.dto.resp.ParamRsp;
import io.cygnux.rapid.infra.dto.resp.StrategyRsp;
import io.cygnux.rapid.infra.service.StrategyService;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.swagger.v3.oas.annotations.tags.Tag;
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

import static io.cygnux.rapid.console.controller.HttpParam.STRATEGY_ID;

/**
 * [REST] - 策略服务
 */
@Tag(name = "策略前端服务")
@RestController("strategyControllerV1")
@RequestMapping(path = "/strategy/v1", produces = "application/json;charset=utf-8")
public class StrategyController {

    private static final Logger log = Log4j2LoggerFactory.getLogger(StrategyController.class);

    @Resource
    private StrategyService strategyService;

    /**
     * 获取全部策略
     *
     * @return List<StrategyRsp>
     */
    @GetMapping
    public List<StrategyRsp> getAllStrategy() {
        return strategyService.getAllStrategy();
    }

    /**
     * 获取策略
     *
     * @param strategyId 策略ID
     * @return StrategyRsp
     */
    @GetMapping(path = "/get")
    public StrategyRsp getStrategyById(@RequestParam(STRATEGY_ID) int strategyId) {
        return strategyService.getStrategy(strategyId);
    }

    /**
     * 根据策略名称获取策略相关参数
     *
     * @param strategyName String
     * @return List<ParamRsp>
     */
    @GetMapping("/param")
    public List<ParamRsp> getParamsByStrategyName(@RequestParam("strategyName") String strategyName) {
        return null;//paramService.getStrategyParam(strategyName, null);
    }

    /**
     * 添加策略参数 (内部接口)
     *
     * @param strategyId int
     * @return boolean
     */
    @PostMapping(path = "/param", consumes = "application/json;charset=utf-8")
    public boolean putParamsByStrategyId(@RequestParam(STRATEGY_ID) int strategyId,
                                         @RequestBody HttpServletRequest request) {
        var params = ControllerUtil.bodyToObject(request, StrategyParamReq.class);
        log.info("func -> putParamsByStrategyId received : {}", params);
        return params != null && strategyService.putStrategyParam(params);
    }

}
