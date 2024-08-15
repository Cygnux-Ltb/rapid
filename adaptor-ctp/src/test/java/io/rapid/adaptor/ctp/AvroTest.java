package io.rapid.adaptor.ctp;

import io.mercury.serialization.json.JsonParser;
import io.mercury.serialization.json.JsonWriter;
import io.rapid.adaptor.ctp.serializable.avro.shared.EventSource;
import io.rapid.adaptor.ctp.serializable.avro.shared.UserLogout;

import java.io.IOException;
import java.nio.ByteBuffer;

public class AvroTest {

    public static void main(String[] args) throws IOException {

        UserLogout userLogout = new UserLogout();

        userLogout.setBrokerID("Test Broker").setUserID("Test User")
                .setIsLast(true).setSource(EventSource.TD)
                .setErrorID(0).setErrorMsg("");

        String json = JsonWriter.toJson(userLogout);

        ByteBuffer buffer = userLogout.toByteBuffer();

        System.out.println(buffer);

        System.out.println(json);

        UserLogout logout = JsonParser.toObject(json, UserLogout.class);

        System.out.println(userLogout == logout);

        assert logout != null;
        System.out.println(logout.getBrokerID());

    }


}
