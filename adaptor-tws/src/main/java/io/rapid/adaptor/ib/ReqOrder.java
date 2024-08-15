package io.rapid.adaptor.ib;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@EqualsAndHashCode
@ToString
public class ReqOrder {

    private String productClass; // 合约类型
    private String currency; // 合约货币
    private String expiry; // 到期日
    private double strikePrice; // 行权价
    private String optionType; // 期权类型
    private String lastTradeDateOrContractMonth; // 合约月,IB专用
    private String multiplier; // 乘数,IB专用

}
