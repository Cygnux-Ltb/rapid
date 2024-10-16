package io.rapid.adaptor.ctp.event;

/**
 * FTDC响应类型
 */
public enum FtdcRspType {

    Unsupported,

    FrontDisconnected,

    HeartBeatWarning,

    RspUserLogin,

    UserLogout,

    RspError,

    MdClosed,

    TraderClosed,

    FtdcDepthMarketData,

    FtdcSpecificInstrument,

    FtdcInputOrder,

    FtdcInputOrderAction,

    FtdcInstrumentStatus,

    FtdcInvestorPosition,

    FtdcOrder,

    FtdcOrderAction,

    FtdcTrade,

    FtdcTradingAccount

}
