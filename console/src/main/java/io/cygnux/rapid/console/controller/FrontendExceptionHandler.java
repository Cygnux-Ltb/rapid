package io.cygnux.rapid.console.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static io.cygnux.rapid.console.controller.ResponseStatus.BAD_REQUEST;
import static io.cygnux.rapid.console.controller.ResponseStatus.INTERNAL_ERROR;
import static io.cygnux.rapid.console.controller.ResponseStatus.NOT_FOUND;
import static io.cygnux.rapid.console.controller.ResponseStatus.UNAUTHORIZED;

@RestControllerAdvice(basePackages = {"io.cygnuxltb.console.controller.frontend"})
public class FrontendExceptionHandler {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseBean handleException(Exception e) {
        if (e instanceof NullPointerException)
            return NOT_FOUND.response().setMessage(e.getMessage());
        if (e instanceof IllegalArgumentException)
            return BAD_REQUEST.response().setMessage(e.getMessage());
        if (e instanceof IllegalStateException)
            return UNAUTHORIZED.response().setMessage(e.getMessage());
        return INTERNAL_ERROR.response().setMessage(e.getMessage());
    }

}