package io.cygnuxltb.console.persistence.dao;

import io.cygnuxltb.console.persistence.CommonConst.ParamGroup;
import io.cygnuxltb.console.persistence.entity.TblParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Param Repository
 *
 * @author yellow013
 */
@Repository
public interface ParamDao extends JpaRepository<TblParam, Long> {

    /**
     * @param ownerGroup String
     * @param ownerName  String
     * @return List<ParamEntity>
     */
    @Query("SELECT e FROM #{#entityName} e WHERE 1 = 1"
            + " AND e.ownerGroup = :ownerGroup "
            + " AND e.ownerName = :ownerName "
    )
    List<TblParam> queryBy(@Param("ownerGroup") String ownerGroup,
                           @Param("ownerName") String ownerName);

    default List<TblParam> queryStrategyParam(String name) {
        return queryBy(ParamGroup.STRATEGY, name);
    }

    default List<TblParam> queryMarketParam(String name) {
        return queryBy(ParamGroup.MARKET, name);
    }

    default List<TblParam> queryTraderParam(String name) {
        return queryBy(ParamGroup.TRADER, name);
    }

    default List<TblParam> querySystemParam(String name) {
        return queryBy(ParamGroup.SYSTEM, name);
    }

}
