package io.rapid.adaptor.ctp.gateway.event.received.trader;

import ctp.thostapi.CThostFtdcInputOrderActionField;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public final class FtdcInputOrderAction {

    /// 经纪公司代码
    private String BrokerID;

    /// 投资者代码
    private String InvestorID;

    /// 报单操作引用
    private int OrderActionRef;

    /// 报单引用
    private String OrderRef;

    /// 请求编号
    private int RequestID;

    /// 前置编号
    private int FrontID;

    /// 会话编号
    private int SessionID;

    /// 交易所代码
    private String ExchangeID;

    /// 报单编号
    private String OrderSysID;

    /// 操作标志
    private char ActionFlag;

    /// 价格
    private double LimitPrice;

    /// 数量变化
    private int VolumeChange;

    /// 用户代码
    private String UserID;

    /// 合约代码
    private String InstrumentID;

    /// 投资单元代码
    private String InvestUnitID;

    /// IP地址
    private String IPAddress;

    /// MAC地址
    private String MacAddress;

    public FtdcInputOrderAction read(CThostFtdcInputOrderActionField field) {
        return this
                .setBrokerID(field.getBrokerID())
                .setInvestorID(field.getInvestorID())
                .setOrderActionRef(field.getOrderActionRef())
                .setOrderRef(field.getOrderRef())
                .setRequestID(field.getRequestID())
                .setFrontID(field.getFrontID())
                .setSessionID(field.getSessionID())
                .setExchangeID(field.getExchangeID())
                .setOrderSysID(field.getOrderSysID())
                .setActionFlag(field.getActionFlag())
                .setLimitPrice(field.getLimitPrice())
                .setVolumeChange(field.getVolumeChange())
                .setUserID(field.getUserID())
                .setInstrumentID(field.getInstrumentID())
                .setInvestUnitID(field.getInvestUnitID())
                .setIPAddress(field.getIPAddress())
                .setMacAddress(field.getMacAddress());
    }

}
