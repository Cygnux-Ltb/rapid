package io.cygnux.rapid.infra.dto.resp;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 用户表
 * User Entity
 *
 * @author yellow013
 */
@Getter
@Setter
@Accessors(chain = true)
public final class UserRsp {

    /**
     * 用户ID
     */
    private int userid;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 电子邮件
     */
    private String email;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 子账户ID
     */
    private int subAccountId;

}
