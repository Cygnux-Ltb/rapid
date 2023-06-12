package io.cygnuxltb.console.controller.util;

import io.mercury.common.character.Charsets;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.mercury.common.util.StringSupport;
import io.mercury.serialization.json.JsonParser;
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

    public static <T> T bodyToObject(HttpServletRequest request, Class<T> clazz) {
        var body = getBody(request);
        if (StringSupport.isNullOrEmpty(body)) {
            log.error("body content is null or empty");
            return null;
        }
        return JsonParser.toObject(body, clazz);
    }

    public static <T> List<T> bodyToList(HttpServletRequest request, Class<T> clazz) {
        var body = getBody(request);
        if (StringSupport.isNullOrEmpty(body)) {
            log.error("body content is null or empty");
            return null;
        }
        return JsonParser.toList(body, clazz);
    }

    public static boolean illegalStrategyId(int strategyId, Logger logger) {
        if (strategyId < 0) {
            logger.error("illegal param -> strategyId=={}", strategyId);
            return true;
        }
        return false;
    }

    public static boolean illegalTradingDay(int tradingDay, Logger logger) {
        if (tradingDay <= 0) {
            logger.error("illegal param -> tradingDay=={}", tradingDay);
            return true;
        }
        return false;
    }

    public static boolean illegalTradingDay(int startTradingDay, int endTradingDay,
                                            Logger logger) {
        if (startTradingDay <= 0 || endTradingDay < startTradingDay) {
            logger.error("illegal param -> startTradingDay=={}, endTradingDay=={}",
                    startTradingDay, endTradingDay);
            return true;
        }
        return false;
    }

    public static boolean illegalOrdSysId(long ordSysId, Logger logger) {
        if (ordSysId <= 0) {
            logger.error("illegal param -> ordSysId=={}", ordSysId);
            return true;
        }
        return false;
    }

    public static boolean illegalStringParam(String paramName, String param, Logger logger) {
        if (StringSupport.isNullOrEmpty(param)) {
            logger.error("illegal param -> {}=={}", paramName, param);
            return true;
        }
        return false;
    }

    public static boolean illegalStrategyName(String strategyName, Logger logger) {
        return illegalStringParam("strategyName", strategyName, logger);
    }

    public static boolean illegalInvestorId(String investorId, Logger logger) {
        return illegalStringParam("investorId", investorId, logger);
    }

    public static boolean illegalInstrumentCode(String instrumentCode, Logger logger) {
        return illegalStringParam("instrumentCode", instrumentCode, logger);
    }

    public static boolean illegalBrokerId(String brokerId, Logger logger) {
        return illegalStringParam("brokerId", brokerId, logger);
    }
    
}
