package io.cygnux.rapid.infra.dto.req;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public final class PortfolioInstrumentReq {

    /**
     * [不可为空] 用户ID
     */
    private int userid;

    /**
     * [不可为空] 投资组合(股票池/目标池)代码
     */
    private String portfolioCode;

    /**
     * [不可为空]交易标的 (股票代码/期货代码)
     */
    private String instrumentCode;

}
