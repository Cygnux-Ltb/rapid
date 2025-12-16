package io.rapid.adaptor.ctp;

import io.mercury.serialization.json.JsonReader;
import io.mercury.serialization.json.JsonWriter;
import io.cygnux.gateway.ctp.event.shared.UserLogout;
import io.cygnux.gateway.ctp.event.source.EventSource;

import java.io.IOException;

public class AvroTest {

    public static void main(String[] args) throws IOException {

        UserLogout userLogout = new UserLogout();

        userLogout.BrokerID = ("Test Broker");
        userLogout.UserID = ("Test User");
        userLogout.IsLast = (true);
        userLogout.Source = (EventSource.TD);
        userLogout.ErrorID = (0);
        userLogout.ErrorMsg = ("");

        String json = JsonWriter.toJson(userLogout);

        System.out.println(json);

        UserLogout logout = JsonReader.toObject(json, UserLogout.class);

        System.out.println(userLogout == logout);

        assert logout != null;
        System.out.println(logout.BrokerID);

        System.out.println((int) '3');

    }


}
