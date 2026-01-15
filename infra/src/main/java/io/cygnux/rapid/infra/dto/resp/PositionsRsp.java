package io.cygnux.rapid.infra.dto.resp;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public final class PositionsRsp {

    /**
     * 用户ID
     */
    private int userid;

    /**
     * 头寸列表
     */
    private List<PositionObj> positions = new ArrayList<>();

    /**
     * 头寸对象
     */
    @Getter
    @Setter
    @Accessors(chain = true)
    public static class PositionObj {

        /**
         * 标的代码
         */
        private String instrumentCode;

        /**
         * 标的名称
         */
        private String instrumentName;

        /**
         * 盈亏
         */
        private double pnl;

        /**
         * 盈亏百分比
         */
        private double pnlPercent;

        /**
         * 今日净盈亏
         */
        private double todayPnl;

        /**
         * 今日盈亏百分比
         */
        private double todayPnlPercent;

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
         * 可用头寸
         */
        private int availablePos;

        /**
         * 持仓时间(天数)
         */
        private int duration;

    }

}
