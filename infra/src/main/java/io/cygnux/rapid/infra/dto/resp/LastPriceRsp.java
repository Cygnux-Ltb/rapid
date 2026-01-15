package io.cygnux.rapid.infra.dto.resp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public final class LastPriceRsp {

    private List<LastPriceObj> lastPrices = new ArrayList<>();

    public LastPriceRsp(List<LastPriceObj> lastPrices) {
        this.lastPrices.addAll(lastPrices);
    }

    public LastPriceRsp(LastPriceObj... lastPrices) {
        this(asList(lastPrices));
    }

    /**
     * 目标对象
     */
    @Getter
    @Setter
    @Accessors(chain = true)
    public static class LastPriceObj {

        /**
         * 交易标的
         */
        private String instrumentCode;

        /**
         * 最新价格
         */
        private double lastPrice;

        /**
         * 涨跌幅
         */
        private double change;

    }

}
