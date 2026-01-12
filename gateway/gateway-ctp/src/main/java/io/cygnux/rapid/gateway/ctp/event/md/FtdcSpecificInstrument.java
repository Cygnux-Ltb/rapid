package io.cygnux.rapid.gateway.ctp.event.md;

import io.cygnux.rapid.gateway.ctp.event.source.SpecificInstrumentSource;
import io.mercury.serialization.json.JsonWriter;

/**
 * 指定的合约
 */
public class FtdcSpecificInstrument {

    /**
     * SpecificInstrument Source
     */
    public SpecificInstrumentSource Source;
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
     * Instrument ID
     */
    public String InstrumentID;

    @Override
    public String toString() {
        return JsonWriter.toJson(this);
    }

}










