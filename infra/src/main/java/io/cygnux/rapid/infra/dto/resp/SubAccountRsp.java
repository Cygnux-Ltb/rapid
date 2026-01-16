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
public final class SubAccountRsp {

    /**
     * 子账户ID
     */
    private int subAccountId;

    /**
     * 账户名称
     */
    private String subAccountName;

    /**
     * 总可用余额
     */
    private double totalAvailable;

    /**
     * 每日可用余额
     */
    private double dailyAvailable;

    /**
     * Remark
     */
    private String remark = "";

    /**
     * 交易账户参数列表
     */
    private List<GeneralParamObj> paramList;

}
