package io.rapid.adaptor.ctp.gateway.event.recv.trader;

import ctp.thostapi.CThostFtdcOrderActionField;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public final class FtdcOrderAction {

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

    /// 操作日期
    private String ActionDate;

    /// 操作时间
    private String ActionTime;

    /// 交易所交易员代码
    private String TraderID;

    /// 安装编号
    private int InstallID;

    /// 本地报单编号
    private String OrderLocalID;

    /// 操作本地编号
    private String ActionLocalID;

    /// 会员代码
    private String ParticipantID;

    /// 客户代码
    private String ClientID;

    /// 业务单元
    private String BusinessUnit;

    /// 报单操作状态
    private char OrderActionStatus;

    /// 用户代码
    private String UserID;

    /// 状态信息
    private String StatusMsg;

    /// 合约代码
    private String InstrumentID;

    /// 营业部编号
    private String BranchID;

    /// 投资单元代码
    private String InvestUnitID;

    /// IP地址
    private String IPAddress;

    /// MAC地址
    private String MacAddress;

    /**
     * @param OrderActionField CThostFtdcOrderActionField
     * @return FtdcOrderAction
     */
    public FtdcOrderAction copy(CThostFtdcOrderActionField OrderActionField) {
        return this
                .setBrokerID(OrderActionField.getBrokerID())

                .setInvestorID(OrderActionField.getInvestorID())

                .setOrderActionRef(OrderActionField.getOrderActionRef())

                .setOrderRef(OrderActionField.getOrderRef())

                .setRequestID(OrderActionField.getRequestID())

                .setFrontID(OrderActionField.getFrontID())

                .setSessionID(OrderActionField.getSessionID())

                .setExchangeID(OrderActionField.getExchangeID())

                .setOrderSysID(OrderActionField.getOrderSysID())

                .setActionFlag(OrderActionField.getActionFlag())

                .setLimitPrice(OrderActionField.getLimitPrice())

                .setVolumeChange(OrderActionField.getVolumeChange())

                .setActionDate(OrderActionField.getActionDate())

                .setActionTime(OrderActionField.getActionTime())

                .setTraderID(OrderActionField.getTraderID())

                .setInstallID(OrderActionField.getInstallID())

                .setOrderLocalID(OrderActionField.getOrderLocalID())

                .setActionLocalID(OrderActionField.getActionLocalID())

                .setParticipantID(OrderActionField.getParticipantID())

                .setClientID(OrderActionField.getClientID())

                .setBusinessUnit(OrderActionField.getBusinessUnit())

                .setOrderActionStatus(OrderActionField.getOrderActionStatus())

                .setUserID(OrderActionField.getUserID())

                .setStatusMsg(OrderActionField.getStatusMsg())

                .setInstrumentID(OrderActionField.getInstrumentID())

                .setBranchID(OrderActionField.getBranchID())

                .setInvestUnitID(OrderActionField.getInvestUnitID())

                .setIPAddress(OrderActionField.getIPAddress())

                .setMacAddress(OrderActionField.getMacAddress())

                ;
    }

}
