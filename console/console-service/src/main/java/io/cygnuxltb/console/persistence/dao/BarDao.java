package io.cygnuxltb.console.persistence.dao;

import io.cygnuxltb.console.persistence.entity.TblMkdBar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Bar Repository
 *
 * @author yellow013
 */
@Repository
public interface BarDao extends JpaRepository<TblMkdBar, Long> {

    @Query("SELECT e FROM #{#entityName} e WHERE 1 = 1"
            + " AND e.instrumentCode = :instrumentCode "
            + " AND e.tradingDay >= :startTradingDay "
            + " AND e.tradingDay <= :endTradingDay ")
    List<TblMkdBar> queryBy(@Param("instrumentCode") String instrumentCode,
                            @Param("startTradingDay") int startTradingDay,
                            @Param("endTradingDay") int endTradingDay);

}
