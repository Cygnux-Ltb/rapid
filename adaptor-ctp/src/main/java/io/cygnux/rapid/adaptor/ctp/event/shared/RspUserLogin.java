package io.cygnux.rapid.adaptor.ctp.event.shared;

import io.mercury.serialization.json.JsonWriter;
import io.cygnux.rapid.adaptor.ctp.event.source.EventSource;

/**
 * 用户登录响应<br>
 * <p>
 * [数据结构原型]
 * <pre>
 * ///用户登录应答
 * struct CThostFtdcRspUserLoginField
 * {
 * ///交易日
 * TThostFtdcDateType	TradingDay;
 * ///登录成功时间
 * TThostFtdcTimeType	LoginTime;
 * ///经纪公司代码
 * TThostFtdcBrokerIDType	BrokerID;
 * ///用户代码
 * TThostFtdcUserIDType	UserID;
 * ///交易系统名称
 * TThostFtdcSystemNameType	SystemName;
 * ///前置编号
 * TThostFtdcFrontIDType	FrontID;
 * ///会话编号
 * TThostFtdcSessionIDType	SessionID;
 * ///最大报单引用
 * TThostFtdcOrderRefType	MaxOrderRef;
 * ///上期所时间
 * TThostFtdcTimeType	SHFETime;
 * ///大商所时间
 * TThostFtdcTimeType	DCETime;
 * ///郑商所时间
 * TThostFtdcTimeType	CZCETime;
 * ///中金所时间
 * TThostFtdcTimeType	FFEXTime;
 * ///能源中心时间
 * TThostFtdcTimeType	INETime;
 * ///后台版本信息
 * TThostFtdcSysVersionType	SysVersion;
 * ///广期所时间
 * TThostFtdcTimeType	GFEXTime;
 * };
 * </pre>
 */
public class RspUserLogin {

    /**
     * 事件来源
     */
    public EventSource Source;
    /**
     * FTDC响应信息 - 错误代码
     */
    public int ErrorID;
    /**
     * FTDC响应信息 - 错误信息
     */
    public String ErrorMsg;
    /**
     * 请求ID
     */
    public int RequestID;
    /**
     * 是否最后一条信息
     */
    public boolean IsLast;
    /**
     * 交易日
     */
    public String TradingDay;
    /**
     * 登录成功时间
     */
    public String LoginTime;
    /**
     * 经纪公司代码
     */
    public String BrokerID;
    /**
     * 用户代码
     */
    public String UserID;
    /**
     * 交易系统名称
     */
    public String SystemName;
    /**
     * 前置编号
     */
    public int FrontID;
    /**
     * 会话编号
     */
    public int SessionID;
    /**
     * 最大报单引用
     */
    public String MaxOrderRef;
    /**
     * 上期所时间
     */
    public String SHFETime;
    /**
     * 大商所时间
     */
    public String DCETime;
    /**
     * 郑商所时间
     */
    public String CZCETime;
    /**
     * 中金所时间
     */
    public String FFEXTime;
    /**
     * 能源中心时间
     */
    public String INETime;
    /**
     * 广期所时间
     */
    public String GFEXTime;
    /**
     * 后台版本信息
     */
    public String SysVersion;

    @Override
    public String toString() {
        return JsonWriter.toJson(this);
    }

}










