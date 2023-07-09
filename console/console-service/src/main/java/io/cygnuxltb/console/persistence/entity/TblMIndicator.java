package io.cygnuxltb.console.persistence.entity;

import io.mercury.persistence.rdb.ColumnDefinition;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 交易账户表
 * Account Entity
 *
 * @author yellow013
 */
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "TBL_M_INDICATOR")
public final class TblMIndicator {

    @Id
    @Column(name = ColumnDefinition.UID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long uid;

    @Column(name = "INDICATOR_ID")
    private int indicatorId;

    @Column(name = "INDICATOR_NAME")
    private String indicatorName;

    @Column(name = "INDICATOR_TYPE")
    private String indicatorType;

    @Column(name = "INDICATOR_INFO")
    private String indicatorInfo;

    @Column(name = "REMARK")
    private String remark;

}

