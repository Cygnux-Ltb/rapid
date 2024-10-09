package io.rapid.adaptor.ctp.serializable.shared;

import io.mercury.serialization.json.JsonWriter;
import io.rapid.adaptor.ctp.serializable.source.EventSource;

/**
 * 错误应答
 */
public class RspError {

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

    @Override
    public String toString() {
        return JsonWriter.toJson(this);
    }

}










