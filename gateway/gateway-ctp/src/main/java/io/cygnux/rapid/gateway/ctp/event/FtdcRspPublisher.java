package io.cygnux.rapid.gateway.ctp.event;

import io.cygnux.rapid.gateway.ctp.event.source.EventSource;
import io.cygnux.rapid.gateway.ctp.event.source.SpecificInstrumentSource;
import io.mercury.common.concurrent.disruptor.RingEventbus;
import io.mercury.common.functional.Processor;
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

import static io.cygnux.rapid.gateway.ctp.event.FtdcRspFieldWriter.writeDepthMarketData;
import static io.cygnux.rapid.gateway.ctp.event.FtdcRspFieldWriter.writeFrontDisconnected;
import static io.cygnux.rapid.gateway.ctp.event.FtdcRspFieldWriter.writeHeartBeatWarning;
import static io.cygnux.rapid.gateway.ctp.event.FtdcRspFieldWriter.writeInputOrder;
import static io.cygnux.rapid.gateway.ctp.event.FtdcRspFieldWriter.writeInputOrderAction;
import static io.cygnux.rapid.gateway.ctp.event.FtdcRspFieldWriter.writeInstrumentStatus;
import static io.cygnux.rapid.gateway.ctp.event.FtdcRspFieldWriter.writeInvestorPosition;
import static io.cygnux.rapid.gateway.ctp.event.FtdcRspFieldWriter.writeOrder;
import static io.cygnux.rapid.gateway.ctp.event.FtdcRspFieldWriter.writeOrderAction;
import static io.cygnux.rapid.gateway.ctp.event.FtdcRspFieldWriter.writeRspError;
import static io.cygnux.rapid.gateway.ctp.event.FtdcRspFieldWriter.writeRspUserLogin;
import static io.cygnux.rapid.gateway.ctp.event.FtdcRspFieldWriter.writeRspUserLogout;
import static io.cygnux.rapid.gateway.ctp.event.FtdcRspFieldWriter.writeSpecificInstrument;
import static io.cygnux.rapid.gateway.ctp.event.FtdcRspFieldWriter.writeTrade;
import static io.cygnux.rapid.gateway.ctp.event.FtdcRspFieldWriter.writeTradingAccount;
import static io.mercury.common.concurrent.disruptor.base.CommonStrategy.Yielding;

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
     * @param source EventSource
     * @param reason      int
     */
    public void publishFrontDisconnected(EventSource source, int reason) {
        eventbus.publish((event, sequence) ->
                writeFrontDisconnected(event, source, reason));
    }

    /**
     * @param timeLapse int
     * @param brokerID  String
     * @param userID    String
     */
    public void publishHeartBeatWarning(EventSource source, int timeLapse,
                                        String brokerID, String userID) {
        eventbus.publish((event, sequence) ->
                writeHeartBeatWarning(event, source, timeLapse, brokerID, userID));
    }

    /**
     * @param field CThostFtdcRspInfoField
     * @param requestID    int
     * @param isLast       boolean
     */
    public void publishRspError(EventSource source, CThostFtdcRspInfoField field,
                                int requestID, boolean isLast) {
        eventbus.publish((event, sequence) ->
                writeRspError(event, source, field, requestID, isLast));
    }

    /**
     * @param field CThostFtdcRspUserLoginField
     * @param rspInfoField      CThostFtdcRspInfoField
     * @param requestID         int
     * @param isLast            boolean
     */
    public void publishRspUserLogin(EventSource source, CThostFtdcRspUserLoginField field,
                                    CThostFtdcRspInfoField rspInfoField, int requestID, boolean isLast) {
        eventbus.publish((event, sequence) ->
                writeRspUserLogin(event, source, field, rspInfoField, requestID, isLast));
    }


    public void publishUserLogout(EventSource eventSource, CThostFtdcUserLogoutField field,
                                  CThostFtdcRspInfoField rspInfoField, int requestID, boolean isLast) {
        eventbus.publish((event, sequence) ->
                writeRspUserLogout(event, eventSource, field, rspInfoField, requestID, isLast));
    }

    /**
     * @param field CThostFtdcDepthMarketDataField
     */
    public void publishDepthMarketData(CThostFtdcDepthMarketDataField field) {
        eventbus.publish((event, sequence) ->
                writeDepthMarketData(event, field));
    }

    /**
     * @param field CThostFtdcOrderField
     */
    public void publishOrder(CThostFtdcOrderField field) {
        eventbus.publish((event, sequence) ->
                writeOrder(event, field));
    }


    /**
     * @param field CThostFtdcTradeField
     */
    public void publishTrade(CThostFtdcTradeField field) {
        eventbus.publish((event, sequence) ->
                writeTrade(event, field));
    }


    /**
     * @param field CThostFtdcInputOrderField
     */
    public void publishInputOrder(CThostFtdcInputOrderField field,
                                  CThostFtdcRspInfoField rspInfoField, boolean isLast) {
        eventbus.publish((event, sequence) ->
                writeInputOrder(event, field, rspInfoField, isLast));
    }

    /**
     * @param field CThostFtdcInputOrderActionField
     */
    public void publishInputOrderAction(CThostFtdcInputOrderActionField field,
                                        CThostFtdcRspInfoField rspInfoField, boolean isLast) {
        eventbus.publish((event, sequence) ->
                writeInputOrderAction(event, field, rspInfoField, isLast));
    }

    /**
     * @param field CThostFtdcOrderActionField
     */
    public void publishOrderAction(CThostFtdcOrderActionField field) {
        eventbus.publish((event, sequence) ->
                writeOrderAction(event, field));
    }

    /**
     * @param field CThostFtdcTradingAccountField
     * @param rspInfoField        CThostFtdcRspInfoField
     * @param requestID           int
     * @param isLast              boolean
     */
    public void publishTradingAccount(CThostFtdcTradingAccountField field,
                                      CThostFtdcRspInfoField rspInfoField,
                                      int requestID, boolean isLast) {
        eventbus.publish((event, sequence) ->
                writeTradingAccount(event, field, rspInfoField, requestID, isLast));
    }

    /**
     * @param field CThostFtdcInvestorPositionField
     * @param rspInfoField          CThostFtdcRspInfoField
     * @param requestID             int
     * @param isLast                boolean
     */
    public void publishInvestorPosition(CThostFtdcInvestorPositionField field,
                                        CThostFtdcRspInfoField rspInfoField,
                                        int requestID, boolean isLast) {
        eventbus.publish((event, sequence) ->
                writeInvestorPosition(event, field, rspInfoField, requestID, isLast));
    }

    /**
     * @param field CThostFtdcInstrumentStatusField
     */
    public void publishInstrumentStatus(CThostFtdcInstrumentStatusField field) {
        eventbus.publish((event, sequence) ->
                writeInstrumentStatus(event, field));
    }

    /**
     * @param source SpecificInstrumentSource
     * @param field  CThostFtdcSpecificInstrumentField
     * @param rspInfoField             CThostFtdcRspInfoField
     * @param requestID                int
     * @param isLast                   boolean
     */
    public void publishSpecificInstrument(SpecificInstrumentSource source,
                                          CThostFtdcSpecificInstrumentField field,
                                          CThostFtdcRspInfoField rspInfoField,
                                          int requestID, boolean isLast) {
        eventbus.publish((event, sequence) ->
                writeSpecificInstrument(event, source,
                        field, rspInfoField, requestID, isLast));
    }

}
