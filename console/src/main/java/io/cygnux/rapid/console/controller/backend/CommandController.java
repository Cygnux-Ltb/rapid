package io.cygnux.rapid.console.controller.backend;

import io.cygnux.rapid.console.controller.ControllerUtil;
import io.cygnux.rapid.console.controller.ResponseStatus;
import io.cygnux.rapid.infra.component.CommandDispatcher;
import io.cygnux.rapid.infra.dto.wrap.OutboxMessage;
import io.cygnux.rapid.infra.dto.wrap.OutboxTitle;
import io.cygnux.rapid.infra.persistence.entity.SubAccountParamEntity;
import io.cygnux.rapid.infra.service.ParamService;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.mercury.serialization.json.JsonWriter;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import static io.cygnux.rapid.console.controller.HttpParam.PROD_ID;

/**
 * [UI] - 系统指令服务[X]
 */
@RestController("commandControllerV1b")
@RequestMapping(path = "/command/v1b")
public class CommandController {

    private static final Logger log = Log4j2LoggerFactory.getLogger(CommandController.class);

    @Resource
    private CommandDispatcher dispatcher;

    @Resource
    private ParamService paramService;

    /**
     * 更新参数 [内部接口]
     *
     * @param productId 产品ID
     * @param params    参数列表
     * @return ResponseStatus
     */
    @PostMapping(path = "/param",
            consumes = "application/json;charset=utf-8",
            produces = "application/json;charset=utf-8")
    public ResponseStatus updateParam(@RequestParam(PROD_ID) int productId,
                                      @RequestBody List<SubAccountParamEntity> params) {
        // 获取Publisher
        //dispatcher.sendCommand();
        //Publisher<String, String> publisher = GROUP_INSTANCE.getMember(cygId);
        // 转换为需要发送的发件箱消息
        String msg = JsonWriter.toJson(new OutboxMessage<>()
                .setTitle(OutboxTitle.UPDATE_STRATEGY_PARAMS.name())
                .setContent(params));
        // 发送消息
        //publisher.publish(msg);
        // 返回Put成功标识
        return ResponseStatus.UPDATED;
    }

    /**
     * 安全更新参数
     *
     * @param request HttpServletRequest
     * @return ResponseStatus
     */
    @PutMapping(path = "/safe",
            consumes = "application/json;charset=utf-8",
            produces = "application/json;charset=utf-8")
    public ResponseStatus updateParamSafe(@RequestBody HttpServletRequest request) {
        var strategyParam = ControllerUtil.bodyToObject(request, SubAccountParamEntity.class);
        if (strategyParam == null)
            return ResponseStatus.BAD_REQUEST;
        log.info("method updateParamSafe received : {}", strategyParam);
//        return switch (paramService.updateParamSafe(strategyParam)) {
//            // 更新成功返回Ok状态码
//            case 0 -> ResponseStatus.UPDATED;
//            // 返回错误参数状态码
//            case -1 -> ResponseStatus.BAD_REQUEST;
//            // 否则返回服务器内部错误状态码
//            default -> ResponseStatus.INTERNAL_ERROR;
//        };
        return ResponseStatus.OK;
    }

    public static void main(String[] args) throws IOException {
        ProcessBuilder pb = new ProcessBuilder("ls");
        pb.redirectErrorStream(true);
        Process proc = pb.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
    }

}
