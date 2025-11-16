package io.cygnux.rapid.core.shared.enums;


import io.cygnux.rapid.core.order.OrdConstant;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum OrdType {

    /**
     * 无效
     */
    INVALID(OrdConstant.INVALID),

    /**
     * Limited
     */
    LIMITED(OrdConstant.TYPE_LIMITED),
    /**
     * Market
     */
    MARKET(OrdConstant.TYPE_MARKET),

    /**
     * Limited_Stop, 在目前的市场价格达到指定的止损价格时, 被激活成为限价单的报单.
     */
    LIMITED_STOP(OrdConstant.TYPE_LIMITED_STOP),
    /**
     * Market_Stop, 在目前的市场价格达到指定的止损价格时, 被激活成为市价单的报单.
     */
    MARKET_STOP(OrdConstant.TYPE_MARKET_STOP),

    /**
     * Market_To_Limited, 按照市价报单的方式成交, 不能成交的部分保留在报单队列中, 变成限价单的报单.
     */
    MTL(OrdConstant.TYPE_MTL),
    /**
     * Best_Price, 不带有价格限定, 按照市场中存在的最好价格买入或者卖出的报单.
     */
    BP(OrdConstant.TYPE_BP),
    /**
     * Average_Price, 限定最终成交平均价格的报单.
     */
    AP(OrdConstant.TYPE_AP),

    /**
     * Fill_Or_Kill, 表示要求立即全部成交, 否则就全部取消的报单.
     */
    FOK(OrdConstant.TYPE_FOK),
    /**
     * Fill_And_Kill, 表示要求立即成交, 对于无法满足的部分予以取消的报单.
     */
    FAK(OrdConstant.TYPE_FAK),

    /**
     * Minimum_Volume, 要求满足成交量达到这个最小成交量, 否则就取消的报单.
     */
    MV(OrdConstant.TYPE_MV),

    ;

    @Getter
    private final char code;

    /**
     * @return OrdType.Limited
     */
    public static OrdType defaultType() {
        return OrdType.LIMITED;
    }

}
