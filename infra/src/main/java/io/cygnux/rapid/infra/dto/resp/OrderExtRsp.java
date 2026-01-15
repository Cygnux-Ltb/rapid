package io.cygnux.rapid.infra.dto.resp;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 订单扩展信息表
 * <p>
 * OrderExt Entity
 *
 * @author yellow013
 */
@Getter
@Setter
@Accessors(chain = true)
public final class OrderExtRsp {

    /**
     * OrderSysId [*]
     */
    private long ordSysId;

    /**
     * OrderRef
     */
    private String ordRef;

    /**
     * SignalSource
     */
    private String signalSource;

    /**
     * DelayCount
     */
    private long delayCount;

    /**
     * RouteId
     */
    private int routeId;

    /**
     * FrontId
     */
    private int frontId;

    /**
     * SessionId
     */
    private int sessionId;

    /**
     * BrokerSysID
     */
    private long brokerSysID;

    /**
     * AdaptorCode
     */
    private String adaptorCode;

    /**
     * ExtRemark
     */
    private String extRemark;

    /**
     * Reason
     */
    private String reason;


}
