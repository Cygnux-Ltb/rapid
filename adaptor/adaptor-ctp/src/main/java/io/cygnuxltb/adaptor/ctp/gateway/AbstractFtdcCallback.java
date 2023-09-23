package io.cygnuxltb.adaptor.ctp.gateway;

import ctp.thostapi.CThostFtdcRspInfoField;
import io.cygnuxltb.adaptor.ctp.gateway.msg.FtdcEventPublisher;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import org.slf4j.Logger;

public abstract class AbstractFtdcCallback {

    protected final Logger log = Log4j2LoggerFactory.getLogger(this.getClass());

    protected final String callbackName = this.getClass().getSimpleName();

    // FtdcEvent 事件发布器
    protected final FtdcEventPublisher publisher;

    protected AbstractFtdcCallback(FtdcEventPublisher publisher) {
        this.publisher = publisher;
    }

    /**
     * 错误推送回调
     *
     * @param field     CThostFtdcRspInfoField
     * @param RequestID int
     * @param isLast    boolean
     */
    void onRspError(CThostFtdcRspInfoField field, int RequestID, boolean isLast) {
        log.error("{}::onRspError -> ErrorID==[{}], ErrorMsg==[{}]",
                callbackName, field.getErrorID(), field.getErrorMsg());
        publisher.publish(field, RequestID, isLast);
    }

}
