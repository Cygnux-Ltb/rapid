package io.cygnux.rapid.infra.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 用户自选投资组合
 */
@Getter
@Setter
@Accessors(chain = true)
public final class PortfoliosRsp {

    /**
     * 用户ID
     */
    private int userid;


    /**
     * 投资组合(股票池/目标池) 列表
     */
    private List<PortfolioObj> portfolioList;

    /**
     * 自选池对象
     */
    @Getter
    @Setter
    @Accessors(chain = true)
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PortfolioObj {

        /**
         * 投资组合(股票池/目标池) 代码
         */
        private String portfolioCode;

        /**
         * 投资组合(股票池/目标池) 名称
         */
        private String portfolioName;

    }

}
