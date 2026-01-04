package io.cygnux.rapid.adapter.ctp.serializable.avro.md;

import com.alibaba.fastjson2.JSONObject;
import io.mercury.serialization.json.JsonReader;
import io.mercury.serialization.json.JsonWriter;
import io.cygnux.rapid.gateway.ctp.event.md.FtdcDepthMarketData;
import org.apache.fory.Fory;
import org.apache.fory.config.Language;

public class FtdcDepthMarketDataTest {

    public static void main(String[] args) {

        Fory fory = Fory.builder().withLanguage(Language.JAVA)
                .requireClassRegistration(true)
                .withIntCompressed(false)
                .withLongCompressed(false)
                .build();
        // Registering types can reduce class name serialization overhead, but not mandatory.
        // If class registration enabled, all custom types must be registered.
        fory.register(FtdcDepthMarketData.class);

        FtdcDepthMarketData md = new FtdcDepthMarketData();
        md.InstrumentID = ("ag2412");
        md.TradingDay = ("20240903");
        md.LastPrice = (179.35);

        String json = JsonWriter.toPrettyJsonHasNulls(md);
        System.out.println(json);

        FtdcDepthMarketData md1 = JsonReader.toObject(json, FtdcDepthMarketData.class);
        System.out.println(JsonWriter.toJson(md1));
        System.out.println(md);
        System.out.println(md1);

        JSONObject jsonObject = JsonReader.parseJson(json);
        Object lastPrice = jsonObject.get("LastPrice");
        System.out.println(lastPrice.getClass().getName());

        TestObject o = new TestObject();

        o.DECTime = ("23:35");
        o.SHETime = ("23:36");
        o.IsLast = (true);
        o.RequestID = (2454);
        System.out.println(JsonWriter.toPrettyJsonHasNulls(o));

        byte[] bytes = fory.serialize(md);
        System.out.println("bytes.length -> " + bytes.length);
        System.out.println("new String(bytes) -> " + new String(bytes));

        Object deserialize = fory.deserialize(bytes);
        System.out.println("fory.deserialize -> " + deserialize.getClass().getName());
        System.out.println("fory.deserialize -> " + JsonWriter.toPrettyJsonHasNulls(deserialize));

    }

    public static class TestObject {
        public String SHETime;
        public String DECTime;
        public int RequestID;
        public boolean IsLast;
    }

}