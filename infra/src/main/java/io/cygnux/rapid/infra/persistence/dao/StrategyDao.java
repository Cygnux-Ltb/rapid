package io.cygnux.rapid.infra.persistence.dao;

import io.cygnux.rapid.infra.persistence.entity.StrategyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Strategy Repository
 *
 * @author yellow013
 */
@Repository
public interface StrategyDao extends JpaRepository<StrategyEntity, Integer> {

    /**
     * @param strategyId int
     * @return TStrategyEntity
     */
    StrategyEntity queryByStrategyId(int strategyId);

    /**
     * @param strategyName String
     * @return TStrategyEntity
     */
    StrategyEntity queryByStrategyName(String strategyName);

    /**
     * @return List<TStrategyEntity>
     */
    List<StrategyEntity> getAllByOrderByStrategyIdAsc();

}
