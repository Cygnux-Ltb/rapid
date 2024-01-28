package io.cygnuxltb.console.persistence.dao;

import io.cygnuxltb.console.persistence.entity.SysParamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import static io.cygnuxltb.console.persistence.ParamGroup.ADAPTOR;
import static io.cygnuxltb.console.persistence.ParamGroup.STRATEGY;
import static io.cygnuxltb.console.persistence.ParamGroup.SYSTEM;

/**
 * Param Repository
 *
 * @author yellow013
 */
@Repository
public interface ParamDao extends JpaRepository<SysParamEntity, Long> {


    /**
     * @param ownerGroup String
     * @param ownerName  String
     * @param paramGroup String
     * @return List<SysParamEntity>
     */
    @Query("SELECT e FROM #{#entityName} e WHERE 1 = 1"
            + " AND (e.ownerGroup = :ownerGroup) "
            + " AND (e.ownerName = :ownerName) "
            + " AND (:paramGroup = NULL OR :paramGroup = '' OR e.paramGroup = :paramGroup) ")
    List<SysParamEntity> queryBy(@Param("ownerGroup") String ownerGroup,
                                 @Param("ownerName") String ownerName,
                                 @Param("paramGroup") String paramGroup);

    /**
     * @param ownerName  String
     * @param paramGroup String
     * @return List<SysParamEntity>
     */
    default List<SysParamEntity> queryStrategyParam(String ownerName, String paramGroup) {
        return queryBy(STRATEGY, ownerName, paramGroup);
    }

    /**
     * @param ownerName  String
     * @param paramGroup String
     * @return List<SysParamEntity>
     */
    default List<SysParamEntity> queryAdaptorParam(String ownerName, String paramGroup) {
        return queryBy(ADAPTOR, ownerName, paramGroup);
    }

    /**
     * @param ownerName  String
     * @param paramGroup String
     * @return List<SysParamEntity>
     */
    default List<SysParamEntity> querySystemParam(String ownerName, String paramGroup) {
        return queryBy(SYSTEM, ownerName, paramGroup);
    }

}
