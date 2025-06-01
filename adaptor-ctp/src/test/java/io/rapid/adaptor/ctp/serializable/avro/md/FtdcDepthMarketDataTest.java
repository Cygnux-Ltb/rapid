package io.rapid.adaptor.ctp.serializable.avro.md;

import com.alibaba.fastjson2.JSONObject;
import io.mercury.serialization.json.JsonReader;
import io.mercury.serialization.json.JsonWriter;
import io.cygnux.rapid.adaptor.ctp.event.md.FtdcDepthMarketData;
import org.apache.fury.Fury;
import org.apache.fury.config.Language;

public class FtdcDepthMarketDataTest {

    public static void main(String[] args) {

        Fury fury = Fury.builder().withLanguage(Language.JAVA)
                .requireClassRegistration(true)
                .withIntCompressed(false)
                .withLongCompressed(false)
                .build();
        // Registering types can reduce class name serialization overhead, but not mandatory.
        // If class registration enabled, all custom types must be registered.
        fury.register(FtdcDepthMarketData.class);

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

        byte[] bytes = fury.serialize(md);
        System.out.println("bytes.length -> " + bytes.length);
        System.out.println("new String(bytes) -> " + new String(bytes));

        Object deserialize = fury.deserialize(bytes);
        System.out.println("fury.deserialize -> " + deserialize.getClass().getName());
        System.out.println("fury.deserialize -> " + JsonWriter.toPrettyJsonHasNulls(deserialize));


    }

    public static class TestObject {
        public String SHETime;
        public String DECTime;
        public int RequestID;
        public boolean IsLast;
    }

}