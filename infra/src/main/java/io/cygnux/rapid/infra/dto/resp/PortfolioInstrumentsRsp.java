package io.cygnux.rapid.infra.dto.resp;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

/**
 * 返回指定用户投资组合包含的投资标的
 */
@Getter
@Setter
@Accessors(chain = true)
public final class PortfolioInstrumentsRsp {

    /**
     * 用户ID
     */
    private int userid;

    /**
     * 投资组合(股票池/目标池) 代码
     */
    private String portfolioCode;

    /**
     * 投资组合(股票池/目标池) 名称
     */
    private String portfolioName;

    /**
     * 标的(股票/期货) 列表
     */
    private List<PortfolioInstrumentObj> instruments = new ArrayList<>();


    /**
     * 标的(股票/期货) 对象
     */
    @Getter
    @Setter
    @Accessors(chain = true)
    public static class PortfolioInstrumentObj {

        /**
         * 标的(股票/期货) 代码
         */
        private String instrumentCode;

        /**
         * 标的(股票/期货) 名称
         */
        private String instrumentName;

        /**
         * 标的(股票/期货) 最新价格
         */
        private double lastPrice;

        /**
         * 标的(股票/期货) 涨跌幅
         */
        private double change;

        /**
         * 标的(股票/期货) 净持仓
         */
        private double netPos;

        /**
         * 标的(股票/期货) 净盈亏
         */
        private double netPnl;

    }


}
