package io.rapid.adaptor.ctp.consts;

public interface FtdcFrontDisconnectedReason {

    static String getPrompt(int ftdcReason) {
        return switch (ftdcReason) {
            case 0x0001 -> "0x0001:适配器已关闭";
            case 0x1001 -> "0x1001:网络读失败";
            case 0x1002 -> "0x1002:网络写失败";
            case 0x2001 -> "0x2001:接收心跳超时";
            case 0x2002 -> "0x2002:发送心跳失败";
            case 0x2003 -> "0x2003:收到错误报文";
            default -> "NONE";
        };
    }

}
