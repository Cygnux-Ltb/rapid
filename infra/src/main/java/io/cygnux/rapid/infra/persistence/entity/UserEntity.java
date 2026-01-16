package io.cygnux.rapid.infra.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import static io.cygnux.rapid.core.types.field.ValueLimitation.MIN_USERID;
import static io.cygnux.rapid.infra.persistence.NonnullColumn.SUB_ACCOUNT_ID;
import static io.cygnux.rapid.infra.persistence.NonnullColumn.USERID;

/**
 * 系统用户表
 * User Entity
 *
 * @author yellow013
 */
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "t_user")
public final class UserEntity {

    /**
     * Userid
     */
    @Id
    @Min(MIN_USERID)
    @Column(name = USERID)
    private int userid;

    /**
     * Username
     */
    @NotBlank
    @Column(name = "USERNAME")
    private String username;

    /**
     * Password
     */
    @NotBlank
    @Column(name = "PASSWORD")
    private String password;

    /**
     * Email
     */
    @Email
    @Column(name = "EMAIL")
    private String email;

    /**
     * Phone
     */
    @Column(name = "PHONE")
    private String phone;

    /**
     * SubAccountId
     */
    @Column(name = SUB_ACCOUNT_ID)
    private int subAccountId;

    /**
     * Remark
     */
    @Column(name = "REMARK")
    private String remark = "";

}
