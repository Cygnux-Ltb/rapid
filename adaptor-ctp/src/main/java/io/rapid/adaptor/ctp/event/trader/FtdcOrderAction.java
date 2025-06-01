package io.rapid.adaptor.ctp.event.trader;

import io.mercury.serialization.json.JsonWriter;

/**
 * FtdcOrderAction
 */
public final class FtdcOrderAction {

    /**
     * FTDC响应信息 - 错误代码
     */
    public int ErrorID;
    /**
     * FTDC响应信息 - 错误信息
     */
    public String ErrorMsg;
    /**
     * 经纪公司代码
     */
    public String BrokerID;
    /**
     * 投资者代码
     */
    public String InvestorID;
    /**
     * 报单操作引用
     */
    public int OrderActionRef;
    /**
     * 报单引用
     */
    public String OrderRef;
    /**
     * 请求编号
     */
    public int RequestID;
    /**
     * 前置编号
     */
    public int FrontID;
    /**
     * 会话编号
     */
    public int SessionID;
    /**
     * 交易所代码
     */
    public String ExchangeID;
    /**
     * 报单编号
     */
    public String OrderSysID;
    /**
     * 操作标志
     */
    public int ActionFlag;
    /**
     * 价格
     */
    public double LimitPrice;
    /**
     * 数量变化
     */
    public int VolumeChange;
    /**
     * 操作日期
     */
    public String ActionDate;
    /**
     * 操作时间
     */
    public String ActionTime;
    /**
     * 交易所交易员代码
     */
    public String TraderID;
    /**
     * 安装编号
     */
    public int InstallID;
    /**
     * 本地报单编号
     */
    public String OrderLocalID;
    /**
     * 操作本地编号
     */
    public String ActionLocalID;
    /**
     * 会员代码
     */
    public String ParticipantID;
    /**
     * 客户代码
     */
    public String ClientID;
    /**
     * 业务单元
     */
    public String BusinessUnit;
    /**
     * 报单操作状态
     */
    public int OrderActionStatus;
    /**
     * 用户代码
     */
    public String UserID;
    /**
     * 状态信息
     */
    public String StatusMsg;
    /**
     * 合约代码
     */
    public String InstrumentID;
    /**
     * 营业部编号
     */
    public String BranchID;
    /**
     * 投资单元代码
     */
    public String InvestUnitID;
    /**
     * IP地址
     */
    public String IPAddress;
    /**
     * MAC地址
     */
    public String MacAddress;

    @Override
    public String toString() {
        return JsonWriter.toJson(this);
    }

}










