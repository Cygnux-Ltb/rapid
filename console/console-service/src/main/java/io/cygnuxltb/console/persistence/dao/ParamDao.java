package io.cygnuxltb.console.persistence.repository;

import io.cygnuxltb.console.persistence.CommonConst.ParamGroup;
import io.cygnuxltb.console.persistence.entity.TbsParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Param Repository
 *
 * @author yellow013
 */
@Repository
public interface ParamRepository extends JpaRepository<TbsParam, Long> {

    /**
     * @param ownerGroup String
     * @param ownerName  String
     * @return List<ParamEntity>
     */
    List<TbsParam> queryByGroupNameAndOwnerName(String ownerGroup, String ownerName);

    default List<TbsParam> queryStrategyParamByName(String name) {
        return queryByGroupNameAndOwnerName(ParamGroup.STRATEGY, name);
    }

    default List<TbsParam> queryMarketParamByName(String name) {
        return queryByGroupNameAndOwnerName(ParamGroup.MARKET, name);
    }

    default List<TbsParam> queryTraderParamByName(String name) {
        return queryByGroupNameAndOwnerName(ParamGroup.TRADER, name);
    }

    default List<TbsParam> querySystemParamByName(String name) {
        return queryByGroupNameAndOwnerName(ParamGroup.SYSTEM, name);
    }

}
