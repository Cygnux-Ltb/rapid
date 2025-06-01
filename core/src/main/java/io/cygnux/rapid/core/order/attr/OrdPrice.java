package io.cygnux.rapid.core.order.attr;

import io.mercury.common.serialization.specific.JsonSerializable;
import io.cygnux.rapid.core.order.impl.ChildOrder;
import io.cygnux.rapid.core.trade.TradeRecord;
import lombok.Getter;

import javax.annotation.Nonnull;

/**
 * @author yellow013
 */
public final class OrdPrice implements JsonSerializable {

    /**
     * 委托价格
     */
    @Getter
    private double offerPrice;

    /**
     * 成交均价
     */
    @Getter
    private double avgTradePrice;

    private OrdPrice() {
    }

    private OrdPrice(double offerPrice) {
        this.offerPrice = offerPrice;
    }

    public static OrdPrice withEmpty() {
        return new OrdPrice();
    }

    public static OrdPrice withOffer(double offerPrice) {
        return new OrdPrice(offerPrice);
    }

    public OrdPrice setOfferPrice(double offerPrice) {
        this.offerPrice = offerPrice;
        return this;
    }

    public OrdPrice calcAvgTradePrice(@Nonnull ChildOrder order) {
        var records = order.getRecords();
        if (records.size() == 1)
            this.avgTradePrice = records.getFirst().tradePrice();
        if (records.size() > 1) {
            var multiplier = order.getInstrument().getMultiplier();
            // 计算总成交金额
            long totalTurnover = records.sumOfLong(trade ->
                    multiplier.toLong(trade.tradePrice()) * trade.tradeQty());
            // 计算总成交量
            long totalQty = records.sumOfInt(TradeRecord::tradeQty);
            if (totalQty > 0L)
                this.avgTradePrice = multiplier.toDouble(totalTurnover / totalQty);
        }
        return this;
    }

    private static final String OfferPriceTitle = "{\"offerPrice\" : ";
    private static final String AvgTradePriceTitle = ", \"avgTradePrice\" : ";
    private static final String End = "}";

    @Override
    public String toString() {
        return OfferPriceTitle + offerPrice
                + AvgTradePriceTitle + avgTradePrice
                + End;
    }

    @Override
    @Nonnull
    public String toJson() {
        return toString();
    }

}
