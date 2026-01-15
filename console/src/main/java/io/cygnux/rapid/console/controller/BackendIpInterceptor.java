package io.cygnux.rapid.console.controller;

import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.mercury.common.util.StringSupport;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Nonnull;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class BackendIpInterceptor implements HandlerInterceptor {

    // private final InetAddress ip = InetAddress.getByName("127.0.0.1");

    private static final Logger log = Log4j2LoggerFactory.getLogger(BackendIpInterceptor.class);

    /**
     * @param request  current HTTP request
     * @param response current HTTP response
     * @param handler  chosen handler to execute, for type and/or instance evaluation
     * @return boolean
     */
    @Override
    public boolean preHandle(@Nonnull HttpServletRequest request,
                             @Nonnull HttpServletResponse response,
                             @Nonnull Object handler) {
        String ipAddress;
        try {
            ipAddress = getIpAddress(request);
            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            if (ipAddress != null && ipAddress.length() > 15) {
                if (ipAddress.indexOf(",") > 0) {
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                }
            }
        } catch (Exception e) {
            log.error("Get IP address has Exception -> {}", e.getMessage(), e);
            ipAddress = "";
        }
        log.info("Request IP address -> {}", ipAddress);
        return true;
    }

    private String getIpAddress(HttpServletRequest request) {
        String ipAddress = "";
        request.getHeader("x-forwarded-for");
        if (StringSupport.isNullOrEmpty(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (ipAddress.equals("127.0.0.1")) {
                // 根据网卡取本机配置的IP
                InetAddress inet;
                try {
                    inet = InetAddress.getLocalHost();
                    ipAddress = inet.getHostAddress();
                } catch (UnknownHostException e) {
                    log.error("InetAddress.getLocalHost() has UnknownHostException -> {}", e.getMessage(), e);
                }
            }
        }
        return ipAddress;
    }

    /**
     * @param request      current HTTP request
     * @param response     current HTTP response
     * @param handler      the handler (or {@link HandlerMethod}) that started asynchronous
     *                     execution, for type and/or instance examination
     * @param modelAndView the {@code ModelAndView} that the handler returned
     *                     (can also be {@code null})
     */
    @Override
    public void postHandle(@Nonnull HttpServletRequest request,
                           @Nonnull HttpServletResponse response,
                           @Nonnull Object handler, ModelAndView modelAndView) {
        // 在处理请求后执行，可以在这里进行一些后续操作
    }

    /**
     * @param request  current HTTP request
     * @param response current HTTP response
     * @param handler  the handler (or {@link HandlerMethod}) that started asynchronous
     *                 execution, for type and/or instance examination
     * @param ex       any exception thrown on handler execution, if any; this does not
     *                 include exceptions that have been handled through an exception resolver
     */
    @Override
    public void afterCompletion(@Nonnull HttpServletRequest request,
                                @Nonnull HttpServletResponse response,
                                @Nonnull Object handler, Exception ex) {
        // 在请求完成后执行，可以进行一些资源清理操作
    }

}

