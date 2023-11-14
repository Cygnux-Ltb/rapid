package io.cygnuxltb.adaptor.ctp.gateway.event;

import com.lmax.disruptor.EventTranslatorOneArg;
import ctp.thostapi.CThostFtdcDepthMarketDataField;
import ctp.thostapi.CThostFtdcInputOrderActionField;
import ctp.thostapi.CThostFtdcInputOrderField;
import ctp.thostapi.CThostFtdcInstrumentStatusField;
import ctp.thostapi.CThostFtdcInvestorPositionField;
import ctp.thostapi.CThostFtdcOrderActionField;
import ctp.thostapi.CThostFtdcOrderField;
import ctp.thostapi.CThostFtdcRspInfoField;
import ctp.thostapi.CThostFtdcRspUserLoginField;
import ctp.thostapi.CThostFtdcTradeField;
import ctp.thostapi.CThostFtdcTradingAccountField;
import io.cygnuxltb.adaptor.ctp.gateway.event.FtdcEvent.FtdcRspType;
import io.mercury.common.concurrent.ring.RingEventbus;

public final class FtdcEventPublisher {

    private final RingEventbus<FtdcEvent> eventbus;

    public FtdcEventPublisher(RingEventbus<FtdcEvent> eventbus) {
        this.eventbus = eventbus;
    }

    public void publish(CThostFtdcRspInfoField field, int requestId, boolean isLast) {
        eventbus.publish(
                (event, sequence, arg0, arg1, arg2) ->
                        event.setType(FtdcRspType.RspInfo)
                                .setLast(arg2)
                                .getFtdcRspInfo().load(arg0, arg1),
                field, requestId, isLast);
    }


    public void publish(CThostFtdcDepthMarketDataField field) {
        eventbus.publish(
                (event, sequence, arg0) ->
                        event.setType(FtdcRspType.DepthMarketData)
                                .getFtdcDepthMarketData().load(arg0),
                field);
    }


    public void publish(CThostFtdcOrderField field) {
        eventbus.publish((event, sequence, arg0) ->
                        event.setType(FtdcRspType.Order).getFtdcOrder().load(arg0),
                field);
    }


    public void publish(CThostFtdcTradeField field) {
        eventbus.publish((event, sequence, arg0) ->
                        event.setType(FtdcRspType.Trade).getFtdcTrade().load(arg0),
                field);
    }

    private final EventTranslatorOneArg<FtdcEvent, CThostFtdcInputOrderActionField> InputOrderActionTranslator =
            (event, sequence, arg0) -> {
                event.getFtdcInputOrderAction().load(arg0);
                event.setType(FtdcRspType.InputOrderAction);
            };

    public void publishMdAvailable(CThostFtdcRspUserLoginField userLogin,
                                   CThostFtdcRspInfoField rspInfo, int requestID, boolean isLast) {

        eventbus.publish();
    }

    /**
     * 发布行情可用事件
     *
     * @param reason int
     */
    public void publishMdUnavailable(int reason) {

    }

    public void publishTraderAvailable(boolean available, int frontId, int sessionId) {

    }


    public void publishTraderUnavailable() {

    }

    public void publishTraderRspUserLogin() {
        CThostFtdcRspUserLoginField field;
    }


    public void publish(CThostFtdcInputOrderField field) {

    }


    public void publish(CThostFtdcOrderField field, boolean isLast) {

    }


    public void publish(CThostFtdcInputOrderActionField field) {
        eventbus.publish((event, sequence, arg0) ->
                        event.setType(FtdcRspType.InputOrderAction)
                                .getFtdcInputOrderAction().load(arg0),
                field);
    }

    public void publish(CThostFtdcOrderActionField field) {

    }


    public void publish(CThostFtdcTradingAccountField field) {

    }


    public void publish(CThostFtdcInvestorPositionField field, boolean isLast) {

    }

    public void publishRspError(CThostFtdcRspInfoField rspInfo, int requestId, boolean isLast) {
        eventbus.publish((event, sequence, arg0, arg1, arg2) ->
                        event.setType(FtdcRspType.RspInfo)
                                .setLast(arg2)
                                .getFtdcRspInfo().load(arg0, arg1),
                rspInfo, requestId, isLast);
    }

    public void publish(CThostFtdcInstrumentStatusField field) {

    }
}
