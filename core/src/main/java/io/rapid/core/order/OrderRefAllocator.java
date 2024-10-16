package io.rapid.core.order;

import java.io.Serial;

public interface OrderRefAllocator {

    void binding(String orderRef, long ordSysId);

    long getOrdSysId(String orderRef);

    String getOrderRef(long ordSysId) throws OrderRefNotFoundException;

    int nextOrderRef();

    final class OrderRefNotFoundException extends Exception {

        @Serial
        private static final long serialVersionUID = -74254388017422611L;

        public OrderRefNotFoundException(long ordSysId) {
            super("ordSysId -> [" + ordSysId + "] is not find orderRef");
        }

    }

}
