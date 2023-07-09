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
 * 配置信息表
 * StrategyParam Entity
 *
 * @author yellow013
 */
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "TBL_S_PARAM")
public final class TblParam {

    @Id
    @Column(name = ColumnDefinition.UID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long uid;

    @Column(name = "OWNER_GROUP")
    private String ownerGroup;

    @Column(name = "OWNER_NAME")
    private String ownerName;

    @Column(name = "PARAM_GROUP")
    private String paramGroup;

    @Column(name = "PARAM_NAME")
    private String paramName;

    @Column(name = "PARAM_TYPE")
    private String paramType;

    @Column(name = "PARAM_VALUE")
    private String paramValue;

}
