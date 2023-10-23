package io.cygnuxltb.jcts.core.mkd;

import io.rapid.core.instrument.Instrument;
import io.mercury.common.datetime.Timestamp;
import io.mercury.common.serialization.specific.JsonSerializable;
import io.mercury.serialization.json.JsonWrapper;
import lombok.Getter;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static io.mercury.common.datetime.Timestamp.withEpochMillis;

/**
 * @author yellow013
 * @creation 2019年5月24日
 * @description 价格转换使用对应Instrument的价格乘数
 */
public class BasicMarketData implements JsonSerializable {

    // Required
    @Getter
    protected final Instrument instrument;

    // Required
    @Getter
    protected final long epochMillis;

    /**
     * base info
     **/
    protected Timestamp timestamp;

    @Getter
    protected double lastPrice;

    @Getter
    protected int volume;

    @Getter
    protected long turnover;

    @Getter
    protected final double[] bidPrices;

    @Getter
    protected final int[] bidVolumes;

    @Getter
    protected final double[] askPrices;

    @Getter
    protected final int[] askVolumes;

    @Getter
    protected final int depth;

    protected BasicMarketData(@Nonnull Instrument instrument, long epochMillis, int depth) {
        this(instrument, epochMillis, null, depth);
    }

    protected BasicMarketData(@Nonnull Instrument instrument, long epochMillis,
                              @Nullable Timestamp timestamp, int depth) {
        this.instrument = instrument;
        this.epochMillis = epochMillis;
        this.timestamp = timestamp;
        this.depth = depth;
        this.bidPrices = new double[depth];
        this.bidVolumes = new int[depth];
        this.askPrices = new double[depth];
        this.askVolumes = new int[depth];
    }

    public int getInstrumentId() {
        return instrument.getInstrumentId();
    }

    public String getInstrumentCode() {
        return instrument.getInstrumentCode();
    }

    public Timestamp getTimestamp() {
        if (timestamp == null)
            this.timestamp = withEpochMillis(epochMillis);
        return timestamp;
    }


    public double getBidPrice1() {
        return bidPrices[0];
    }


    public double getBidPrice2() {
        return bidPrices[1];
    }


    public double getBidPrice3() {
        return bidPrices[2];
    }


    public double getBidPrice4() {
        return bidPrices[3];
    }


    public double getBidPrice5() {
        return bidPrices[4];
    }


    public int getBidVolume1() {
        return bidVolumes[0];
    }


    public int getBidVolume2() {
        return bidVolumes[1];
    }


    public int getBidVolume3() {
        return bidVolumes[2];
    }


    public int getBidVolume4() {
        return bidVolumes[3];
    }


    public int getBidVolume5() {
        return bidVolumes[4];
    }


    public double getAskPrice1() {
        return askPrices[0];
    }


    public double getAskPrice2() {
        return askPrices[1];
    }


    public double getAskPrice3() {
        return askPrices[2];
    }


    public double getAskPrice4() {
        return askPrices[3];
    }


    public double getAskPrice5() {
        return askPrices[4];
    }


    public int getAskVolume1() {
        return askVolumes[0];
    }


    public int getAskVolume2() {
        return askVolumes[1];
    }


    public int getAskVolume3() {
        return askVolumes[2];
    }


    public int getAskVolume4() {
        return askVolumes[3];
    }


    public int getAskVolume5() {
        return askVolumes[4];
    }

    /*********************** Setter *************************/

    public BasicMarketData setLastPrice(double lastPrice) {
        this.lastPrice = lastPrice;
        return this;
    }

    public BasicMarketData setVolume(int volume) {
        this.volume = volume;
        return this;
    }

    public BasicMarketData setTurnover(long turnover) {
        this.turnover = turnover;
        return this;
    }

    public BasicMarketData setBidPrice1(double price) {
        this.bidPrices[0] = price;
        return this;
    }

    public BasicMarketData setBidPrice2(double price) {
        this.bidPrices[1] = price;
        return this;
    }

    public BasicMarketData setBidPrice3(double price) {
        this.bidPrices[2] = price;
        return this;
    }

    public BasicMarketData setBidPrice4(double price) {
        this.bidPrices[3] = price;
        return this;
    }

    public BasicMarketData setBidPrice5(double price) {
        this.bidPrices[4] = price;
        return this;
    }

    public BasicMarketData setBidVolume1(int volume) {
        this.bidVolumes[0] = volume;
        return this;
    }

    public BasicMarketData setBidVolume2(int volume) {
        this.bidVolumes[1] = volume;
        return this;
    }

    public BasicMarketData setBidVolume3(int volume) {
        this.bidVolumes[2] = volume;
        return this;
    }

    public BasicMarketData setBidVolume4(int volume) {
        this.bidVolumes[3] = volume;
        return this;
    }

    public BasicMarketData setBidVolume5(int volume) {
        this.bidVolumes[4] = volume;
        return this;
    }

    public BasicMarketData setAskPrice1(double price) {
        this.askPrices[0] = price;
        return this;
    }

    public BasicMarketData setAskPrice2(double price) {
        this.askPrices[1] = price;
        return this;
    }

    public BasicMarketData setAskPrice3(double price) {
        this.askPrices[2] = price;
        return this;
    }

    public BasicMarketData setAskPrice4(double price) {
        this.askPrices[3] = price;
        return this;
    }

    public BasicMarketData setAskPrice5(double price) {
        this.askPrices[4] = price;
        return this;
    }

    public BasicMarketData setAskVolume1(int volume) {
        this.askVolumes[0] = volume;
        return this;
    }

    public BasicMarketData setAskVolume2(int volume) {
        this.askVolumes[1] = volume;
        return this;
    }

    public BasicMarketData setAskVolume3(int volume) {
        this.askVolumes[2] = volume;
        return this;
    }

    public BasicMarketData setAskVolume4(int volume) {
        this.askVolumes[3] = volume;
        return this;
    }

    public BasicMarketData setAskVolume5(int volume) {
        this.askVolumes[4] = volume;
        return this;
    }

    @Override
    public String toString() {
        return JsonWrapper.toJsonHasNulls(this);
    }

    @Nonnull
    @Override
    public String toJson() {
        return this.toString();
    }

}
