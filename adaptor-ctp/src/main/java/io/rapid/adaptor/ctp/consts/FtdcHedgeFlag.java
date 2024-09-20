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
    char Speculation = '1';
    /**
     * 投机 [String]
     */
    String SpeculationStr = "1";

    /**
     * 套利 [char]
     */
    char Arbitrage = '2';
    /**
     * 套利 [String]
     */
    String ArbitrageStr = "2";

    /**
     * 套保 [char]
     */
    char Hedge = '3';
    /**
     * 套保 [String]
     */
    String HedgeStr = "3";

    /**
     * 做市商 [char]
     */
    char MarketMaker = '5';
    /**
     * 做市商 [String]
     */
    String MarketMakerStr = "5";

    /**
     * 第一腿投机第二腿套保 大商所专用 [char]
     */
    char SpecHedge = '6';
    /**
     * 第一腿投机第二腿套保 大商所专用 [String]
     */
    String SpecHedgeStr = "6";

    /**
     * 第一腿套保第二腿投机 大商所专用 [char]
     */
    char HedgeSpec = '7';
    /**
     * 第一腿套保第二腿投机 大商所专用 [String]
     */
    String HedgeSpecStr = "7";

}

