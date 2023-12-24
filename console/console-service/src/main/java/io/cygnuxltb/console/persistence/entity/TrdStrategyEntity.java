package io.cygnuxltb.console.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import static io.cygnuxltb.console.persistence.ColumnConst.STRATEGY_ID;
import static io.cygnuxltb.console.persistence.ColumnConst.STRATEGY_NAME;
import static io.mercury.persistence.rdb.ColumnDefinition.UID;
import static jakarta.persistence.GenerationType.IDENTITY;

/**
 * 策略表
 * Strategy Entity
 *
 * @author yellow013
 */
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "TBL_TRD_STRATEGY")
public final class TblTrdStrategy {

    @Id
    @Column(name = UID)
    @GeneratedValue(strategy = IDENTITY)
    private long uid;

    @Column(name = STRATEGY_ID)
    private int strategyId;

    @Column(name = STRATEGY_NAME)
    private String strategyName;

    @Column(name = "STRATEGY_TYPE")
    private String strategyType;

    @Column(name = "STRATEGY_INFO")
    private String strategyInfo;

}
