package io.cygnuxltb.console.persistence.repository;

import io.cygnuxltb.console.persistence.CommonConst;
import io.cygnuxltb.console.persistence.entity.ParamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Param Repository
 *
 * @author yellow013
 */
@Repository
public interface ParamRepository extends JpaRepository<ParamEntity, Long> {

    /**
     * @param group String
     * @param name  String
     * @return List<ParamEntity>
     */
    List<ParamEntity> queryByGroupAndName(String group, String name);

    default List<ParamEntity> queryStrategyParamByName(String name) {
        return queryByGroupAndName(CommonConst.ParamGroup.STRATEGY, name);
    }

    default List<ParamEntity> queryMarketParamByName(String name) {
        return queryByGroupAndName(CommonConst.ParamGroup.MARKET, name);
    }

    default List<ParamEntity> queryTraderParamByName(String name) {
        return queryByGroupAndName(CommonConst.ParamGroup.TRADER, name);
    }

    default List<ParamEntity> querySystemParamByName(String name) {
        return queryByGroupAndName(CommonConst.ParamGroup.SYSTEM, name);
    }

}
