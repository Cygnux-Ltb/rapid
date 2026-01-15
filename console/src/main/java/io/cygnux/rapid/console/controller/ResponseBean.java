package io.cygnux.rapid.console.controller;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class ResponseBean {

    private int code;

    private String message;

    private String info;

    private boolean isArray;

    private Object data;

}
