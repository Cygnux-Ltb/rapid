package io.rapid.adaptor.ctp.consts;

public class FtdcConstDictionary {

    String getFrontDisconnectedReason(int Reason) {
        return switch (Reason) {
            case 0x1001 -> "网络读失败";
            case 0x1002 -> "网络写失败";
            case 0x2001 -> "接收心跳超时";
            case 0x2002 -> "发送心跳失败";
            case 0x2003 -> "收到错误报文";
            default -> "未知";
        };
    }

}
