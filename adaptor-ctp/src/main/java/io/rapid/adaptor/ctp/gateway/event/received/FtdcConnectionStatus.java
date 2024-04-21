package io.cygnuxltb.adaptor.ctp.gateway.event.received;

import ctp.thostapi.CThostFtdcRspUserLoginField;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class FtdcConnectionStatus {

    /// 可用状态
    private boolean available;

    /// 交易日
    private String TradingDay;

    /// 登录成功时间
    private String LoginTime;

    /// 经纪公司代码
    private String BrokerID;

    /// 用户代码
    private String UserID;

    /// 交易系统名称
    private String SystemName;

    /// 前置编号
    private int FrontID;

    /// 会话编号
    private int SessionID;

    /// 最大报单引用
    private String MaxOrderRef;

    /// 上期所时间
    private String SHFETime;

    /// 大商所时间
    private String DCETime;

    /// 郑商所时间
    private String CZCETime;

    /// 中金所时间
    private String FFEXTime;

    /// 能源中心时间
    private String INETime;

    public FtdcConnectionStatus read(CThostFtdcRspUserLoginField field) {
        return this
                // 交易日, 登录成功时间
                .setTradingDay(field.getTradingDay())
                .setLoginTime(field.getLoginTime())
                // 经纪公司代码, 用户代码, 交易系统名称
                .setBrokerID(field.getBrokerID())
                .setUserID(field.getUserID())
                .setSystemName(field.getSystemName())
                // 前置编号, 会话编号, 最大报单引用
                .setFrontID(field.getFrontID())
                .setSessionID(field.getSessionID())
                .setMaxOrderRef(field.getMaxOrderRef())
                // 上期所时间, 大商所时间, 郑商所时间, 中金所时间, 能源中心时间
                .setSHFETime(field.getSHFETime())
                .setDCETime(field.getDCETime())
                .setCZCETime(field.getCZCETime())
                .setFFEXTime(field.getFFEXTime())
                .setINETime(field.getINETime());
    }

}
