package io.cygnux.rapid.console.controller;

import io.mercury.common.character.Charsets;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.mercury.common.util.StringSupport;
import io.mercury.serialization.json.JsonReader;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;

import java.io.IOException;
import java.util.List;

public final class ControllerUtil {

    private static final Logger log = Log4j2LoggerFactory.getLogger(ControllerUtil.class);

    /**
     * 检查参数列表
     *
     * @param objs Object...
     * @return boolean
     */
    public static boolean paramIsNull(Object... objs) {
        for (Object obj : objs)
            if (obj != null)
                return false;
        return true;
    }

    private static String getBody(HttpServletRequest request) {
        try {
            return IOUtils.toString(request.getInputStream(), Charsets.UTF8);
        } catch (IOException e) {
            log.error("get body content has IOException -> {}", e.getMessage(), e);
            return null;
        }
    }

    private static boolean checkBody(String body, HttpServletRequest request) {
        if (StringSupport.isNullOrEmpty(body)) {
            log.error("body content is null or empty");
            return false;
        }
        return true;
    }

    /**
     * @param request HttpServletRequest
     * @param clazz   Class<T>
     * @param <T>     Return Type
     * @return T Return Object
     */
    public static <T> T bodyToObject(HttpServletRequest request, Class<T> clazz) {
        var body = getBody(request);
        return checkBody(body, request)
                ? JsonReader.toObject(body, clazz)
                : null;
    }

    /**
     * @param request HttpServletRequest
     * @param clazz   Class<T>
     * @param <T>     Return Type
     * @return List<T> Return List
     */
    public static <T> List<T> bodyToList(HttpServletRequest request, Class<T> clazz) {
        var body = getBody(request);
        return checkBody(body, request)
                ? JsonReader.toList(body, clazz)
                : null;
    }



}
