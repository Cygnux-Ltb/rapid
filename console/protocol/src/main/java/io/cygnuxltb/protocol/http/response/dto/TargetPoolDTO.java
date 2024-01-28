package io.cygnuxltb.protocol.http.response;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 用户目标池
 */
@Getter
@Setter
@Accessors(chain = true)
public class TargetPoolDTO {

    /**
     * 用户ID
     */
    private int userId;

    /**
     * 投资组合(股票池/目标池)名称
     */
    private String portfolioName;

    /**
     * 目标池股票列表
     */
    private List<TargetObj> targetList;

    /**
     * 目标对象
     */
    @Getter
    @Setter
    @Accessors(chain = true)
    public static class TargetObj {

        /**
         * 标的代码
         */
        private String instrumentCode;

        /**
         * 标的名称
         */
        private String instrumentName;

        /**
         * 现价
         */
        private double currentPrice;

        /**
         * 净持仓
         */
        private double netPos;

        /**
         * 涨跌幅
         */
        private int change;

    }

}
