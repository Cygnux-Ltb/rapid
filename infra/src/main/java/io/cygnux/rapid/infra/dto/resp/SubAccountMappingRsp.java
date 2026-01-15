package io.cygnux.rapid.infra.dto.resp;

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
public final class SubAccountMappingRsp {

    /**
     * 子账户ID
     */
    private int subAccountId;

    /**
     * 子账户映射列表
     */
    private List<SubAccountMappingObj> subAccountMappings;

    /**
     * 头寸对象
     */
    @Getter
    @Setter
    @Accessors(chain = true)
    public static class SubAccountMappingObj {

        /**
         * 映射账户ID
         */
        private int accountId;

        /**
         * 账户在此子账户的可用金额
         */
        private double availableBalance;

        /**
         * Remark
         */
        private String remark = "";

    }

}
