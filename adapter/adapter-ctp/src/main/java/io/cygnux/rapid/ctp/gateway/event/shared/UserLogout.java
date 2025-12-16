package io.cygnux.rapid.ctp.gateway.event.shared;

import io.mercury.serialization.json.JsonWriter;
import io.cygnux.rapid.ctp.gateway.event.source.EventSource;

/**
 * 用户登出请求(响应)
 */
public class UserLogout {

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
     * 经纪公司代码
     */
    public String BrokerID;
    /**
     * 用户代码
     */
    public String UserID;

    @Override
    public String toString() {
        return JsonWriter.toJson(this);
    }

}










