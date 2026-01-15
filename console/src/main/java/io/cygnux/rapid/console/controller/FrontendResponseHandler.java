package io.cygnux.rapid.console.controller;

import org.slf4j.Logger;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;

import static io.cygnux.rapid.console.controller.ResponseStatus.NOT_FOUND;
import static io.cygnux.rapid.console.controller.ResponseStatus.OK;
import static io.cygnux.rapid.infra.dto.PackageUtil.isInConsoleBeansPackage;
import static io.mercury.common.log4j2.Log4j2LoggerFactory.getLogger;

@RestControllerAdvice(basePackages = {"io.cygnuxltb.console.controller.frontend"})
public class FrontendResponseHandler implements ResponseBodyAdvice<Object> {

    private static final Logger log = getLogger(FrontendResponseHandler.class);

    @Override
    public boolean supports(@Nonnull MethodParameter parameter,
                            @Nonnull Class<? extends HttpMessageConverter<?>> type) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(@Nullable Object body,
                                  @Nonnull MethodParameter parameter,
                                  @Nonnull MediaType selectedContentType,
                                  @Nonnull Class<? extends HttpMessageConverter<?>> type,
                                  @Nonnull ServerHttpRequest request,
                                  @Nonnull ServerHttpResponse response) {
        switch (body) {
            case null -> {
                return NOT_FOUND.response();
            }
            case ResponseBean resp -> {
                return resp;
            }
            case ResponseStatus status -> {
                return status.response();
            }
            default -> {
                if (body instanceof Collection || isInConsoleBeansPackage(body)) {
                    return OK.responseOf(body);
                } else {
                    log.warn("Response Type -> {} from method [{}]", body.getClass().getSimpleName(), parameter.getMethod());
                    return body;
                }
            }
        }
    }


}
