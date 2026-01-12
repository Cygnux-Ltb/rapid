package io.cygnux.rapid.core.instrument;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 货币类型
 */
@Getter
@AllArgsConstructor
public enum Currency {

    /**
     * 美元
     */
    USD("USD"),

    /**
     * 日元
     */
    JPY("JPY"),

    /**
     * 在岸人民币
     */
    CNY("CNY"),

    /**
     * 离岸人民币
     */
    CNH("CNH"),

    /**
     * 港币
     */
    HKD("HKD"),

    /**
     * 欧元
     */
    EUR("EUR"),

    /**
     * 英镑
     */
    GBP("GBP"),

    /**
     * 瑞士法郎
     */
    CHF("CHF"),

    /**
     * 加元
     */
    CAD("CAD"),

    /**
     * 澳元
     */
    AUD("AUD"),

    /**
     * 印尼盾
     */
    IDR("IDR"),

    /**
     * 马来西亚林吉特
     */
    MYR("MYR"),

    /**
     * 新西兰元
     */
    NZD("NZD"),

    /**
     * 菲律宾比索
     */
    PHP("PHP"),

    /**
     * 俄罗斯卢布
     */
    SUR("SUR"),

    /**
     * 新加坡元
     */
    SGD("SGD"),

    /**
     * 韩国元
     */
    KRW("KRW"),

    /**
     * 泰国铢
     */
    THB("THB"),

    /**
     * 未知货币代码
     */
    UNKNOWN("UNKNOWN"),

    /**
     * 空货币代码
     */
    NONE("NONE"),

    ;

    private final String code;

}
