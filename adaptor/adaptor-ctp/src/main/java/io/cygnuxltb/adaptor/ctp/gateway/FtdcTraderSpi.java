package io.cygnuxltb.adaptor.ctp.gateway;

import ctp.thostapi.CThostFtdcInputOrderActionField;
import ctp.thostapi.CThostFtdcInputOrderField;
import ctp.thostapi.CThostFtdcInstrumentField;
import ctp.thostapi.CThostFtdcInvestorPositionField;
import ctp.thostapi.CThostFtdcOrderActionField;
import ctp.thostapi.CThostFtdcOrderField;
import ctp.thostapi.CThostFtdcRspAuthenticateField;
import ctp.thostapi.CThostFtdcRspInfoField;
import ctp.thostapi.CThostFtdcRspUserLoginField;
import ctp.thostapi.CThostFtdcSettlementInfoField;
import ctp.thostapi.CThostFtdcTradeField;
import ctp.thostapi.CThostFtdcTraderSpi;
import ctp.thostapi.CThostFtdcTradingAccountField;
import ctp.thostapi.CThostFtdcUserLogoutField;
import io.cygnuxltb.adaptor.ctp.gateway.FtdcGateway.FtdcTraderGateway.FtdcTraderCallback;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import org.slf4j.Logger;

import static io.cygnuxltb.adaptor.ctp.gateway.utils.FtdcRspInfoHandler.nonError;

public final class FtdcTraderSpiImpl extends CThostFtdcTraderSpi {

    private static final Logger log = Log4j2LoggerFactory.getLogger(FtdcTraderSpiImpl.class);

    private final FtdcTraderCallback callback;

    FtdcTraderSpiImpl(FtdcTraderCallback callback) {
        this.callback = callback;
    }

    @Override
    public void OnFrontConnected() {
        log.info("FtdcTraderSpi::OnFrontConnected");
        callback.onFrontConnected();
    }

    @Override
    public void OnFrontDisconnected(int Reason) {
        log.error("FtdcTraderSpi::OnFrontDisconnected, Reason==[{}]", Reason);
        callback.onFrontDisconnected();
    }

    @Override
    public void OnRspAuthenticate(CThostFtdcRspAuthenticateField RspAuthenticate,
                                  CThostFtdcRspInfoField RspInfo,
                                  int RequestID, boolean IsLast) {
        log.info("FtdcTraderSpi::OnRspAuthenticate, RequestID==[{}], IsLast==[{}]", RequestID, IsLast);
        if (nonError("FtdcTraderSpi::OnRspAuthenticate", RspInfo))
            if (RspAuthenticate != null)
                callback.onRspAuthenticate(RspAuthenticate);
            else
                log.warn("FtdcTraderSpi::OnRspAuthenticate return null");

    }

    @Override
    public void OnRspUserLogin(CThostFtdcRspUserLoginField RspUserLogin,
                               CThostFtdcRspInfoField RspInfo,
                               int RequestID, boolean IsLast) {
        log.info("FtdcTraderSpi::OnRspUserLogin, RequestID==[{}], IsLast==[{}]", RequestID, IsLast);
        if (nonError("FtdcTraderSpi::OnRspUserLogin", RspInfo)) {
            if (RspUserLogin != null)
                callback.onRspUserLogin(RspUserLogin);
            else
                log.error("FtdcTraderSpi::OnRspUserLogin return null");
        }
    }

    @Override
    public void OnRspUserLogout(CThostFtdcUserLogoutField UserLogout,
                                CThostFtdcRspInfoField RspInfo,
                                int RequestID, boolean IsLast) {
        log.info("FtdcTraderSpi::OnRspUserLogout, RequestID==[{}], IsLast==[{}]", RequestID, IsLast);
        if (nonError("FtdcTraderSpi::OnRspUserLogout", RspInfo))
            if (UserLogout != null)
                // TODO 处理用户登出
                log.info("Output :: OnRspUserLogout -> BrokerID==[{}], UserID==[{}]", UserLogout.getBrokerID(),
                        UserLogout.getUserID());
            else
                log.error("FtdcTraderSpi::OnRspUserLogout return null");


    }

    @Override
    public void OnRspQryOrder(CThostFtdcOrderField Order,
                              CThostFtdcRspInfoField RspInfo,
                              int RequestID, boolean IsLast) {
        log.info("FtdcTraderSpi::OnRspQryOrder, RequestID==[{}], IsLast==[{}]", RequestID, IsLast);
        if (nonError("FtdcTraderSpi::OnRspQryOrder", RspInfo))
            if (Order != null)
                callback.onRspQryOrder(Order, IsLast);
            else
                log.error("FtdcTraderSpi::OnRspQryOrder return null");


    }

    @Override
    public void OnRspQryTradingAccount(CThostFtdcTradingAccountField TradingAccount,
                                       CThostFtdcRspInfoField RspInfo,
                                       int RequestID, boolean IsLast) {
        log.info("FtdcTraderSpi::OnRspQryTradingAccount, RequestID==[{}], IsLast==[{}]", RequestID, IsLast);
        if (nonError("FtdcTraderSpi::OnRspQryTradingAccount", RspInfo))
            if (TradingAccount != null)
                callback.onQryTradingAccount(TradingAccount, IsLast);
            else
                log.error("FtdcTraderSpi::OnRspQryTradingAccount return null");
    }

    @Override
    public void OnRspQryInvestorPosition(CThostFtdcInvestorPositionField InvestorPosition,
                                         CThostFtdcRspInfoField RspInfo,
                                         int RequestID, boolean IsLast) {
        log.info("FtdcTraderSpi::OnRspQryInvestorPosition, RequestID==[{}], IsLast==[{}]", RequestID, IsLast);
        if (nonError("FtdcTraderSpi::OnRspQryInvestorPosition", RspInfo))
            if (InvestorPosition != null)
                callback.onRspQryInvestorPosition(InvestorPosition, IsLast);
            else
                log.error("FtdcTraderSpi::OnRspQryInvestorPosition return null");
    }

    @Override
    public void OnRspQrySettlementInfo(CThostFtdcSettlementInfoField SettlementInfo,
                                       CThostFtdcRspInfoField RspInfo,
                                       int RequestID, boolean IsLast) {
        log.info("FtdcTraderSpi::OnRspQrySettlementInfo, RequestID==[{}], IsLast==[{}]", RequestID, IsLast);
        if (nonError("FtdcTraderSpi::OnRspQrySettlementInfo", RspInfo))
            if (SettlementInfo != null)
                log.info(
                        """
                                OnRspQrySettlementInfo -> BrokerID==[{}], AccountID==[{}], InvestorID==[{}],
                                SettlementID==[{}], TradingDay==[{}], CurrencyID==[{}]
                                <<<<<<<<<<<<<<<< CONTENT TEXT >>>>>>>>>>>>>>>>
                                {}
                                """,
                        SettlementInfo.getBrokerID(), SettlementInfo.getAccountID(), SettlementInfo.getInvestorID(),
                        SettlementInfo.getSettlementID(), SettlementInfo.getTradingDay(),
                        SettlementInfo.getCurrencyID(), SettlementInfo.getContent());
            else
                log.error("FtdcTraderSpi::OnRspQrySettlementInfo return null");
    }

    @Override
    public void OnRspQryInstrument(CThostFtdcInstrumentField Instrument,
                                   CThostFtdcRspInfoField RspInfo,
                                   int RequestID, boolean IsLast) {
        log.info("FtdcTraderSpi::OnRspQryInstrument, RequestID==[{}], IsLast==[{}]", RequestID, IsLast);
        if (nonError("FtdcTraderSpi::OnRspQryInstrument", RspInfo))
            if (Instrument != null)
                log.info("Output :: OnRspQryInstrument, ExchangeID==[{}], InstrumentID==[{}]",
                        Instrument.getExchangeID(), Instrument.getInstrumentID());
            else
                log.error("FtdcTraderSpi::OnRspQryInstrument return null");
    }

    @Override
    public void OnRtnOrder(CThostFtdcOrderField Order) {
        if (Order != null)
            callback.onRtnOrder(Order);
        else
            log.error("FtdcTraderSpi::OnRtnOrder return null");
    }

    @Override
    public void OnRtnTrade(CThostFtdcTradeField Trade) {
        if (Trade != null)
            callback.onRtnTrade(Trade);
        else
            log.error("FtdcTraderSpi::OnRtnTrade return null");
    }

    /**
     * 报单错误回调:1
     */
    @Override
    public void OnRspOrderInsert(CThostFtdcInputOrderField InputOrder,
                                 CThostFtdcRspInfoField RspInfo,
                                 int RequestID, boolean IsLast) {
        log.info("FtdcTraderSpi::OnRspOrderInsert, RequestID==[{}], IsLast==[{}]", RequestID, IsLast);
        if (nonError("FtdcTraderSpi::OnRspOrderInsert", RspInfo))
            if (InputOrder != null)
                callback.onRspOrderInsert(InputOrder);
            else
                log.error("FtdcTraderSpi::OnRspOrderInsert return null");
    }

    /**
     * 报单错误回调:2
     */
    @Override
    public void OnErrRtnOrderInsert(CThostFtdcInputOrderField InputOrder,
                                    CThostFtdcRspInfoField RspInfo) {
        log.info("TraderSpi::OnErrRtnOrderInsert");
        if (nonError("TraderSpi::OnErrRtnOrderInsert", RspInfo))
            if (InputOrder != null)
                callback.onErrRtnOrderInsert(InputOrder);
            else
                log.error("TraderSpi::OnErrRtnOrderInsert return null");
    }

    /**
     * 撤单错误回调:1
     */
    @Override
    public void OnRspOrderAction(CThostFtdcInputOrderActionField InputOrderAction,
                                 CThostFtdcRspInfoField RspInfo,
                                 int RequestID, boolean IsLast) {
        log.info("TraderSpi::OnRspOrderAction, RequestID==[{}], IsLast==[{}]", RequestID, IsLast);
        if (nonError("TraderSpi::OnRspOrderAction", RspInfo))
            if (InputOrderAction != null)
                callback.onRspOrderAction(InputOrderAction);
            else
                log.error("TraderSpi::OnRspOrderAction return null");
    }

    /**
     * 撤单错误回调:2
     */
    @Override
    public void OnErrRtnOrderAction(CThostFtdcOrderActionField OrderAction,
                                    CThostFtdcRspInfoField RspInfo) {
        log.info("TraderSpi::OnErrRtnOrderAction");
        if (nonError("TraderSpi::OnErrRtnOrderAction", RspInfo))
            if (OrderAction != null)
                callback.onErrRtnOrderAction(OrderAction);
            else
                log.error("TraderSpi::OnErrRtnOrderAction return null");
    }

    /**
     * 错误回调
     */
    @Override
    public void OnRspError(CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        log.info("TraderSpi::OnRspError, RequestID==[{}], IsLast==[{}]", RequestID, IsLast);
        callback.onRspError(RspInfo, RequestID, IsLast);
    }

}