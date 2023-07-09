package io.cygnuxltb.protocol.http.outbound;

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
     * 投资组合名称
     */
    private String portfolioName;

    /**
     * 交易标的列表
     */
    private List<String> instrumentCodes;

}
