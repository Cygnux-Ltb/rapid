package io.cygnuxltb.jcts.core.adaptor;

import io.cygnuxltb.jcts.core.handler.OrderHandler;
import io.mercury.common.lang.exception.ComponentStartupException;

import javax.annotation.Nonnull;
import java.io.IOException;

public interface TraderAdaptor {

    /**
     * @return Adaptor ID
     */
    @Nonnull
    String getAdaptorId();

    /**
     * Adaptor 启动函数
     *
     * @return boolean
     */
    boolean startup() throws IOException, IllegalStateException, ComponentStartupException;

    TraderAdaptor setOrderHandler(OrderHandler handler);

}
