package io.cygnux.rapid.core.types.account;

import java.io.Serial;

public class SubAccountException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -8903289183998546839L;

    public SubAccountException(String message) {
        super(message);
    }

}