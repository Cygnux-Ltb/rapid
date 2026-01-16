package io.cygnux.rapid.infra.dto.resp;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;


/**
 * 登录状态
 */
@Getter
@Setter
@Accessors(chain = true)
public final class SigninStatus {

    /**
     * 是否已验证
     */
    private boolean authenticated;

    /**
     * 用户ID
     */
    private int userid;

    /**
     * 消息内容
     */
    private String message;

    /**
     * 安全码
     */
    private long securityCode;

}
