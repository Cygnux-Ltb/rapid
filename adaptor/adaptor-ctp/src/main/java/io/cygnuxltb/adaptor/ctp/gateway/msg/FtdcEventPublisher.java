package io.cygnuxltb.adaptor.ctp.gateway.msg;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.EventTranslatorThreeArg;
import com.lmax.disruptor.EventTranslatorTwoArg;
import ctp.thostapi.CThostFtdcDepthMarketDataField;
import ctp.thostapi.CThostFtdcInputOrderActionField;
import ctp.thostapi.CThostFtdcInputOrderField;
import ctp.thostapi.CThostFtdcInvestorPositionField;
import ctp.thostapi.CThostFtdcOrderActionField;
import ctp.thostapi.CThostFtdcOrderField;
import ctp.thostapi.CThostFtdcRspInfoField;
import ctp.thostapi.CThostFtdcRspUserLoginField;
import ctp.thostapi.CThostFtdcTradeField;
import ctp.thostapi.CThostFtdcTradingAccountField;
import io.cygnuxltb.adaptor.ctp.gateway.msg.FtdcEvent.FtdcRspType;
import io.mercury.common.concurrent.ring.RingEventbus;

public final class FtdcEventPublisher {

    private final RingEventbus<FtdcEvent> eventbus;

    public FtdcEventPublisher(RingEventbus<FtdcEvent> eventbus) {
        this.eventbus = eventbus;
    }

    public void publishRspError(CThostFtdcRspInfoField rspInfo, int requestId, boolean isLast) {
        eventbus.publish(
                (event, sequence, arg0, arg1, arg2) ->
                        event.setType(FtdcRspType.RspInfo)
                                .setLast(arg2)
                                .getRspInfo().copy(arg0, arg1),
                rspInfo, requestId, isLast);
    }


    public void publishDepthMarketData(CThostFtdcDepthMarketDataField field) {
        eventbus.publish(
                (event, sequence, arg0) ->
                        event.setType(FtdcRspType.DepthMarketData)
                                .getDepthMarketData().copy(arg0),
                field);
    }


    private final EventTranslatorOneArg<FtdcEvent, CThostFtdcOrderField> FtdcOrderTranslator =
            (event, sequence, arg0) -> {
                event.getOrder().copy(arg0);
                event.setType(FtdcRspType.Order);
            };

    public void publish(CThostFtdcOrderField field) {
        eventbus.publish(FtdcOrderTranslator, field);
    }

    private final EventTranslatorOneArg<FtdcEvent, CThostFtdcTradeField> FtdcTradeTranslator =
            (event, sequence, arg0) -> {
                event.getTrade().copy(arg0);
                event.setType(FtdcRspType.Order);
            };


    public void publish(CThostFtdcTradeField field) {
        eventbus.publish(FtdcTradeTranslator, field);
    }

    private final EventTranslatorOneArg<FtdcEvent, CThostFtdcInputOrderActionField> InputOrderActionTranslator =
            (event, sequence, arg0) -> {
                event.getInputOrderAction().copy(arg0);
                event.setType(FtdcRspType.InputOrderAction);
            };


    public void publishMdLogin(CThostFtdcRspUserLoginField rspUserLogin,
                               CThostFtdcRspInfoField rspInfo, int requestID, boolean isLast) {
        eventbus.publish();

    }


    public void publishTraderRspUserLogin(boolean available, int frontId, int sessionId) {
        CThostFtdcRspUserLoginField field;
    }


    public void publish(CThostFtdcInputOrderField field) {

    }

    private final EventTranslatorThreeArg<FtdcEvent, CThostFtdcRspInfoField, Integer, Boolean> rspInfoFieldTranslatorWithNonLast =
            (event, sequence, field, requestId, isLast) -> {
                event.getRspInfo().copy(field, requestId);
                event.setLast(isLast);
                event.setType(FtdcRspType.RspInfo);
            };

    public void publish(CThostFtdcRspInfoField field, int requestId, boolean isLast) {

    }


    public void publish(CThostFtdcOrderField field, boolean isLast) {

    }

    private final EventTranslatorOneArg<FtdcEvent, CThostFtdcInputOrderActionField> inputOrderActionTranslator =
            (event, sequence, field) -> {
                event.getInputOrderAction().copy(field);
                event.setType(FtdcRspType.InputOrderAction);
            };

    public void publish(CThostFtdcInputOrderActionField field) {
        eventbus.publish(inputOrderActionTranslator, field);
    }

    public void publish(CThostFtdcOrderActionField field) {

    }


    public void publish(CThostFtdcTradingAccountField field) {

    }


    public void publish(CThostFtdcInvestorPositionField field, boolean isLast) {

    }


}
