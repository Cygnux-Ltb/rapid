package io.rapid.core.order.attribute;

import io.mercury.common.serialization.specific.JsonSerializable;
import io.rapid.core.instrument.InstrumentKeeper;
import io.rapid.core.order.TradeRecord;
import io.rapid.core.order.impl.Order;

import javax.annotation.Nonnull;

/**
 * @author yellow013
 */
public final class OrdPrice implements JsonSerializable {

    /**
     * 委托价格
     */
    private double offerPrice;

    /**
     * 成交均价
     */
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

    public double getOfferPrice() {
        return offerPrice;
    }

    public double getAvgTradePrice() {
        return avgTradePrice;
    }

    public OrdPrice setOfferPrice(double offerPrice) {
        this.offerPrice = offerPrice;
        return this;
    }

    public OrdPrice calcAvgTradePrice(@Nonnull Order order) {
        var records = order.getRecords();
        if (records.size() == 1) {
            this.avgTradePrice = records.getFirst().tradePrice();
        }
        if (records.size() > 1) {
            var multiplier = InstrumentKeeper.getInstrument(order.getInstrumentCode()).getMultiplier();
            // 计算总成交金额
            long totalTurnover = records
                    .sumOfLong(trade -> multiplier.toLong(trade.tradePrice()) * trade.tradeQty());
            // 计算总成交量
            long totalQty = records.sumOfInt(TradeRecord::tradeQty);
            if (totalQty > 0L)
                this.avgTradePrice = multiplier.toDouble(totalTurnover / totalQty);
        }
        return this;
    }

    private static final String OfferPriceField = "{\"offerPrice\" : ";
    private static final String AvgTradePriceField = ", \"avgTradePrice\" : ";
    private static final String End = "}";

    @Override
    public String toString() {
        return OfferPriceField + offerPrice
                + AvgTradePriceField + avgTradePrice
                + End;
    }

    @Override
    @Nonnull
    public String toJson() {
        return toString();
    }

}
