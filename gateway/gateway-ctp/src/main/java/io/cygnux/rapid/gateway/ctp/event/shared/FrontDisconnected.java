package io.cygnux.rapid.gateway.ctp.event.shared;

import io.cygnux.rapid.gateway.ctp.event.source.EventSource;
import io.mercury.serialization.json.JsonWriter;

/**
 * 通信连接断开
 */
public class FrontDisconnected {

    /**
     * 事件来源
     */
    public EventSource Source;

    /**
     * 错误原因 + 错误提示信息<br>
     * 0x1001 网络读失败<br>
     * 0x1002 网络写失败<br>
     * 0x2001 接收心跳超时<br>
     * 0x2002 发送心跳失败<br>
     * 0x2003 收到错误报文
     */
    public String Msg;

    @Override
    public String toString() {
        return JsonWriter.toJson(this);
    }

}










