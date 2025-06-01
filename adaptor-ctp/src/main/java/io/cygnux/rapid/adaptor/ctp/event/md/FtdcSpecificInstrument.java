package io.cygnux.rapid.adaptor.ctp.event.md;

import io.mercury.serialization.json.JsonWriter;
import io.cygnux.rapid.adaptor.ctp.event.source.SpecificInstrumentSource;

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










