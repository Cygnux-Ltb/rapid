package io.cygnuxltb.adaptor.ctp.gateway.msg;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.EventTranslatorThreeArg;
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


    public void publishMdAvailable(CThostFtdcRspUserLoginField rspUserLogin,
                                   CThostFtdcRspInfoField rspInfo, int requestID, boolean isLast) {
        eventbus.publish();

    }

    /**
     * 发布行情可用事件
     * @param reason
     */
    public void publishMdUnavailable(int reason) {

    }


    public void publishTraderRspUserLogin(boolean available, int frontId, int sessionId) {
        CThostFtdcRspUserLoginField field;
    }


    public void publish(CThostFtdcInputOrderField field) {

    }

    private final EventTranslatorThreeArg<FtdcEvent, CThostFtdcRspInfoField, Integer, Boolean> rspInfoFieldTranslatorWithNonLast =
            (event, sequence, field, requestId, isLast) -> {
                event.getFtdcRspInfo().load(field, requestId);
                event.setLast(isLast);
                event.setType(FtdcRspType.RspInfo);
            };


    public void publish(CThostFtdcOrderField field, boolean isLast) {

    }

    private final EventTranslatorOneArg<FtdcEvent, CThostFtdcInputOrderActionField> inputOrderActionTranslator =
            (event, sequence, field) -> {
                event.getFtdcInputOrderAction().load(field);
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
