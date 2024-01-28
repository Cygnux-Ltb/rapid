package io.cygnuxltb.console.controller;

import io.cygnuxltb.console.controller.base.ResponseBean;
import io.cygnuxltb.console.controller.base.ResponseStatus;
import io.cygnuxltb.console.service.UserService;
import io.cygnuxltb.protocol.http.response.status.SigninStatus;
import io.mercury.common.datetime.EpochTime;
import io.mercury.common.http.MimeType;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static io.cygnuxltb.protocol.http.ServiceURI.user;
import static io.mercury.common.log4j2.Log4j2LoggerFactory.getLogger;

/**
 * 用户服务
 */
@RestController
@RequestMapping(path = user, produces = MimeType.APPLICATION_JSON_UTF8)
public class UserController {

    private static final Logger log = getLogger(UserController.class);

    @Resource
    private UserService service;

    /**
     * 用户登陆
     *
     * @param sign     用户名/邮箱/手机号
     * @param password 密码
     * @return SigninStatus
     */
    @PostMapping(path = "/signin")
    public SigninStatus signin(String sign, String password) {
        int signin = service.signin(sign, password);
        SigninStatus status = new SigninStatus()
                .setAuthenticated(false)
                .setSecurityCode(EpochTime.getEpochMillis());
        return switch (signin) {
            case -1 -> status
                    .setMessage("密码错误");
            case 0 -> status
                    .setMessage("用户不存在");
            default -> status
                    .setAuthenticated(true)
                    .setMessage("验证成功");
        };
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
        return ResponseStatus.FORBIDDEN.response();
    }

}
