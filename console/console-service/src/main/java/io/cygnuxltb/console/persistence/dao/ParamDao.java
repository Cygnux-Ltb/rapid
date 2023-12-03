package io.cygnuxltb.console.persistence.dao;

import io.cygnuxltb.console.persistence.entity.TblSysParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import static io.cygnuxltb.console.persistence.ParamGroup.MARKET;
import static io.cygnuxltb.console.persistence.ParamGroup.STRATEGY;
import static io.cygnuxltb.console.persistence.ParamGroup.SYSTEM;
import static io.cygnuxltb.console.persistence.ParamGroup.TRADER;

/**
 * Param Repository
 *
 * @author yellow013
 */
@Repository
public interface ParamDao extends JpaRepository<TblSysParam, Long> {

    /**
     * @param ownerGroup String
     * @param ownerName  String
     * @return List<ParamEntity>
     */
    @Query("SELECT e FROM #{#entityName} e WHERE 1 = 1"
            + " AND e.ownerGroup = :ownerGroup "
            + " AND e.ownerName = :ownerName "
    )
    List<TblSysParam> queryBy(@Param("ownerGroup") String ownerGroup,
                              @Param("ownerName") String ownerName);

    default List<TblSysParam> queryStrategyParam(String name) {
        return queryBy(STRATEGY, name);
    }

    default List<TblSysParam> queryMarketParam(String name) {
        return queryBy(MARKET, name);
    }

    default List<TblSysParam> queryTraderParam(String name) {
        return queryBy(TRADER, name);
    }

    default List<TblSysParam> querySystemParam(String name) {
        return queryBy(SYSTEM, name);
    }

}
