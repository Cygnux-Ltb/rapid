package io.rapid.core;

import lombok.Getter;

public final class ErrorMsg {

    @Getter
    private final int code;
    @Getter
    private final String language;
    @Getter
    private final String message;

    ErrorMsg(int code, String language, String message) {
        this.code = code;
        this.language = language;
        this.message = message;
    }

    @Override
    public String toString() {
        return code + ":" + language + ":" + message;
    }

}
