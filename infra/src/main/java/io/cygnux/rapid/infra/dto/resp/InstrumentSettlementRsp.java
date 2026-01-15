package io.cygnux.rapid.infra.dto.resp;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 交易标的结算表
 * InstrumentSettlement Entity
 *
 * @author yellow013
 */
@Getter
@Setter
@Accessors(chain = true)
public final class InstrumentSettlementRsp {

    /**
     * 交易日
     */
    private int tradingDay;

    /**
     * 交易标的代码
     */
    private String instrumentCode;

    /**
     * 收盘价
     */
    private double closePrice;

    /**
     * 开盘价
     */
    private double openPrice;

    /**
     * 最高价
     */
    private double highestPrice;

    /**
     * 最低价
     */
    private double lowestPrice;

    /**
     * 结算价
     */
    private double settlementPrice;


}
