package io.rapid.adaptor.ctp.event;

import io.mercury.common.concurrent.disruptor.RingEventbus;
import io.mercury.common.functional.Processor;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.rapid.adaptor.ctp.event.source.EventSource;
import io.rapid.adaptor.ctp.event.source.SpecificInstrumentSource;
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
import org.slf4j.Logger;

import static io.mercury.common.concurrent.disruptor.base.CommonStrategy.Yielding;
import static io.rapid.adaptor.ctp.event.FtdcRspFieldWriter.writeDepthMarketData;
import static io.rapid.adaptor.ctp.event.FtdcRspFieldWriter.writeFrontDisconnected;
import static io.rapid.adaptor.ctp.event.FtdcRspFieldWriter.writeHeartBeatWarning;
import static io.rapid.adaptor.ctp.event.FtdcRspFieldWriter.writeInputOrder;
import static io.rapid.adaptor.ctp.event.FtdcRspFieldWriter.writeInputOrderAction;
import static io.rapid.adaptor.ctp.event.FtdcRspFieldWriter.writeInstrumentStatus;
import static io.rapid.adaptor.ctp.event.FtdcRspFieldWriter.writeInvestorPosition;
import static io.rapid.adaptor.ctp.event.FtdcRspFieldWriter.writeOrder;
import static io.rapid.adaptor.ctp.event.FtdcRspFieldWriter.writeOrderAction;
import static io.rapid.adaptor.ctp.event.FtdcRspFieldWriter.writeRspError;
import static io.rapid.adaptor.ctp.event.FtdcRspFieldWriter.writeRspUserLogin;
import static io.rapid.adaptor.ctp.event.FtdcRspFieldWriter.writeRspUserLogout;
import static io.rapid.adaptor.ctp.event.FtdcRspFieldWriter.writeSpecificInstrument;
import static io.rapid.adaptor.ctp.event.FtdcRspFieldWriter.writeTrade;
import static io.rapid.adaptor.ctp.event.FtdcRspFieldWriter.writeTradingAccount;

public final class FtdcRspPublisher {

    private static final Logger log = Log4j2LoggerFactory.getLogger(FtdcRspPublisher.class);

    private final RingEventbus<FtdcRspEvent> eventbus;

    /**
     * 由构造函数在内部转换为MPSC队列缓冲区
     *
     * @param processor Processor<FtdcRspEvent>
     */
    public FtdcRspPublisher(Processor<FtdcRspEvent> processor) {
        this.eventbus = RingEventbus
                .multiProducer(FtdcRspEvent.EVENT_FACTORY)
                .size(128)
                .name("ftdc-eventbus")
                .waitStrategy(Yielding.get())
                .process(processor);
    }

    /**
     * @param Source   EventSource
     * @param Reason   int
     * @param BrokerID String
     * @param UserID   String
     */
    public void publishFrontDisconnected(EventSource Source, int Reason,
                                         String BrokerID, String UserID) {
        eventbus.publish((event, sequence) ->
                writeFrontDisconnected(event, Source, Reason, BrokerID, UserID)
        );
    }

    /**
     * @param TimeLapse int
     * @param BrokerID  String
     * @param UserID    String
     */
    public void publishHeartBeatWarning(EventSource Source, int TimeLapse,
                                        String BrokerID, String UserID) {
        eventbus.publish((event, sequence) ->
                writeHeartBeatWarning(event, Source, TimeLapse, BrokerID, UserID)
        );
    }

    /**
     * @param Field     CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    public void publishRspError(EventSource Source, CThostFtdcRspInfoField Field,
                                int RequestID, boolean IsLast) {
        eventbus.publish((event, sequence) ->
                writeRspError(event, Source, Field, RequestID, IsLast)
        );
    }

    /**
     * @param Field        CThostFtdcRspUserLoginField
     * @param RspInfoField CThostFtdcRspInfoField
     * @param RequestID    int
     * @param IsLast       boolean
     */
    public void publishRspUserLogin(EventSource Source, CThostFtdcRspUserLoginField Field,
                                    CThostFtdcRspInfoField RspInfoField, int RequestID, boolean IsLast) {
        eventbus.publish((event, sequence) ->
                writeRspUserLogin(event, Source, Field, RspInfoField, RequestID, IsLast)
        );
    }


    public void publishUserLogout(EventSource Source, CThostFtdcUserLogoutField Field,
                                  CThostFtdcRspInfoField RspInfoField, int RequestID, boolean IsLast) {
        eventbus.publish((event, sequence) ->
                writeRspUserLogout(event, Source, Field, RspInfoField, RequestID, IsLast)
        );
    }

    /**
     * @param DepthMarketDataField CThostFtdcDepthMarketDataField
     */
    public void publishDepthMarketData(CThostFtdcDepthMarketDataField DepthMarketDataField) {
        eventbus.publish((event, sequence) -> writeDepthMarketData(event, DepthMarketDataField));
    }

    /**
     * @param OrderField CThostFtdcOrderField
     */
    public void publishOrder(CThostFtdcOrderField OrderField) {
        eventbus.publish((event, sequence) -> writeOrder(event, OrderField));
    }


    /**
     * @param TradeField CThostFtdcTradeField
     */
    public void publishTrade(CThostFtdcTradeField TradeField) {
        eventbus.publish((event, sequence) -> writeTrade(event, TradeField));
    }


    /**
     * @param Field CThostFtdcInputOrderField
     */
    public void publishInputOrder(CThostFtdcInputOrderField Field,
                                  CThostFtdcRspInfoField RspInfoField, boolean IsLast) {
        eventbus.publish((event, sequence) ->
                writeInputOrder(event, Field, RspInfoField, IsLast)
        );
    }

    /**
     * @param Field CThostFtdcInputOrderActionField
     */
    public void publishInputOrderAction(CThostFtdcInputOrderActionField Field,
                                        CThostFtdcRspInfoField RspInfoField, boolean IsLast) {
        eventbus.publish((event, sequence) ->
                writeInputOrderAction(event, Field, RspInfoField, IsLast)
        );
    }

    /**
     * @param Field CThostFtdcOrderActionField
     */
    public void publishOrderAction(CThostFtdcOrderActionField Field) {
        eventbus.publish((event, sequence) -> writeOrderAction(event, Field));
    }

    /**
     * @param Field        CThostFtdcTradingAccountField
     * @param RspInfoField CThostFtdcRspInfoField
     * @param RequestID    int
     * @param IsLast       boolean
     */
    public void publishTradingAccount(CThostFtdcTradingAccountField Field,
                                      CThostFtdcRspInfoField RspInfoField,
                                      int RequestID, boolean IsLast) {
        eventbus.publish((event, sequence) ->
                writeTradingAccount(event, Field, RspInfoField, RequestID, IsLast)
        );
    }

    /**
     * @param Field        CThostFtdcInvestorPositionField
     * @param RspInfoField CThostFtdcRspInfoField
     * @param RequestID    int
     * @param IsLast       boolean
     */
    public void publishInvestorPosition(CThostFtdcInvestorPositionField Field,
                                        CThostFtdcRspInfoField RspInfoField,
                                        int RequestID, boolean IsLast) {
        eventbus.publish((event, sequence) ->
                writeInvestorPosition(event, Field, RspInfoField, RequestID, IsLast)
        );
    }

    /**
     * @param Field CThostFtdcInstrumentStatusField
     */
    public void publishInstrumentStatus(CThostFtdcInstrumentStatusField Field) {
        eventbus.publish((event, sequence) -> writeInstrumentStatus(event, Field));
    }

    /**
     * @param Source       SpecificInstrumentSource
     * @param Field        CThostFtdcSpecificInstrumentField
     * @param RspInfoField CThostFtdcRspInfoField
     * @param RequestID    int
     * @param IsLast       boolean
     */
    public void publishSpecificInstrument(SpecificInstrumentSource Source,
                                          CThostFtdcSpecificInstrumentField Field,
                                          CThostFtdcRspInfoField RspInfoField,
                                          int RequestID, boolean IsLast) {
        eventbus.publish((event, sequence) ->
                writeSpecificInstrument(event, Source, Field, RspInfoField, RequestID, IsLast)
        );
    }

}
