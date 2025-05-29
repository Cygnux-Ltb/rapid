package io.rapid.core.instrument;

import java.time.ZoneOffset;

import static io.mercury.common.datetime.TimeZone.CST;
import static io.mercury.common.datetime.TimeZone.JST;
import static io.mercury.common.datetime.TimeZone.UTC;

public enum Exchange {

    // BSE - The Bombay Stock Exchange Limited
    // PSE - Philippine Stock Exchange
    // CBOT - Chicago Board of Trade
    // NYMEX - New York Mercantile Exchange
    // COMEX - Chicago Mercantile Exchange
    // ICE - Intercontinental Exchange

    /**
     * Tokyo Commodity Exchange
     */
    TOCOM(11, "Tokyo Commodity Exchange", JST),

    /**
     * Tokyo Financial Exchange
     */
    TFX(12, "Tokyo Financial Exchange", JST),

    /**
     *
     */
    LME(31, "London Metal Exchange", UTC),

    /**
     * Shanghai Futures Exchange
     */
    SHFE(41, "Shanghai Futures Exchange", CST),

    /**
     * Dalian Commodity Exchange
     */
    DCE(42, "Dalian Commodity Exchange", CST),

    /**
     * Zhengzhou Commodity Exchange
     */
    ZCE(43, "Zhengzhou Commodity Exchange", CST),

    /**
     * China Financial Futures Exchange
     */
    CFFEX(44, "China Financial Futures Exchange", CST),

    /**
     * Shanghai International Energy Exchange
     */
    SHINE(45, "Shanghai International Energy Exchange", CST),

    /**
     * Shanghai Gold Exchange
     */
    SGE(46, "Shanghai Gold Exchange", CST),

    /**
     * Shanghai Stock Exchange
     */
    SSE(47, "Shanghai Stock Exchange", CST),

    /**
     * Shenzhen Stock Exchange
     */
    SZSE(48, "Shenzhen Stock Exchange", CST),

    ;

    // 唯一编码
    private final int exchangeId;

    // 交易所全名
    private final String fullName;

    // 交易所时区
    private final ZoneOffset zoneOffset;

    // 最大可用ID
    public static final int MAX_ID = 213;

    // ExchangeId 掩码
    public static final int MASK = 10000000;

    /**
     * @param exchangeId int
     * @param fullName   String
     * @param zoneOffset ZoneOffset
     */
    Exchange(int exchangeId, String fullName, ZoneOffset zoneOffset) {
        this.exchangeId = exchangeId * MASK;
        this.fullName = fullName;
        this.zoneOffset = zoneOffset;
    }

    public int getExchangeId() {
        return exchangeId;
    }

    public String getExchangeCode() {
        return name();
    }

    public String getDesc() {
        return fullName;
    }

    public ZoneOffset getZoneOffset() {
        return zoneOffset;
    }

    public static int parseExchangeId(Instrument instrument) {
        return parseExchangeId(instrument.getInstrumentId());
    }

    public static int parseExchangeId(int instrumentId) {
        return instrumentId / MASK;
    }

    public static void main(String[] args) {
        System.out.println(Exchange.parseExchangeId(InstrumentKeeper.getInstrumentByCode("rb2501")));
    }

}
