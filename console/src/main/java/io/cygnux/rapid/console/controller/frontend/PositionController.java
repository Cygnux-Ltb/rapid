package io.cygnux.rapid.console.controller.frontend;

import io.cygnux.rapid.infra.dto.req.PositionsCloseReq;
import io.cygnux.rapid.infra.dto.resp.PositionsRsp;
import io.cygnux.rapid.console.controller.ResponseStatus;
import io.cygnux.rapid.infra.service.PositionService;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static io.cygnux.rapid.console.controller.HttpParam.USERID;
import static io.cygnux.rapid.console.controller.ResponseStatus.OK;
import static io.mercury.common.constant.MimeTypeConst.APPLICATION_JSON_UTF8;

/**
 * [REST] - 仓位服务
 */
@Tag(name = "仓位服务")
@RestController("positionControllerV1")
@RequestMapping(path = "/position/v1", produces = APPLICATION_JSON_UTF8)
public class PositionController {

    private static final Logger log = Log4j2LoggerFactory.getLogger(PositionController.class);

    @Resource
    private PositionService positionService;

    /**
     * 查询当前持仓 ([page1.jpg]-持仓板块)
     *
     * @param userid 用户ID
     * @return PositionsDTO
     */
    @GetMapping("/current")
    public PositionsRsp getCurrentPosition(@RequestParam(USERID) int userid) {
        return positionService.getPosition(userid);
    }


    /**
     * 持仓平仓操作
     * <p>
     * 传[用户ID]时; 动作为:全部平仓, [一键平仓]功能使用
     * 传[用户ID],[投资标的代码]时; 动作为:平仓所有指定投资标的的仓位
     * 传[用户ID],[投资标的代码],[方向]时; 动作为:平仓指定投资标的和指定方向的仓位
     * 传[用户ID],[投资标的代码],[方向],[数量]时; 动作为:平仓指定数量的指定投资标的和指定方向的仓位和
     *
     * @param req PositionsCloseRequest
     * @return ResponseStatus
     */
    @PostMapping(path = "/close", consumes = APPLICATION_JSON_UTF8)
    public ResponseStatus getCurrentPosition(@RequestBody PositionsCloseReq req) {
        return OK;
    }

}
