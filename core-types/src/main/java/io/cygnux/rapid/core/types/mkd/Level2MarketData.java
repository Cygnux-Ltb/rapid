package io.cygnux.rapid.core.types.mkd;

import io.cygnux.rapid.core.types.instrument.Instrument;
import io.mercury.common.datetime.Timestamp;
import io.mercury.common.serialization.specific.JsonSerializable;
import io.mercury.serialization.json.JsonWriter;
import lombok.Getter;

import javax.annotation.Nonnull;
import java.io.Serial;

import static io.mercury.common.datetime.Timestamp.withEpochMillis;

/**
 * @author yellow013
 * @creation 2019年5月24日
 * @description 价格转换使用对应Instrument的价格乘数
 */
public class Level2MarketData implements JsonSerializable {

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

    protected Level2MarketData(@Nonnull Instrument instrument, long epochMillis, int depth) {
        this(instrument, epochMillis, null, depth);
    }

    protected Level2MarketData(@Nonnull Instrument instrument, long epochMillis, Timestamp timestamp, int depth) {
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

    public Level2MarketData setLastPrice(double lastPrice) {
        this.lastPrice = lastPrice;
        return this;
    }

    public Level2MarketData setVolume(int volume) {
        this.volume = volume;
        return this;
    }

    public Level2MarketData setTurnover(long turnover) {
        this.turnover = turnover;
        return this;
    }

    public Level2MarketData setBidPrice1(double price) {
        this.bidPrices[0] = price;
        return this;
    }

    public Level2MarketData setBidPrice2(double price) {
        this.bidPrices[1] = price;
        return this;
    }

    public Level2MarketData setBidPrice3(double price) {
        this.bidPrices[2] = price;
        return this;
    }

    public Level2MarketData setBidPrice4(double price) {
        this.bidPrices[3] = price;
        return this;
    }

    public Level2MarketData setBidPrice5(double price) {
        this.bidPrices[4] = price;
        return this;
    }

    public Level2MarketData setBidVolume1(int volume) {
        this.bidVolumes[0] = volume;
        return this;
    }

    public Level2MarketData setBidVolume2(int volume) {
        this.bidVolumes[1] = volume;
        return this;
    }

    public Level2MarketData setBidVolume3(int volume) {
        this.bidVolumes[2] = volume;
        return this;
    }

    public Level2MarketData setBidVolume4(int volume) {
        this.bidVolumes[3] = volume;
        return this;
    }

    public Level2MarketData setBidVolume5(int volume) {
        this.bidVolumes[4] = volume;
        return this;
    }

    public Level2MarketData setAskPrice1(double price) {
        this.askPrices[0] = price;
        return this;
    }

    public Level2MarketData setAskPrice2(double price) {
        this.askPrices[1] = price;
        return this;
    }

    public Level2MarketData setAskPrice3(double price) {
        this.askPrices[2] = price;
        return this;
    }

    public Level2MarketData setAskPrice4(double price) {
        this.askPrices[3] = price;
        return this;
    }

    public Level2MarketData setAskPrice5(double price) {
        this.askPrices[4] = price;
        return this;
    }

    public Level2MarketData setAskVolume1(int volume) {
        this.askVolumes[0] = volume;
        return this;
    }

    public Level2MarketData setAskVolume2(int volume) {
        this.askVolumes[1] = volume;
        return this;
    }

    public Level2MarketData setAskVolume3(int volume) {
        this.askVolumes[2] = volume;
        return this;
    }

    public Level2MarketData setAskVolume4(int volume) {
        this.askVolumes[3] = volume;
        return this;
    }

    public Level2MarketData setAskVolume5(int volume) {
        this.askVolumes[4] = volume;
        return this;
    }

    @Override
    public String toString() {
        return JsonWriter.toJsonHasNulls(this);
    }

    @Nonnull
    @Override
    public String toJson() {
        return this.toString();
    }

    public static class QuoteLevelOverflowException extends RuntimeException {
        @Serial
        private static final long serialVersionUID = 2602076635184902103L;

        public QuoteLevelOverflowException(String msg) {
            super(msg);
        }

    }


}
