package io.cygnuxltb.protocol.http.response.status;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class SignInStatus {

    /**
     * 是否已验证
     */
    private boolean authenticated;

    /**
     * 消息内容
     */
    private String message;

    /**
     * 安全码
     */
    private long securityCode;

}
