package io.cygnuxltb.console.controller.base;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static io.cygnuxltb.console.controller.base.ResponseStatus.BAD_REQUEST;
import static io.cygnuxltb.console.controller.base.ResponseStatus.INTERNAL_ERROR;
import static io.cygnuxltb.console.controller.base.ResponseStatus.NOT_FOUND;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseBean handleException(Exception e) {
        if (e instanceof NullPointerException)
            return NOT_FOUND.response()
                    .setMessage(e.getMessage());
        if (e instanceof IllegalArgumentException)
            return BAD_REQUEST.response()
                    .setMessage(e.getMessage());
        return INTERNAL_ERROR.response();
    }

}