package io.cygnux.gateway.ctp.event;

import io.mercury.common.concurrent.disruptor.RingEventbus;
import io.mercury.common.functional.Processor;
import io.cygnux.gateway.ctp.event.source.EventSource;
import io.cygnux.gateway.ctp.event.source.SpecificInstrumentSource;
import org.rationalityfrontline.jctp.CThostFtdcDepthMarketDataField;
import org.rationalityfrontline.jctp.CThostFtdcInputOrderActionField;
import org.rationalityfrontline.jctp.CThostFtdcInputOrderField;
import org.rationalityfrontline.jctp.CThostFtdcInstrumentStatusField;
import org.rationalityfrontline.jctp.CThostFtdcInvestorPositionField;
import org.rationalityfrontline.jctp.CThostFtdcOrderActionField;
import org.rationalityfrontline.jctp.CThostFtdcOrderField;
import org.rationalityfrontline.jctp.CThostFtdcRspInfoField;
import org.rationalityfrontline.jctp.CThostFtdcRspUserLoginField;
import org.rationalityfrontline.jctp.CThostFtdcSpecificInstrumentField;
import org.rationalityfrontline.jctp.CThostFtdcTradeField;
import org.rationalityfrontline.jctp.CThostFtdcTradingAccountField;
import org.rationalityfrontline.jctp.CThostFtdcUserLogoutField;

import static io.mercury.common.concurrent.disruptor.base.CommonStrategy.Yielding;
import static io.cygnux.gateway.ctp.event.FtdcRspFieldWriter.writeDepthMarketData;
import static io.cygnux.gateway.ctp.event.FtdcRspFieldWriter.writeFrontDisconnected;
import static io.cygnux.gateway.ctp.event.FtdcRspFieldWriter.writeHeartBeatWarning;
import static io.cygnux.gateway.ctp.event.FtdcRspFieldWriter.writeInputOrder;
import static io.cygnux.gateway.ctp.event.FtdcRspFieldWriter.writeInputOrderAction;
import static io.cygnux.gateway.ctp.event.FtdcRspFieldWriter.writeInstrumentStatus;
import static io.cygnux.gateway.ctp.event.FtdcRspFieldWriter.writeInvestorPosition;
import static io.cygnux.gateway.ctp.event.FtdcRspFieldWriter.writeOrder;
import static io.cygnux.gateway.ctp.event.FtdcRspFieldWriter.writeOrderAction;
import static io.cygnux.gateway.ctp.event.FtdcRspFieldWriter.writeRspError;
import static io.cygnux.gateway.ctp.event.FtdcRspFieldWriter.writeRspUserLogin;
import static io.cygnux.gateway.ctp.event.FtdcRspFieldWriter.writeRspUserLogout;
import static io.cygnux.gateway.ctp.event.FtdcRspFieldWriter.writeSpecificInstrument;
import static io.cygnux.gateway.ctp.event.FtdcRspFieldWriter.writeTrade;
import static io.cygnux.gateway.ctp.event.FtdcRspFieldWriter.writeTradingAccount;

public final class FtdcRspPublisher {

    private final RingEventbus<FtdcRspEvent> eventbus;

    /**
     * 由构造函数在内部转换为MPSC队列缓冲区
     *
     * @param processor Processor<FtdcRspEvent>
     */
    public FtdcRspPublisher(Processor<FtdcRspEvent> processor) {
        this.eventbus = RingEventbus
                .multiProducer(FtdcRspEvent::new)
                .size(128)
                .name("ftdc-eventbus")
                .waitStrategy(Yielding.get())
                .process(processor);
    }

    /**
     * @param eventSource EventSource
     * @param reason      int
     */
    public void publishFrontDisconnected(EventSource eventSource, int reason) {
        eventbus.publish((event, sequence) ->
                writeFrontDisconnected(event, eventSource, reason));
    }

    /**
     * @param timeLapse int
     * @param brokerID  String
     * @param userID    String
     */
    public void publishHeartBeatWarning(EventSource eventSource, int timeLapse,
                                        String brokerID, String userID) {
        eventbus.publish((event, sequence) ->
                writeHeartBeatWarning(event, eventSource, timeLapse, brokerID, userID));
    }

    /**
     * @param rspInfoField CThostFtdcRspInfoField
     * @param requestID    int
     * @param isLast       boolean
     */
    public void publishRspError(EventSource eventSource, CThostFtdcRspInfoField rspInfoField,
                                int requestID, boolean isLast) {
        eventbus.publish((event, sequence) ->
                writeRspError(event, eventSource, rspInfoField, requestID, isLast));
    }

    /**
     * @param rspUserLoginField CThostFtdcRspUserLoginField
     * @param rspInfoField      CThostFtdcRspInfoField
     * @param requestID         int
     * @param isLast            boolean
     */
    public void publishRspUserLogin(EventSource eventSource, CThostFtdcRspUserLoginField rspUserLoginField,
                                    CThostFtdcRspInfoField rspInfoField, int requestID, boolean isLast) {
        eventbus.publish((event, sequence) ->
                writeRspUserLogin(event, eventSource, rspUserLoginField, rspInfoField, requestID, isLast));
    }


    public void publishUserLogout(EventSource eventSource, CThostFtdcUserLogoutField userLogoutField,
                                  CThostFtdcRspInfoField rspInfoField, int requestID, boolean isLast) {
        eventbus.publish((event, sequence) ->
                writeRspUserLogout(event, eventSource, userLogoutField, rspInfoField, requestID, isLast));
    }

    /**
     * @param depthMarketDataField CThostFtdcDepthMarketDataField
     */
    public void publishDepthMarketData(CThostFtdcDepthMarketDataField depthMarketDataField) {
        eventbus.publish((event, sequence) ->
                writeDepthMarketData(event, depthMarketDataField));
    }

    /**
     * @param orderField CThostFtdcOrderField
     */
    public void publishOrder(CThostFtdcOrderField orderField) {
        eventbus.publish((event, sequence) ->
                writeOrder(event, orderField));
    }


    /**
     * @param tradeField CThostFtdcTradeField
     */
    public void publishTrade(CThostFtdcTradeField tradeField) {
        eventbus.publish((event, sequence) ->
                writeTrade(event, tradeField));
    }


    /**
     * @param inputOrderField CThostFtdcInputOrderField
     */
    public void publishInputOrder(CThostFtdcInputOrderField inputOrderField,
                                  CThostFtdcRspInfoField rspInfoField, boolean isLast) {
        eventbus.publish((event, sequence) ->
                writeInputOrder(event, inputOrderField, rspInfoField, isLast));
    }

    /**
     * @param inputOrderActionField CThostFtdcInputOrderActionField
     */
    public void publishInputOrderAction(CThostFtdcInputOrderActionField inputOrderActionField,
                                        CThostFtdcRspInfoField rspInfoField, boolean isLast) {
        eventbus.publish((event, sequence) ->
                writeInputOrderAction(event, inputOrderActionField, rspInfoField, isLast));
    }

    /**
     * @param orderActionField CThostFtdcOrderActionField
     */
    public void publishOrderAction(CThostFtdcOrderActionField orderActionField) {
        eventbus.publish((event, sequence) ->
                writeOrderAction(event, orderActionField));
    }

    /**
     * @param tradingAccountField CThostFtdcTradingAccountField
     * @param rspInfoField        CThostFtdcRspInfoField
     * @param requestID           int
     * @param isLast              boolean
     */
    public void publishTradingAccount(CThostFtdcTradingAccountField tradingAccountField,
                                      CThostFtdcRspInfoField rspInfoField,
                                      int requestID, boolean isLast) {
        eventbus.publish((event, sequence) ->
                writeTradingAccount(event, tradingAccountField, rspInfoField, requestID, isLast));
    }

    /**
     * @param investorPositionField CThostFtdcInvestorPositionField
     * @param rspInfoField          CThostFtdcRspInfoField
     * @param requestID             int
     * @param isLast                boolean
     */
    public void publishInvestorPosition(CThostFtdcInvestorPositionField investorPositionField,
                                        CThostFtdcRspInfoField rspInfoField,
                                        int requestID, boolean isLast) {
        eventbus.publish((event, sequence) ->
                writeInvestorPosition(event, investorPositionField, rspInfoField, requestID, isLast));
    }

    /**
     * @param instrumentStatusField CThostFtdcInstrumentStatusField
     */
    public void publishInstrumentStatus(CThostFtdcInstrumentStatusField instrumentStatusField) {
        eventbus.publish((event, sequence) ->
                writeInstrumentStatus(event, instrumentStatusField));
    }

    /**
     * @param specificInstrumentSource SpecificInstrumentSource
     * @param specificInstrumentField  CThostFtdcSpecificInstrumentField
     * @param rspInfoField             CThostFtdcRspInfoField
     * @param requestID                int
     * @param isLast                   boolean
     */
    public void publishSpecificInstrument(SpecificInstrumentSource specificInstrumentSource,
                                          CThostFtdcSpecificInstrumentField specificInstrumentField,
                                          CThostFtdcRspInfoField rspInfoField,
                                          int requestID, boolean isLast) {
        eventbus.publish((event, sequence) ->
                writeSpecificInstrument(event, specificInstrumentSource,
                        specificInstrumentField, rspInfoField, requestID, isLast));
    }

}
