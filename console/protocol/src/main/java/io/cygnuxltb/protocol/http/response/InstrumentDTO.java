package io.cygnuxltb.protocol.http.response;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 交易标的表
 * Instrument Entity
 *
 * @author yellow013
 */
@Getter
@Setter
@Accessors(chain = true)
public final class InstrumentDTO {

    /**
     * 交易标的代码
     */
    private String instrumentCode;

    /**
     * 交易标的类型
     */
    private String instrumentType;

    /**
     * 交易所代码
     */
    private String exchangeCode;

    /**
     * 交易费用
     */
    private double fee;

    /**
     * 可交易标识
     */
    private boolean tradable;

}
