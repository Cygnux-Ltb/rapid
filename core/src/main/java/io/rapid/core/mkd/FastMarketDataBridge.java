package io.cygnuxltb.jcts.core.mkd;

import io.rapid.core.instrument.Instrument;
import io.cygnuxltb.jcts.core.ser.event.FastMarketDataEvent;
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
                .setBidPrices1(0L).setBidPrices2(0L).setBidPrices3(0L)
                .setBidPrices4(0L).setBidPrices5(0L)
                // set -> level5 bid volumes
                .setBidVolumes1(0).setBidVolumes2(0).setBidVolumes3(0)
                .setBidVolumes4(0).setBidVolumes5(0)
                // set -> level5 ask prices
                .setAskPrices1(0L).setAskPrices2(0L).setAskPrices3(0L)
                .setAskPrices4(0L).setAskPrices5(0L)
                // set -> level5 ask volumes
                .setAskVolumes1(0).setAskVolumes2(0).setAskVolumes3(0)
                .setAskVolumes4(0).setAskVolumes5(0)
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
        return marketData.getBidPrices1();
    }


    public double getBidPrice2() {
        return marketData.getBidPrices2();
    }


    public double getBidPrice3() {
        return marketData.getBidPrices3();
    }


    public double getBidPrice4() {
        return marketData.getBidPrices4();
    }


    public double getBidPrice5() {
        return marketData.getBidPrices5();
    }


    public int[] getBidVolumes() {
        return bidVolumes;
    }


    public int getBidVolume1() {
        return marketData.getBidVolumes1();
    }


    public int getBidVolume2() {
        return marketData.getBidVolumes2();
    }


    public int getBidVolume3() {
        return marketData.getBidVolumes3();
    }


    public int getBidVolume4() {
        return marketData.getBidVolumes4();
    }


    public int getBidVolume5() {
        return marketData.getBidVolumes5();
    }


    public double[] getAskPrices() {
        return askPrices;
    }


    public double getAskPrice1() {
        return marketData.getAskPrices1();
    }


    public double getAskPrice2() {
        return marketData.getAskPrices2();
    }


    public double getAskPrice3() {
        return marketData.getAskPrices3();
    }


    public double getAskPrice4() {
        return marketData.getAskPrices4();
    }


    public double getAskPrice5() {
        return marketData.getAskPrices5();
    }


    public int[] getAskVolumes() {
        return askVolumes;
    }


    public int getAskVolume1() {
        return marketData.getAskVolumes1();

    }


    public int getAskVolume2() {
        return marketData.getAskVolumes2();
    }


    public int getAskVolume3() {
        return marketData.getAskVolumes3();
    }


    public int getAskVolume4() {
        return marketData.getAskVolumes4();
    }


    public int getAskVolume5() {
        return marketData.getAskVolumes5();
    }


    public void updated() {
        this.bidPrices[0] = marketData.getBidPrices1();
        this.bidPrices[1] = marketData.getBidPrices2();
        this.bidPrices[2] = marketData.getBidPrices3();
        this.bidPrices[3] = marketData.getBidPrices4();
        this.bidPrices[4] = marketData.getBidPrices5();
        this.bidVolumes[0] = marketData.getBidVolumes1();
        this.bidVolumes[1] = marketData.getBidVolumes2();
        this.bidVolumes[2] = marketData.getBidVolumes3();
        this.bidVolumes[3] = marketData.getBidVolumes4();
        this.bidVolumes[4] = marketData.getBidVolumes5();
        this.askPrices[0] = marketData.getAskPrices1();
        this.askPrices[1] = marketData.getAskPrices2();
        this.askPrices[2] = marketData.getAskPrices3();
        this.askPrices[3] = marketData.getAskPrices4();
        this.askPrices[4] = marketData.getAskPrices5();
        this.askVolumes[0] = marketData.getAskVolumes1();
        this.askVolumes[1] = marketData.getAskVolumes2();
        this.askVolumes[2] = marketData.getAskVolumes3();
        this.askVolumes[3] = marketData.getAskVolumes4();
        this.askVolumes[4] = marketData.getAskVolumes5();
    }

}
