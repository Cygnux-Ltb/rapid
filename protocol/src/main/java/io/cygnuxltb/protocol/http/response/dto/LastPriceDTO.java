package io.cygnuxltb.protocol.http.response.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class LastPriceDTO {

    /**
     * 交易标的
     */
    private String instrumentCode;

    /**
     * 最新价格
     */
    private double lastPrice;

    public LastPriceDTO() {
    }

    public LastPriceDTO(String instrumentCode) {
        this.instrumentCode = instrumentCode;
    }

}
