package io.cygnux.rapid.ctp.gateway.event.trader;

import io.mercury.serialization.json.JsonWriter;

/**
 * FtdcInputOrderAction
 */
public final class FtdcInputOrderAction {

    /**
     * FTDC响应信息 - 错误代码
     */
    public int ErrorID;
    /**
     * FTDC响应信息 - 错误信息
     */
    public String ErrorMsg;
    /**
     * 是否最后一条信息
     */
    public boolean IsLast;
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
     * 用户代码
     */
    public String UserID;
    /**
     * 合约代码
     */
    public String InstrumentID;
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










