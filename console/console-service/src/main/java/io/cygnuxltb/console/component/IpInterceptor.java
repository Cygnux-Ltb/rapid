package io.cygnuxltb.console.component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Nonnull;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class IpInterceptor implements HandlerInterceptor {

    private String allowedIp = "192.168.0.1"; // 允许访问的IP地址

    private final InetAddress ip = InetAddress.getByName("127.0.0.1");

    public IpInterceptor() throws UnknownHostException {
    }

    @Override
    public boolean preHandle(@Nonnull HttpServletRequest request,
                             @Nonnull HttpServletResponse response,
                             @Nonnull Object handler)
            throws Exception {
        String clientIp = request.getRemoteAddr();
        if (!allowedIp.equals(clientIp)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Forbidden");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(@Nonnull HttpServletRequest request,
                           @Nonnull HttpServletResponse response,
                           @Nonnull Object handler, ModelAndView modelAndView) {
        // 在处理请求后执行，可以在这里进行一些后续操作
    }

    @Override
    public void afterCompletion(@Nonnull HttpServletRequest request,
                                @Nonnull HttpServletResponse response,
                                @Nonnull Object handler, Exception ex) {
        // 在请求完成后执行，可以进行一些资源清理操作
    }
}

