package io.cygnux.rapid.ctp.gateway.event;

import io.mercury.common.epoch.EpochRecord;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.nio.ByteBuffer;

/**
 * FTDC响应包装
 */
@Getter
@Setter
@Accessors(chain = true)
@Deprecated
public class FtdcRsp {

    /**
     * 响应类型
     */
    private FtdcRspType type;
    /**
     * 时间戳
     */
    private EpochRecord epochRecord;
    /**
     * FTDC响应内容 - 已序列化
     */
    private ByteBuffer content;

}










