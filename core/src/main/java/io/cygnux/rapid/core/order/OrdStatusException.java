package io.cygnux.rapid.core.order;

/**
 * OrdStatusException
 *
 * @author yellow013
 */
public class OrdStatusException extends RuntimeException {

    @java.io.Serial
    private static final long serialVersionUID = -4772495541311633988L;

    public OrdStatusException(String message) {
        super(message);
    }

}