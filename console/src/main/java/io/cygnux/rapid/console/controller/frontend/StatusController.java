package io.cygnux.rapid.console.controller.frontend;

import io.cygnux.rapid.console.controller.ControllerUtil;
import io.cygnux.rapid.console.controller.ResponseStatus;
import io.cygnux.rapid.infra.dto.req.TradingSwitchesReq;
import io.cygnux.rapid.infra.dto.wrap.OutboxMessage;
import io.cygnux.rapid.infra.dto.wrap.OutboxTitle;
import io.mercury.common.collections.MutableMaps;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.mercury.serialization.json.JsonWriter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentMap;

import static io.cygnux.rapid.console.controller.HttpParam.PROD_CODE;

/**
 * [REST] - 交易系统状态服务
 */
@Tag(name = "交易系统状态服务")
@RestController("statusControllerV1")
@RequestMapping(path = "/status/v1", produces = "application/json;charset=utf-8")
public class StatusController {

    private static final Logger log = Log4j2LoggerFactory.getLogger(StatusController.class);

    private final ConcurrentMap<Integer, TradingSwitchesReq> StrategySwitchMap = MutableMaps.newConcurrentMap();

    /**
     * 获取全部策略状态
     *
     * @return Collection<StrategySwitch>
     */
    @GetMapping
    public Collection<TradingSwitchesReq> allStrategySwitch() {
        return StrategySwitchMap.values();
    }

    /**
     * 发送状态指令
     *
     * @param request HttpServletRequest
     * @return ResponseStatus
     */
    @PutMapping(path = "/command", consumes = "application/json;charset=utf-8")
    public ResponseStatus statusCommand(@RequestBody HttpServletRequest request) {
        var strategySwitchList = ControllerUtil.bodyToList(request, TradingSwitchesReq.class);
        if (strategySwitchList == null) {
            return ResponseStatus.BAD_REQUEST;
        }
        // 将传入的StrategySwitch按照sysId分组
        Map<Integer, List<TradingSwitchesReq>> strategySwitchListMap = new HashMap<>();
        for (TradingSwitchesReq strategySwitch : strategySwitchList) {
            int cygId = strategySwitch.getProductId();
            if (strategySwitchListMap.containsKey(cygId)) {
                strategySwitchListMap.get(cygId).add(strategySwitch);
            } else {
                List<TradingSwitchesReq> strategySwitchForProdId = new ArrayList<>();
                strategySwitchForProdId.add(strategySwitch);
                strategySwitchListMap.put(cygId, strategySwitchForProdId);
            }
        }
        // 按照sysId分别发送策略开关
        for (Integer sysId : strategySwitchListMap.keySet()) {
            //Publisher<String, String> publisher = CommandDispatcher.GROUP_INSTANCE.getMember(cygId);
            String msg = JsonWriter
                    .toJson(new OutboxMessage<>()
                            .setTitle(OutboxTitle.STRATEGY_SWITCH.name())
                            .setContent(strategySwitchListMap.get(sysId)));
            log.info("StrategySwitch : {}", msg);
            //publisher.publish(msg);
        }
        return ResponseStatus.OK;
    }

    /**
     * 更新状态
     *
     * @param productId int
     * @param request   HttpServletRequest
     * @return ResponseStatus
     */
    @PutMapping("/update")
    public ResponseStatus updateStatus(@RequestParam(PROD_CODE) int productId,
                                       @RequestBody HttpServletRequest request) {
        TradingSwitchesReq strategySwitch = ControllerUtil.bodyToObject(request, TradingSwitchesReq.class);
        Objects.requireNonNull(strategySwitch, "Input StrategySwitch is null");
        strategySwitch.setProductId(productId);
        StrategySwitchMap.put(productId, strategySwitch);
        return ResponseStatus.OK;
    }

}