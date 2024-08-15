package io.rapid.adaptor.ctp.gateway.event;

import ctp.thostapi.CThostFtdcDepthMarketDataField;
import ctp.thostapi.CThostFtdcInputOrderActionField;
import ctp.thostapi.CThostFtdcInputOrderField;
import ctp.thostapi.CThostFtdcInstrumentStatusField;
import ctp.thostapi.CThostFtdcInvestorPositionField;
import ctp.thostapi.CThostFtdcOrderActionField;
import ctp.thostapi.CThostFtdcOrderField;
import ctp.thostapi.CThostFtdcRspInfoField;
import ctp.thostapi.CThostFtdcRspUserLoginField;
import ctp.thostapi.CThostFtdcSpecificInstrumentField;
import ctp.thostapi.CThostFtdcTradeField;
import ctp.thostapi.CThostFtdcTradingAccountField;
import ctp.thostapi.CThostFtdcUserLogoutField;
import io.mercury.common.concurrent.disruptor.RingEventbus.MultiProducerRingEventbus;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.rapid.adaptor.ctp.serializable.avro.md.SpecificInstrumentSource;
import io.rapid.adaptor.ctp.serializable.avro.pack.FtdcRspType;
import io.rapid.adaptor.ctp.serializable.avro.shared.EventSource;
import org.slf4j.Logger;

import static io.mercury.common.datetime.EpochTime.getEpochMillis;
import static io.rapid.adaptor.ctp.gateway.event.FtdcRspFieldWriter.writeSpecificInstrument;
import static io.rapid.adaptor.ctp.serializable.avro.pack.FtdcRspType.FtdcDepthMarketData;

public final class FtdcRspPublisher {

    private static final Logger log = Log4j2LoggerFactory.getLogger(FtdcRspPublisher.class);

    private final MultiProducerRingEventbus<FtdcRspEvent> eventbus;

    /**
     * @param eventbus MpRingEventbus<FtdcEvent>
     */
    public FtdcRspPublisher(MultiProducerRingEventbus<FtdcRspEvent> eventbus) {
        this.eventbus = eventbus;
    }

    public void publishFrontDisconnected(EventSource Source, int Reason,
                                         String BrokerID, String UserID) {
        eventbus.publish((event, sequence) ->
                FtdcRspFieldWriter.writeFrontDisconnected(event, Source, Reason, BrokerID, UserID)
                        .setEpochMillis(getEpochMillis())
                        .setType(FtdcRspType.FrontDisconnected)
        );
    }

    /**
     * 心跳超时警告
     *
     * @param TimeLapse int
     * @param BrokerID  String
     * @param UserID    String
     */
    public void publishHeartBeatWarning(EventSource Source, int TimeLapse,
                                        String BrokerID, String UserID) {
        eventbus.publish((event, sequence) ->
                FtdcRspFieldWriter.writeHeartBeatWarning(event, Source, TimeLapse, BrokerID, UserID)
                        .setEpochMillis(getEpochMillis())
                        .setType(FtdcRspType.HeartBeatWarning)
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
                FtdcRspFieldWriter.writeRspError(event, Source, Field, RequestID, IsLast)
                        .setEpochMillis(getEpochMillis())
                        .setType(FtdcRspType.RspError)
        );
    }

    /**
     * @param Field     CThostFtdcRspUserLoginField
     * @param RspInfo   CThostFtdcRspInfoField
     * @param RequestID int
     * @param IsLast    boolean
     */
    public void publishRspUserLogin(EventSource Source,
                                    CThostFtdcRspUserLoginField Field,
                                    CThostFtdcRspInfoField RspInfo,
                                    int RequestID, boolean IsLast) {
        eventbus.publish((event, sequence) ->
                FtdcRspFieldWriter.writeRspUserLogin(event, Source, Field, RspInfo, RequestID, IsLast)
                        .setEpochMillis(getEpochMillis())
                        .setType(FtdcRspType.RspUserLogin)
        );
    }


    public void publishTraderUserLogout(EventSource Source,
                                        CThostFtdcUserLogoutField Field,
                                        CThostFtdcRspInfoField RspInfo,
                                        int RequestID, boolean IsLast) {
        eventbus.publish((event, sequence) ->
                FtdcRspFieldWriter.writeRspUserLogout(event, Source, Field, RspInfo, RequestID, IsLast)
                        .setEpochMillis(getEpochMillis())
                        .setType(FtdcRspType.RspUserLogout)
        );
    }

    /**
     * @param Field CThostFtdcDepthMarketDataField
     */
    public void publishDepthMarketData(CThostFtdcDepthMarketDataField Field) {
        eventbus.publish((event, sequence) ->
                FtdcRspFieldWriter.writeDepthMarketData(event, Field)
                        .setEpochMillis(getEpochMillis())
                        .setType(FtdcDepthMarketData)
        );
    }

    /**
     * @param Field CThostFtdcOrderField
     */
    public void publishOrder(CThostFtdcOrderField Field) {
        eventbus.publish((event, sequence) ->
                FtdcRspFieldWriter.writeOrder(event, Field)
                        .setEpochMillis(getEpochMillis())
                        .setType(FtdcRspType.FtdcOrder)
        );
    }


    /**
     * @param Field CThostFtdcTradeField
     */
    public void publishTrade(CThostFtdcTradeField Field) {
        eventbus.publish((event, sequence) ->
                FtdcRspFieldWriter.writeTrade(event, Field)
                        .setEpochMillis(getEpochMillis())
                        .setType(FtdcRspType.FtdcTrade)
        );
    }


    /**
     * @param Field CThostFtdcInputOrderField
     */
    public void publishInputOrder(CThostFtdcInputOrderField Field, CThostFtdcRspInfoField RspInfo, boolean IsLast) {
        eventbus.publish((event, sequence) ->
                FtdcRspFieldWriter.writeInputOrder(event, Field, RspInfo, IsLast)
                        .setEpochMillis(getEpochMillis())
                        .setType(FtdcRspType.FtdcInputOrder)
        );
    }

    /**
     * @param Field CThostFtdcInputOrderActionField
     */
    public void publishInputOrderAction(CThostFtdcInputOrderActionField Field,
                                        CThostFtdcRspInfoField RspInfo, boolean IsLast) {
        eventbus.publish((event, sequence) ->
                FtdcRspFieldWriter.writeInputOrderAction(event, Field, RspInfo, IsLast)
                        .setEpochMillis(getEpochMillis())
                        .setType(FtdcRspType.FtdcInputOrderAction)
        );
    }

    /**
     * @param Field CThostFtdcOrderActionField
     */
    public void publishOrderAction(CThostFtdcOrderActionField Field) {
        eventbus.publish((event, sequence) ->
                FtdcRspFieldWriter.writeOrderAction(event, Field)
                        .setEpochMillis(getEpochMillis())
                        .setType(FtdcRspType.FtdcOrderAction)
        );
    }


    public void publishTradingAccount(CThostFtdcTradingAccountField Field,
                                      CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        eventbus.publish((event, sequence) ->
                FtdcRspFieldWriter.writeTradingAccount(event, Field, RspInfo, RequestID, IsLast)
                        .setEpochMillis(getEpochMillis())
                        .setType(FtdcRspType.FtdcTradingAccount)
        );
    }


    public void publishInvestorPosition(CThostFtdcInvestorPositionField Field,
                                        CThostFtdcRspInfoField RspInfo, int RequestID, boolean IsLast) {
        eventbus.publish((event, sequence) ->
                FtdcRspFieldWriter.writeInvestorPosition(event, Field, RspInfo, RequestID, IsLast)
                        .setEpochMillis(getEpochMillis())
                        .setType(FtdcRspType.FtdcInvestorPosition)
        );
    }

    public void publishInstrumentStatus(CThostFtdcInstrumentStatusField Field) {
        eventbus.publish((event, sequence) ->
                FtdcRspFieldWriter.writeInstrumentStatus(event, Field)
                        .setEpochMillis(getEpochMillis())
                        .setType(FtdcRspType.FtdcInstrumentStatus)
        );
    }


    public void publishSpecificInstrument(CThostFtdcSpecificInstrumentField Field, CThostFtdcRspInfoField RspInfo, SpecificInstrumentSource Source, int RequestID, boolean IsLast) {
        eventbus.publish((event, sequence) ->
                writeSpecificInstrument(event, Source, Field, RspInfo, RequestID, IsLast)
                        .setType(FtdcRspType.FtdcSpecificInstrument));
    }


}
