package io.rapid.adaptor.ctp.serializable.shared;

import io.mercury.serialization.json.JsonWriter;
import io.rapid.adaptor.ctp.serializable.source.EventSource;

/**
 * 心跳超时警告
 */
public class HeartBeatWarning {

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
     * 距离上次接收报文的时间
     */
    public int TimeLapse;

    @Override
    public String toString() {
        return JsonWriter.toJson(this);
    }

}










