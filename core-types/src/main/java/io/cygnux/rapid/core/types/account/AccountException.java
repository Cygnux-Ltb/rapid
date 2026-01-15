package io.cygnux.rapid.core.types.account;

import java.io.Serial;

public class AccountException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -6421678546942382394L;

    public AccountException(String message) {
        super(message);
    }

}
