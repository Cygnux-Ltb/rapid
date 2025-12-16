package io.cygnux.rapid.ctp.gateway.consts;

/**
 * ///TFtdcHedgeFlagType是一个投机套保标志类型<br>
 * <br>
 * ///投机<br>
 * #define THOST_FTDC_HF_Speculation '1'<br>
 * <br>
 * ///套利<br>
 * #define THOST_FTDC_HF_Arbitrage '2'<br>
 * <br>
 * ///套保<br>
 * #define THOST_FTDC_HF_Hedge '3'<br>
 * <br>
 * ///做市商<br>
 * #define THOST_FTDC_HF_MarketMaker '5'<br>
 * <br>
 * ///第一腿投机第二腿套保 大商所专用<br>
 * #define THOST_FTDC_HF_SpecHedge '6'<br>
 * <br>
 * ///第一腿套保第二腿投机 大商所专用<br>
 * #define THOST_FTDC_HF_HedgeSpec '7'<br>
 */
public final class FtdcHedgeFlag {

    /**
     * 投机 [char]
     */
    public static final char SPECULATION = '1';
    /**
     * 投机 [String]
     */
    public static final String SPECULATION_STR = String.valueOf(SPECULATION);

    /**
     * 套利 [char]
     */
    public static final char ARBITRAGE = '2';
    /**
     * 套利 [String]
     */
    public static final String ARBITRAGE_STR = String.valueOf(ARBITRAGE);

    /**
     * 套保 [char]
     */
    public static final char HEDGE = '3';
    /**
     * 套保 [String]
     */
    public static final String HEDGE_STR = String.valueOf(HEDGE);

    /**
     * 做市商 [char]
     */
    public static final char MARKET_MAKER = '5';
    /**
     * 做市商 [String]
     */
    public static final String MARKET_MAKER_STR = String.valueOf(MARKET_MAKER);

    /**
     * 第一腿投机第二腿套保 大商所专用 [char]
     */
    public static final char SPEC_HEDGE = '6';
    /**
     * 第一腿投机第二腿套保 大商所专用 [String]
     */
    public static final String SPEC_HEDGE_STR = String.valueOf(SPEC_HEDGE);

    /**
     * 第一腿套保第二腿投机 大商所专用 [char]
     */
    public static final char HEDGE_SPEC = '7';
    /**
     * 第一腿套保第二腿投机 大商所专用 [String]
     */
    public static final String HEDGE_SPEC_STR = String.valueOf(HEDGE_SPEC);

    private FtdcHedgeFlag() {
    }

}
