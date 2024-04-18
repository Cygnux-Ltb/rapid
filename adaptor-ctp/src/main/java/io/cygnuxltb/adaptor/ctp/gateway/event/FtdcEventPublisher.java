package io.cygnuxltb.adaptor.ctp.gateway.event;

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
import ctp.thostapi.CThostFtdcUserLogoutField;
import io.cygnuxltb.adaptor.ctp.gateway.event.FtdcEvent.FtdcRspType;
import io.mercury.common.concurrent.ring.RingEventbus.MpRingEventbus;

public final class FtdcEventPublisher {

    private final MpRingEventbus<FtdcEvent> eventbus;

    public FtdcEventPublisher(MpRingEventbus<FtdcEvent> eventbus) {
        this.eventbus = eventbus;
    }

    /**
     * @param field     CThostFtdcRspInfoField
     * @param requestId int
     * @param isLast    boolean
     */
    public void publish(CThostFtdcRspInfoField field, int requestId, boolean isLast) {
        eventbus.publish((event, sequence) ->
                event.setType(FtdcRspType.RspInfo)
                        .setRequestId(requestId)
                        .setLast(isLast)
                        .getRspInfo()
                        .read(field));
    }

    /**
     * @param field CThostFtdcDepthMarketDataField
     */
    public void publish(CThostFtdcDepthMarketDataField field) {
        eventbus.publish((event, sequence) ->
                event.setType(FtdcRspType.DepthMarketData)
                        .getDepthMarketData()
                        .read(field));
    }


    /**
     * @param field  CThostFtdcOrderField
     * @param isLast boolean
     */
    public void publish(CThostFtdcOrderField field, boolean isLast) {
        eventbus.publish((event, sequence) ->
                event.setType(FtdcRspType.Order)
                        .setLast(isLast)
                        .getOrder()
                        .read(field));
    }

    /**
     * @param field CThostFtdcTradeField
     */
    public void publish(CThostFtdcTradeField field) {
        eventbus.publish((event, sequence) ->
                event.setType(FtdcRspType.Trade)
                        .getTrade()
                        .read(field));
    }

    /**
     * @param field     CThostFtdcRspUserLoginField
     * @param rspInfo   CThostFtdcRspInfoField
     * @param requestId requestId
     * @param isLast    isLast
     */
    public void publishMdAvailable(CThostFtdcRspUserLoginField field,
                                   CThostFtdcRspInfoField rspInfo,
                                   int requestId, boolean isLast) {
        eventbus.publish((event, sequence) ->
                event.setType(FtdcRspType.MdConnectionStatus)
                        .setRequestId(requestId)
                        .setLast(isLast)
                        .getConnectionStatus()
                        .read(field));
    }

    /**
     * 发布行情不可用事件
     *
     * @param reason int
     */
    public void publishMdUnavailable(int reason) {

    }

    /**
     * @param field     CThostFtdcRspUserLoginField
     * @param rspInfo   CThostFtdcRspInfoField
     * @param requestId int
     * @param isLast    boolean
     */
    public void publishTraderAvailable(CThostFtdcRspUserLoginField field,
                                       CThostFtdcRspInfoField rspInfo,
                                       int requestId, boolean isLast) {
        eventbus.publish((event, sequence) ->
                event.setType(FtdcRspType.TraderConnectionStatus)
                        .setRequestId(requestId)
                        .setLast(isLast)
                        .getConnectionStatus()
                        .read(field));
    }


    public void publishTraderUnavailable(int reason) {

    }

    /**
     * @param field CThostFtdcInputOrderField
     */
    public void publish(CThostFtdcInputOrderField field) {
        eventbus.publish((event, sequence) ->
                event.setType(FtdcRspType.InputOrder)
                        .getInputOrder()
                        .read(field));
    }

    /**
     * @param field CThostFtdcInputOrderActionField
     */
    public void publish(CThostFtdcInputOrderActionField field) {
        eventbus.publish((event, sequence) ->
                event.setType(FtdcRspType.InputOrderAction)
                        .getInputOrderAction()
                        .read(field));
    }

    /**
     * @param field CThostFtdcOrderActionField
     */
    public void publish(CThostFtdcOrderActionField field) {
        eventbus.publish((event, sequence) ->
                event.setType(FtdcRspType.OrderAction)
                        .getOrderAction()
                        .read(field));
    }


    public void publish(CThostFtdcTradingAccountField field) {

    }


    public void publish(CThostFtdcInvestorPositionField field, boolean isLast) {

    }


    public void publishInstrumentStatus(CThostFtdcInstrumentStatusField field) {

    }

    public void publishTraderUserLogout(CThostFtdcUserLogoutField field, int requestID, boolean isLast) {
    }


}
