package io.cygnuxltb.console.persistence.repository;

import io.cygnuxltb.console.persistence.CommonConst.ParamGroup;
import io.cygnuxltb.console.persistence.entity.sys.ParamEntity;
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
     * @param ownerGroup String
     * @param ownerName  String
     * @return List<ParamEntity>
     */
    List<ParamEntity> queryByOwnerGroupAndOwnerName(String ownerGroup, String ownerName);

    default List<ParamEntity> queryStrategyParamByName(String name) {
        return queryByOwnerGroupAndOwnerName(ParamGroup.STRATEGY, name);
    }

    default List<ParamEntity> queryMarketParamByName(String name) {
        return queryByOwnerGroupAndOwnerName(ParamGroup.MARKET, name);
    }

    default List<ParamEntity> queryTraderParamByName(String name) {
        return queryByOwnerGroupAndOwnerName(ParamGroup.TRADER, name);
    }

    default List<ParamEntity> querySystemParamByName(String name) {
        return queryByOwnerGroupAndOwnerName(ParamGroup.SYSTEM, name);
    }

}
