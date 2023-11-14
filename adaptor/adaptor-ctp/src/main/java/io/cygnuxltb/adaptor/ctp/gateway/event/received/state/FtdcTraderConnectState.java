package io.cygnuxltb.adaptor.ctp.gateway.event.received.state;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class FtdcTraderConnectState {

    /// 可用状态
    private boolean available;

    ///交易日
    private String TradingDay;
    ///登录成功时间
    private String LoginTime;
    ///经纪公司代码
    private String BrokerID;
    ///用户代码
    private String UserID;
    ///交易系统名称
    private String SystemName;
    ///前置编号
    private int FrontID;
    ///会话编号
    private int SessionID;
    ///最大报单引用
    private String MaxOrderRef;
}
