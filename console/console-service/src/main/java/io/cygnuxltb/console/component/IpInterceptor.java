package io.cygnuxltb.console.component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Nonnull;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class IpInterceptor implements HandlerInterceptor {

    private final InetAddress ip = InetAddress.getByName("127.0.0.1");

    public IpInterceptor() throws UnknownHostException {
    }

    /**
     * @param request  current HTTP request
     * @param response current HTTP response
     * @param handler  chosen handler to execute, for type and/or instance evaluation
     * @return boolean
     * @throws Exception e
     */
    @Override
    public boolean preHandle(@Nonnull HttpServletRequest request,
                             @Nonnull HttpServletResponse response,
                             @Nonnull Object handler) throws Exception {
        String clientIp = request.getRemoteAddr();
        // 允许访问的IP地址
        String allowedIp = "192.168.0.1";
        if (!allowedIp.equals(clientIp)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Forbidden");
            return false;
        }
        return true;
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

