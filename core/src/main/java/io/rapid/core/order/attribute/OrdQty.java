package io.rapid.core.order.attribute;

import io.mercury.common.serialization.specific.JsonSerializable;
import lombok.Getter;

import javax.annotation.Nonnull;

/**
 * @author yellow013
 */
public final class OrdQty implements JsonSerializable {

    /**
     * 委托数量
     */
    @Getter
    private final int offerQty;

    /**
     * 剩余数量
     */
    @Getter
    private int leavesQty;

    /**
     * 上一次成交数量
     */
    @Getter
    private int lastFilledQty;

    /**
     * 已成交数量
     */
    @Getter
    private int filledQty;

    private OrdQty(int offerQty) {
        this.offerQty = offerQty;
        this.leavesQty = offerQty;
    }

    public static OrdQty withOffer(int offerQty) {
        return new OrdQty(offerQty);
    }

    /**
     * 设置已成交数量, 适用于在订单回报中返回此订单总成交数量的柜台
     *
     * @param filledQty int
     * @return OrdQty
     */
    public OrdQty setFilledQty(int filledQty) {
        this.lastFilledQty = this.filledQty;
        this.filledQty = filledQty;
        this.leavesQty = offerQty - filledQty;
        return this;
    }

    /**
     * 添加已成交数量, 适用于在订单回报中返回当次成交数量的柜台
     *
     * @param filledQty int
     * @return OrdQty
     */
    public OrdQty addFilledQty(int filledQty) {
        return setFilledQty(this.filledQty + filledQty);
    }

    private static final String OfferQtyTitle = "{\"offerQty\" : ";
    private static final String LeavesQtyTitle = ", \"leavesQty\" : ";
    private static final String LastFilledQtyTitle = ", \"lastFilledQty\" : ";
    private static final String FilledQtyTitle = ", \"filledQty\" : ";
    private static final String End = "}";

    @Override
    public String toString() {
        return OfferQtyTitle + offerQty
                + LeavesQtyTitle + leavesQty
                + LastFilledQtyTitle + lastFilledQty
                + FilledQtyTitle + filledQty
                + End;
    }

    @Nonnull
    @Override
    public String toJson() {
        return toString();
    }

}
