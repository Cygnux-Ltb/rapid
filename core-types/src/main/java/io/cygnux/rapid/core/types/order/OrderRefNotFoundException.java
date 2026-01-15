package io.cygnux.rapid.core.types.order;

import java.io.Serial;

public class OrderRefNotFoundException extends Exception {

    @Serial
    private static final long serialVersionUID = -74254388017422611L;

    public OrderRefNotFoundException(long ordSysId) {
        super("ordSysId -> [" + ordSysId + "] is not find orderRef");
    }

}