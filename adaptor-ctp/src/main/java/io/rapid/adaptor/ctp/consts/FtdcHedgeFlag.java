package io.rapid.adaptor.ctp.consts;

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
public interface FtdcHedgeFlag {

    /**
     * 投机 [char]
     */
    char SPECULATION = '1';
    /**
     * 投机 [String]
     */
    String SPECULATION_STR = String.valueOf(SPECULATION);

    /**
     * 套利 [char]
     */
    char ARBITRAGE = '2';
    /**
     * 套利 [String]
     */
    String ARBITRAGE_STR = String.valueOf(ARBITRAGE);

    /**
     * 套保 [char]
     */
    char HEDGE = '3';
    /**
     * 套保 [String]
     */
    String HEDGE_STR = String.valueOf(HEDGE);

    /**
     * 做市商 [char]
     */
    char MARKET_MAKER = '5';
    /**
     * 做市商 [String]
     */
    String MARKET_MAKER_STR = String.valueOf(MARKET_MAKER);

    /**
     * 第一腿投机第二腿套保 大商所专用 [char]
     */
    char SPEC_HEDGE = '6';
    /**
     * 第一腿投机第二腿套保 大商所专用 [String]
     */
    String SPEC_HEDGE_STR = String.valueOf(SPEC_HEDGE);

    /**
     * 第一腿套保第二腿投机 大商所专用 [char]
     */
    char HEDGE_SPEC = '7';
    /**
     * 第一腿套保第二腿投机 大商所专用 [String]
     */
    String HEDGE_SPEC_STR = String.valueOf(HEDGE_SPEC);

}

