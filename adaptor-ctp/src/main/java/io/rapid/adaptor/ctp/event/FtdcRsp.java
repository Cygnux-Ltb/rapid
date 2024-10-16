package io.rapid.adaptor.ctp.event;

import io.mercury.common.epoch.EpochRecord;

import java.nio.ByteBuffer;

/**
 * FTDC响应包装
 */
public class FtdcRsp {

    /**
     * 响应类型
     */
    public FtdcRspType RspType;
    /**
     * 时间戳
     */
    public EpochRecord EpochRecord;
    /**
     * FTDC响应内容 - 已序列化
     */
    public ByteBuffer RspContent;

}










