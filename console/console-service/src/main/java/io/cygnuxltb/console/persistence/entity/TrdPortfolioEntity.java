package io.cygnuxltb.console.persistence.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import static io.cygnuxltb.console.persistence.ColumnConst.INSTRUMENT_CODE;
import static io.cygnuxltb.console.persistence.ColumnConst.USER_ID;
import static io.mercury.persistence.rdb.ColumnDefinition.UID;
import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "TRD_PORTFOLIO")
public final class TrdPortfolioEntity {

    @Id
    @Column(name = UID)
    @GeneratedValue(strategy = IDENTITY)
    private long uid;

    /**
     * userId [*]
     */
    @Column(name = USER_ID)
    private int userId;

    /**
     * groupName [*]
     */
    @Column(name = "PORTFOLIO_NAME")
    private String portfolioName;

    /**
     * instrumentCode [*]
     */
    @Column(name = INSTRUMENT_CODE)
    private String instrumentCode;

}
