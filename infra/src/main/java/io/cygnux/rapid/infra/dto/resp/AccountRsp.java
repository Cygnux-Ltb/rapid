package io.cygnux.rapid.infra.dto.resp;

import io.cygnux.rapid.infra.dto.common.GeneralParamObj;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 交易账户
 *
 * @author yellow013
 */
@Getter
@Setter
@Accessors(chain = true)
public final class AccountRsp {

    /**
     * 账户ID
     */
    private int accountId;

    /**
     * 账户名称
     */
    private String accountName;

    /**
     * 经纪商代码
     */
    private String brokerCode;

    /**
     * 投资者代码
     */
    private String investorCode;

    /**
     * 账户余额
     */
    private double balance;

    /**
     * 账户信用
     */
    private double credit;

    /**
     * 交易适配器ID
     */
    private int adaptorId;

    /**
     * Remark
     */
    private String remark = "";

    /**
     * 交易账户参数列表
     */
    private List<GeneralParamObj> paramList;

}
