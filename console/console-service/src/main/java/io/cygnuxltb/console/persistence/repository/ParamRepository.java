package io.cygnuxltb.console.persistence.dao;

import io.cygnuxltb.console.persistence.dao.base.BaseJpaRepository;
import io.cygnuxltb.console.persistence.entity.ParamEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Param DAO
 *
 * @author yellow013
 */
@Repository
public interface ParamRepository extends BaseJpaRepository<ParamEntity> {

    /**
     * @param strategyId int
     * @return List<ParamEntity>
     */
    List<ParamEntity> queryByStrategyId(int strategyId);

    /**
     * @param strategyName String
     * @return List<ParamEntity>
     */
    List<ParamEntity> queryByStrategyName(String strategyName);

}
