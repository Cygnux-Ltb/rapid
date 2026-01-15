package io.cygnux.rapid.infra.persistence.vo;

import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import static org.apache.commons.lang3.StringUtils.compare;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class PortfolioVo implements Comparable<PortfolioVo> {

    /**
     * 投资组合代码
     */
    private String portfolioCode;

    /**
     * 投资组合名称
     */
    private String portfolioName;

    @Override
    public int compareTo(@Nonnull PortfolioVo vo) {
        int compare = compare(portfolioCode, vo.portfolioCode);
        return compare != 0
                ? compare
                : compare(portfolioName, vo.portfolioName);
    }

}
