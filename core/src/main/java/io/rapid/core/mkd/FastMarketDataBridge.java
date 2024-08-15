package io.rapid.core.mkd;

import io.rapid.core.instrument.Instrument;
import io.rapid.core.serializable.avro.inbound.FastMarketDataEvent;
import io.mercury.common.datetime.Timestamp;

public final class FastMarketDataBridge  {

    private final FastMarketDataEvent marketData;

    private final double[] bidPrices = new double[5];
    private final int[] bidVolumes = new int[5];
    private final double[] askPrices = new double[5];
    private final int[] askVolumes = new int[5];

    private FastMarketDataBridge() {
        this.marketData = FastMarketDataEvent
                // call -> new builder
                .newBuilder()
                // set -> timestamp, instrumentId, instrumentCode
                .setTimestamp(0L).setInstrumentId(0).setInstrumentCode("")
                // set -> last price, volume, turnover
                .setLastPrice(0L).setVolume(0).setTurnover(0L)
                // set -> level5 bid prices
                .setBidPrice1(0L).setBidPrice2(0L).setBidPrice3(0L)
                .setBidPrice4(0L).setBidPrice5(0L)
                // set -> level5 bid volumes
                .setBidVolume1(0).setBidVolume2(0).setBidVolume3(0)
                .setBidVolume4(0).setBidVolume5(0)
                // set -> level5 ask prices
                .setAskPrice1(0L).setAskPrice2(0L).setAskPrice3(0L)
                .setAskPrice4(0L).setAskPrice5(0L)
                // set -> level5 ask volumes
                .setAskVolume1(0).setAskVolume2(0).setAskVolume3(0)
                .setAskVolume4(0).setAskVolume5(0)
                // call -> build
                .build();
    }

    /**
     * @return FastMarketDataBridge
     */
    public static FastMarketDataBridge newInstance() {
        return new FastMarketDataBridge();
    }

    public FastMarketDataEvent getFastMarketData() {
        return marketData;
    }

    public FastMarketDataBridge setInstrument(Instrument instrument) {
        this.marketData.setInstrumentId(instrument.getInstrumentId());
        this.marketData.setInstrumentCode(instrument.getInstrumentCode());
        return this;
    }


    public int getInstrumentId() {
        return marketData.getInstrumentId();
    }


    public String getInstrumentCode() {
        return marketData.getInstrumentCode();
    }


    public long getEpochMillis() {
        return marketData.getTimestamp();
    }


    public Timestamp getTimestamp() {
        return Timestamp.withEpochMillis(marketData.getTimestamp());
    }


    public double getLastPrice() {
        return marketData.getLastPrice();
    }


    public int getVolume() {
        return marketData.getVolume();
    }


    public long getTurnover() {
        return marketData.getTurnover();
    }


    public int getDepth() {
        return 5;
    }


    public double[] getBidPrices() {
        return bidPrices;
    }


    public double getBidPrice1() {
        return marketData.getBidPrice1();
    }


    public double getBidPrice2() {
        return marketData.getBidPrice2();
    }


    public double getBidPrice3() {
        return marketData.getBidPrice3();
    }


    public double getBidPrice4() {
        return marketData.getBidPrice4();
    }


    public double getBidPrice5() {
        return marketData.getBidPrice5();
    }


    public int[] getBidVolumes() {
        return bidVolumes;
    }


    public int getBidVolume1() {
        return marketData.getBidVolume1();
    }


    public int getBidVolume2() {
        return marketData.getBidVolume2();
    }


    public int getBidVolume3() {
        return marketData.getBidVolume3();
    }


    public int getBidVolume4() {
        return marketData.getBidVolume4();
    }


    public int getBidVolume5() {
        return marketData.getBidVolume5();
    }


    public double[] getAskPrices() {
        return askPrices;
    }


    public double getAskPrice1() {
        return marketData.getAskPrice1();
    }


    public double getAskPrice2() {
        return marketData.getAskPrice2();
    }


    public double getAskPrice3() {
        return marketData.getAskPrice3();
    }


    public double getAskPrice4() {
        return marketData.getAskPrice4();
    }


    public double getAskPrice5() {
        return marketData.getAskPrice5();
    }


    public int[] getAskVolumes() {
        return askVolumes;
    }


    public int getAskVolume1() {
        return marketData.getAskVolume1();

    }


    public int getAskVolume2() {
        return marketData.getAskVolume2();
    }


    public int getAskVolume3() {
        return marketData.getAskVolume3();
    }


    public int getAskVolume4() {
        return marketData.getAskVolume4();
    }


    public int getAskVolume5() {
        return marketData.getAskVolume5();
    }


    public void updated() {
        this.bidPrices[0] = marketData.getBidPrice1();
        this.bidPrices[1] = marketData.getBidPrice2();
        this.bidPrices[2] = marketData.getBidPrice3();
        this.bidPrices[3] = marketData.getBidPrice4();
        this.bidPrices[4] = marketData.getBidPrice5();
        this.bidVolumes[0] = marketData.getBidVolume1();
        this.bidVolumes[1] = marketData.getBidVolume2();
        this.bidVolumes[2] = marketData.getBidVolume3();
        this.bidVolumes[3] = marketData.getBidVolume4();
        this.bidVolumes[4] = marketData.getBidVolume5();
        this.askPrices[0] = marketData.getAskPrice1();
        this.askPrices[1] = marketData.getAskPrice2();
        this.askPrices[2] = marketData.getAskPrice3();
        this.askPrices[3] = marketData.getAskPrice4();
        this.askPrices[4] = marketData.getAskPrice5();
        this.askVolumes[0] = marketData.getAskVolume1();
        this.askVolumes[1] = marketData.getAskVolume2();
        this.askVolumes[2] = marketData.getAskVolume3();
        this.askVolumes[3] = marketData.getAskVolume4();
        this.askVolumes[4] = marketData.getAskVolume5();
    }

}
