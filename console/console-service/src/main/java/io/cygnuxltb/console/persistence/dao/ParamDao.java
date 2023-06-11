package io.cygnuxltb.console.persistence.dao;

import io.cygnuxltb.console.persistence.CommonConst.ParamGroup;
import io.cygnuxltb.console.persistence.entity.TbsParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Param Repository
 *
 * @author yellow013
 */
@Repository
public interface ParamDao extends JpaRepository<TbsParam, Long> {

    /**
     * @param ownerGroup String
     * @param ownerName  String
     * @return List<ParamEntity>
     */
    @Query("SELECT e FROM #{#entityName} e WHERE 1 = 1"
            + " AND e.ownerGroup = :ownerGroup "
            + " AND e.ownerName = :ownerName "
    )
    List<TbsParam> queryBy(String ownerGroup, String ownerName);

    default List<TbsParam> queryStrategyParam(String name) {
        return queryBy(ParamGroup.STRATEGY, name);
    }

    default List<TbsParam> queryMarketParam(String name) {
        return queryBy(ParamGroup.MARKET, name);
    }

    default List<TbsParam> queryTraderParam(String name) {
        return queryBy(ParamGroup.TRADER, name);
    }

    default List<TbsParam> querySystemParam(String name) {
        return queryBy(ParamGroup.SYSTEM, name);
    }

}
