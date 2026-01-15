package io.cygnux.rapid.infra.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import static io.cygnux.rapid.core.types.field.ValueLimitation.MIN_ORD_SYS_ID;
import static io.cygnux.rapid.infra.persistence.NonnullColumn.ORD_SYS_ID;
import static io.mercury.common.constant.DbColumnConst.UID;
import static jakarta.persistence.GenerationType.IDENTITY;

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
@Entity
@Table(name = "t_order_ext")
public final class OrderExtEntity {

    @Id
    @Column(name = UID)
    @GeneratedValue(strategy = IDENTITY)
    private long uid;

    /**
     * OrderSysId [*]
     */
    @Min(MIN_ORD_SYS_ID)
    @Column(name = ORD_SYS_ID)
    private long ordSysId;

    /**
     * OrderRef
     */
    @NotBlank
    @Column(name = "ORD_REF")
    private String ordRef;

    /**
     * SignalSource
     */
    @Column(name = "SIGNAL_SOURCE")
    private String signalSource;

    /**
     * DelayCount
     */
    @Column(name = "DELAY_COUNT")
    private long delayCount;

    /**
     * RouteId
     */
    @Column(name = "ROUTE_ID")
    private int routeId;

    /**
     * FrontId
     */
    @Column(name = "FRONT_ID")
    private int frontId;

    /**
     * SessionId
     */
    @Column(name = "SESSION_ID")
    private int sessionId;

    /**
     * BrokerSysID
     */
    @Column(name = "BROKER_SYS_ID")
    private Long brokerSysID;

    /**
     * AdaptorCode
     */
    @Column(name = "ADAPTOR_CODE")
    private String adaptorCode;

    /**
     * ExtRemark
     */
    @Column(name = "EXT_REMARK")
    private String extRemark;

    /**
     * Reason
     */
    @Column(name = "REASON")
    private String reason;

}
