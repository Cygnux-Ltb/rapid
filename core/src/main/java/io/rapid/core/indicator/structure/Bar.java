package io.rapid.core.indicator.structure;

import io.mercury.common.serialization.specific.JsonSerializable;
import lombok.Getter;

import javax.annotation.Nonnull;

/**
 * @author yellow013
 */
@Getter
public final class Bar implements JsonSerializable {

    /**
     * 开盘价
     */
    private final double open;

    /**
     * 最高价
     */
    private double highest;

    /**
     * 最低价
     */
    private double lowest;

    /**
     * 最新价
     */
    private double last;

    public Bar(double open) {
        this.open = open;
        this.highest = open;
        this.lowest = open;
        this.last = open;
    }

    /**
     * @param price double
     * @return Bar
     */
    public Bar addPrice(double price) {
        if (price > highest)
            highest = price;
        if (price < lowest)
            lowest = price;
        last = price;
        return this;
    }

    @Override
    public String toString() {
        return // 开盘价
                "{\"open\":" + open
                        // 最高价
                        + ",\"highest\":" + highest
                        // 最低价
                        + ",\"lowest\":" + lowest
                        // 最新价
                        + ",\"last\":" + last + "}";
    }

    @Nonnull
    @Override
    public String toJson() {
        return toString();
    }

    public double hl2() {
        return 0.0d;
    }

    public double hlc3() {
        return 0.0d;
    }

    public double hlcc4() {
        return 0.0d;
    }

    public double ohlc4() {
        return 0.0d;
    }

    public static void main(String[] args) {

        Bar bar = new Bar(10D).addPrice(10000D).addPrice(100D).addPrice(1000D);
        System.out.println(bar);

    }

}
