package io.rapid.core;

public record ErrorMsg(
        int code,
        String language,
        String message) {

    @Override
    public String toString() {
        return STR."\{code}:\{language}:\{message}";
    }

}
