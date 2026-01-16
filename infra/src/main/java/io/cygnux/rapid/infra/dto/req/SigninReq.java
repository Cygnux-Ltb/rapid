package io.cygnux.rapid.infra.dto.req;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public final class SigninReq {

    /**
     * 用户名/邮箱/手机号
     */
    private String sign;

    /**
     * 密码
     */
    private String password;

    /**
     * 验证码
     */
    private String verifyCode;

}

