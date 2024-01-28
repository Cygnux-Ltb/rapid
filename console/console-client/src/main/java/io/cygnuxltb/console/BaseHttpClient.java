package io.cygnuxltb.console;

import io.mercury.common.http.PathParams.PathParam;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static io.mercury.common.http.JreHttpClient.GET;
import static io.mercury.common.http.JreHttpClient.PUT;
import static io.mercury.common.http.PathParams.with;
import static io.mercury.common.log4j2.Log4j2LoggerFactory.getLogger;
import static io.mercury.common.util.StringSupport.isNullOrEmpty;
import static io.mercury.serialization.json.JsonParser.toList;
import static io.mercury.serialization.json.JsonWrapper.toJson;

class BaseHttpClient {

    private static final Logger log = getLogger(BaseHttpClient.class);

    protected <T> List<T> sendGetRequest(Class<T> type, String uri, PathParam... params) {
        try {
            var response = GET(with(params).toFullUri(uri));
            int statusCode = response.statusCode();
            if (statusCode > 307)
                throw new RuntimeException(
                        STR."GET request uri: [\{uri}] return status code: [\{statusCode}]");
            String body = response.body();
            return isNullOrEmpty(body)
                    ? new ArrayList<>()
                    : toList(body, type);
        } catch (Exception e) {
            log.error("catch exception message -> {}", e.getMessage(), e);
            throw new RuntimeException(STR."Exception Message -> \{e.getMessage()}", e);
        }
    }

    protected boolean sendPutRequest(Object body, String uri, PathParam... params) {
        if (body == null)
            throw new IllegalArgumentException("Put request body does not allowed null or empty string.");
        try {
            var response = PUT(with(params).toFullUri(uri), toJson(body));
            int statusCode = response.statusCode();
            if (statusCode > 307) {
                log.error("PUT request uri: [{}] -> {}", uri, statusCode);
                return false;
            }
            return true;
        } catch (Exception e) {
            log.error("catch exception message -> {}", e.getMessage(), e);
            throw new RuntimeException(STR."Exception Message -> \{e.getMessage()}", e);
        }
    }

}
