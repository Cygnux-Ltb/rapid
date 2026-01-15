package io.cygnux.rapid.infra.persistence.dao;

import io.cygnux.rapid.infra.persistence.entity.StrategyParamEntity;
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
public interface StrategyParamDao extends JpaRepository<StrategyParamEntity, Long> {

    /**
     * @param userid     int
     * @param strategyId int
     * @return List<TStrategyParamEntity>
     */
    @Query("""
            SELECT e FROM #{#entityName} e WHERE 1 = 1
                AND (e.userid = :userid)
                AND (e.strategyId = :strategyId)
                ORDER BY e.userid ASC, e.strategyId ASC
            """)
    List<StrategyParamEntity> queryBy(@Param("userid") int userid,
                                      @Param("strategyId") int strategyId);

    /**
     * @param userid     int
     * @param strategyId int
     * @param algoId     int
     * @return TStrategyParamEntity
     */
    @Query("""
            SELECT e FROM #{#entityName} e WHERE 1 = 1
                AND (e.userid = :userid)
                AND (e.strategyId = :strategyId)
                AND (e.algoId = :algoId)
                AND (e.paramName = :paramName)
            """)
    StrategyParamEntity queryBy(@Param("userid") int userid,
                                @Param("strategyId") int strategyId,
                                @Param("algoId") int algoId,
                                @Param("paramName") String paramName);


}
