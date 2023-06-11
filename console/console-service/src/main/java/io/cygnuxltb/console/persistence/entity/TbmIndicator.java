package io.cygnuxltb.console.persistence.entity.market;

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
@Table(name = "tbm_indicator")
public final class TbmIndicator {

    @Id
    @Column(name = ColumnDefinition.UID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long uid;

    @Column(name = "indicator_id")
    private int indicatorId;

    @Column(name = "indicator_name")
    private String indicatorName;

    @Column(name = "indicator_type")
    private String indicatorType;

    @Column(name = "remark")
    private String remark;

}

