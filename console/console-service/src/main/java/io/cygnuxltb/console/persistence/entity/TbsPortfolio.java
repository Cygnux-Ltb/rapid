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

import static io.cygnuxltb.console.persistence.CommonConst.Column.INSTRUMENT_CODE;
import static io.cygnuxltb.console.persistence.CommonConst.Column.USER_ID;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "tbs_portfolio")
public final class TbsPortfolio {

    @Id
    @Column(name = ColumnDefinition.UID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long uid;

    /**
     * userId [*]
     */
    @Column(name = USER_ID)
    private int userId;

    /**
     * groupName [*]
     */
    @Column(name = "group_name")
    private String groupName;

    /**
     * instrumentCode [*]
     */
    @Column(name = INSTRUMENT_CODE)
    private String instrumentCode;

}
