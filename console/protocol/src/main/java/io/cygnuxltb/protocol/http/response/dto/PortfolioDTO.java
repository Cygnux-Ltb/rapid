package io.cygnuxltb.protocol.http.response.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 用户自选标的
 */
@Getter
@Setter
@Accessors(chain = true)
public class PortfolioDTO {

    /**
     * 用户ID
     */
    private int userId;

    /**
     * 投资组合(股票池/目标池)名称
     */
    private String portfolioName;

    /**
     * 交易标的(股票/期货)列表
     */
    private List<String> instrumentCodes;

}
