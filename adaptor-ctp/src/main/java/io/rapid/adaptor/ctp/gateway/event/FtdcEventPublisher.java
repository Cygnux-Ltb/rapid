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
import ctp.thostapi.CThostFtdcTradeField;
import ctp.thostapi.CThostFtdcTradingAccountField;
import ctp.thostapi.CThostFtdcUserLogoutField;
import io.mercury.common.concurrent.ring.RingEventbus.MpRingEventbus;
import org.springframework.stereotype.Component;

import static io.rapid.adaptor.ctp.gateway.event.FtdcEvent.FtdcRspType.DepthMarketData;
import static io.rapid.adaptor.ctp.gateway.event.FtdcEvent.FtdcRspType.InputOrder;
import static io.rapid.adaptor.ctp.gateway.event.FtdcEvent.FtdcRspType.InputOrderAction;
import static io.rapid.adaptor.ctp.gateway.event.FtdcEvent.FtdcRspType.MdConnectionStatus;
import static io.rapid.adaptor.ctp.gateway.event.FtdcEvent.FtdcRspType.Order;
import static io.rapid.adaptor.ctp.gateway.event.FtdcEvent.FtdcRspType.OrderAction;
import static io.rapid.adaptor.ctp.gateway.event.FtdcEvent.FtdcRspType.RspInfo;
import static io.rapid.adaptor.ctp.gateway.event.FtdcEvent.FtdcRspType.Trade;
import static io.rapid.adaptor.ctp.gateway.event.FtdcEvent.FtdcRspType.TraderConnectionStatus;

@Component
public final class FtdcEventPublisher {

    private final MpRingEventbus<FtdcEvent> eventbus;

    /**
     * @param eventbus MpRingEventbus<FtdcEvent>
     */
    public FtdcEventPublisher(MpRingEventbus<FtdcEvent> eventbus) {
        this.eventbus = eventbus;
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
                event.setType(MdConnectionStatus)
                        .setRequestId(requestId)
                        .setLast(isLast)
                        .getConnectionStatus()
                        .copy(field));
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
                event.setType(TraderConnectionStatus)
                        .setRequestId(requestId)
                        .setLast(isLast)
                        .getConnectionStatus()
                        .copy(field));
    }


    public void publishTraderUnavailable(int reason) {

    }


    /**
     * @param field     CThostFtdcRspInfoField
     * @param requestId int
     * @param isLast    boolean
     */
    public void publish(CThostFtdcRspInfoField field, int requestId, boolean isLast) {
        eventbus.publish((event, sequence) ->
                event.setType(RspInfo)
                        .setRequestId(requestId)
                        .setLast(isLast)
                        .getRspInfo()
                        .copy(field));
    }

    /**
     * @param field CThostFtdcDepthMarketDataField
     */
    public void publish(CThostFtdcDepthMarketDataField field) {
        eventbus.publish((event, sequence) ->
                event.setType(DepthMarketData)
                        .getDepthMarketData()
                        .copy(field));
    }


    /**
     * @param field  CThostFtdcOrderField
     * @param isLast boolean
     */
    public void publish(CThostFtdcOrderField field, boolean isLast) {
        eventbus.publish((event, sequence) ->
                event.setType(Order)
                        .setLast(isLast)
                        .getOrder()
                        .copy(field));
    }

    /**
     * @param field CThostFtdcTradeField
     */
    public void publish(CThostFtdcTradeField field) {
        eventbus.publish((event, sequence) ->
                event.setType(Trade)
                        .getTrade()
                        .copy(field));
    }


    /**
     * @param field CThostFtdcInputOrderField
     */
    public void publish(CThostFtdcInputOrderField field) {
        eventbus.publish((event, sequence) ->
                event.setType(InputOrder)
                        .getInputOrder()
                        .copy(field));
    }

    /**
     * @param field CThostFtdcInputOrderActionField
     */
    public void publish(CThostFtdcInputOrderActionField field) {
        eventbus.publish((event, sequence) ->
                event.setType(InputOrderAction)
                        .getInputOrderAction()
                        .copy(field));
    }

    /**
     * @param field CThostFtdcOrderActionField
     */
    public void publish(CThostFtdcOrderActionField field) {
        eventbus.publish((event, sequence) ->
                event.setType(OrderAction)
                        .getOrderAction()
                        .copy(field));
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
