package io.cygnuxltb.adaptor.ctp.gateway.msg;

import com.lmax.disruptor.EventTranslatorOneArg;
import ctp.thostapi.CThostFtdcDepthMarketDataField;
import ctp.thostapi.CThostFtdcInputOrderActionField;
import ctp.thostapi.CThostFtdcInputOrderField;
import ctp.thostapi.CThostFtdcInvestorPositionField;
import ctp.thostapi.CThostFtdcOrderActionField;
import ctp.thostapi.CThostFtdcOrderField;
import ctp.thostapi.CThostFtdcRspInfoField;
import ctp.thostapi.CThostFtdcTradeField;
import ctp.thostapi.CThostFtdcTradingAccountField;
import io.cygnuxltb.adaptor.ctp.gateway.msg.FtdcEvent.FtdcRspType;
import io.mercury.common.concurrent.ring.RingEventbus;

public final class FtdcEventPublisher {

    private final RingEventbus<FtdcEvent> eventbus;

    public FtdcEventPublisher(RingEventbus<FtdcEvent> eventbus) {
        this.eventbus = eventbus;
    }

    private final EventTranslatorOneArg<FtdcEvent, CThostFtdcDepthMarketDataField> depthMarketDataTranslator =
            (event, sequence, arg0) -> {
                event.getDepthMarketData().load(arg0);
                event.setType(FtdcRspType.DepthMarketData);
            };

    public void publish(CThostFtdcDepthMarketDataField field) {
        eventbus.publish(depthMarketDataTranslator, field);
    }


    private final EventTranslatorOneArg<FtdcEvent, CThostFtdcOrderField> ftdcOrderTranslator =
            (event, sequence, arg0) -> {
                event.getOrder().load(arg0);
                event.setType(FtdcRspType.Order);
            };

    public void publish(CThostFtdcOrderField field) {
        eventbus.publish(ftdcOrderTranslator, field);
    }

    private final EventTranslatorOneArg<FtdcEvent, CThostFtdcTradeField> ftdcTradeTranslator =
            (event, sequence, arg0) -> {
                event.getTrade().load(arg0);
                event.setType(FtdcRspType.Order);
            };


    public void publish(CThostFtdcTradeField field) {
        eventbus.publish(ftdcTradeTranslator, field);
    }

    public void publish(boolean available) {

    }


    public void publish(boolean available, int frontId, int sessionId) {

    }


    public void publish(CThostFtdcInputOrderField field) {

    }


    public void publish(CThostFtdcRspInfoField field, int requestId, boolean isLast) {

    }


    public void publish(CThostFtdcOrderField field, boolean isLast) {

    }


    public void publish(CThostFtdcOrderActionField field) {
    }


    public void publish(CThostFtdcTradingAccountField field) {
    }


    public void publish(CThostFtdcInvestorPositionField field, boolean isLast) {

    }


    private final EventTranslatorOneArg<FtdcEvent, CThostFtdcInputOrderActionField> ftdcInputOrderActionTranslator =
            (event, sequence, arg0) -> {
                event.getInputOrderAction().load(arg0);
                event.setType(FtdcRspType.InputOrderAction);
            };

    public void publish(CThostFtdcInputOrderActionField field) {
        eventbus.publish(ftdcInputOrderActionTranslator, field);
    }





}
