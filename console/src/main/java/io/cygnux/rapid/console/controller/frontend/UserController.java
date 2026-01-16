package io.cygnux.rapid.console.controller.frontend;

import io.cygnux.rapid.infra.dto.req.SigninReq;
import io.cygnux.rapid.infra.dto.resp.SigninStatus;
import io.cygnux.rapid.console.controller.ResponseBean;
import io.cygnux.rapid.console.controller.ResponseStatus;
import io.cygnux.rapid.infra.service.UserService;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.util.Objects.requireNonNull;

/**
 * [REST] - 用户服务
 */
@Tag(name = "用户前端服务")
@RestController("userControllerV1")
@RequestMapping(path = "/user/v1", produces = "application/json;charset=utf-8")
public class UserController {

    private static final Logger log = Log4j2LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService userService;

    /**
     * 用户登陆
     *
     * @param req UserSigninReq
     * @return SigninStatus
     */
    @PostMapping(path = "/signin")
    public SigninStatus signin(@RequestBody SigninReq req) {
        requireNonNull(req);
        return userService.signin(req.getSign(), req.getPassword(), req.getVerifyCode());
    }

    /**
     * 用户注册, 当前不支持新用户注册
     *
     * @param sign     标识
     * @param type     标识类型
     * @param password 密码
     * @return ResponseBean
     */
    @PostMapping(path = "/signup")
    public ResponseBean signup(String sign, int type, String password) {
        return ResponseStatus.FORBIDDEN.responseOf();
    }

}
