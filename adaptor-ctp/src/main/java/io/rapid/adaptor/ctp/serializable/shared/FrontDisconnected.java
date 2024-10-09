package io.rapid.adaptor.ctp.serializable.shared;

import io.mercury.serialization.json.JsonWriter;
import io.rapid.adaptor.ctp.serializable.source.EventSource;

/**
 * 通信连接断开
 */
public class FrontDisconnected {

    /**
     * 事件来源
     */
    public EventSource Source;
    /**
     * 经纪公司代码
     */
    public String BrokerID;
    /**
     * 用户代码
     */
    public String UserID;
    /**
     * 错误原因
     * 0x1001 网络读失败
     * 0x1002 网络写失败
     * 0x2001 接收心跳超时
     * 0x2002 发送心跳失败
     * 0x2003 收到错误报文
     */
    public int Reason;

    @Override
    public String toString() {
        return JsonWriter.toJson(this);
    }

}










