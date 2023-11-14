package io.cygnuxltb.console.controller;

import io.cygnuxltb.console.controller.base.ResponseBean;
import io.cygnuxltb.console.controller.base.ResponseStatus;
import io.cygnuxltb.console.service.UserService;
import io.cygnuxltb.protocol.http.response.status.SignInStatus;
import io.mercury.common.datetime.EpochTime;
import io.mercury.common.http.MimeType;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户服务
 */
@RestController
@RequestMapping(path = "/user", produces = MimeType.APPLICATION_JSON_UTF8)
public class UserController {

    private static final Logger log = Log4j2LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService service;

    /**
     * 用户登陆
     *
     * @param sign     用户名/邮箱/手机号
     * @param password 密码
     * @return SignInStatus
     */
    @PostMapping(path = "/signin")
    public SignInStatus signin(String sign, String password) {
        boolean signIn = service.signIn(sign, password);
        return new SignInStatus()
                .setAuthenticated(signIn)
                .setSecurityCode(EpochTime.getEpochMillis())
                .setMessage(signIn ? "SUCCESSFUL" : "FAILED");
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
