package io.cygnuxltb.protocol.http.response;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class PositionsDTO {

    /**
     * 用户ID
     */
    private int userId;

    /**
     * 头寸列表
     */
    private List<Position> positions = new ArrayList<>();

    /**
     * 头寸对象
     */
    @Getter
    @Setter
    @Accessors(chain = true)
    public static class Position {

        /**
         * 标的代码
         */
        private String instrumentCode;

        /**
         * 净盈亏
         */
        private double netPnl;

        /**
         * 成本价
         */
        private double costPrice;

        /**
         * 多头头寸
         */
        private int longPos;

        /**
         * 空头头寸
         */
        private int shortPos;

        /**
         * 净头寸
         */
        private int netPos;

        /**
         * 持仓时间
         */
        private int duration;

    }

}
