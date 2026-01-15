package io.cygnux.rapid.infra.persistence.dao;

import io.cygnux.rapid.infra.persistence.entity.AdapterParamEntity;
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
public interface AdapterParamDao extends JpaRepository<AdapterParamEntity, Long> {

    /**
     * @param adapterId  int
     * @param paramGroup String
     * @return List<AdapterParamEntity>
     */
    @Query("""
            SELECT e FROM #{#entityName} e WHERE 1 = 1
                AND (e.adapterId = :adapterId)
                AND (e.paramGroup = :paramGroup OR e.paramGroup = 'PUBLIC')
                ORDER BY e.adapterId ASC , e.paramGroup ASC
            """)
    List<AdapterParamEntity> queryBy(@Param("adapterId") int adapterId,
                                     @Param("paramGroup") String paramGroup);

    @Query("""
            SELECT e FROM #{#entityName} e WHERE 1 = 1
                AND (e.adapterId = :adapterId)
                AND (e.paramGroup = :paramGroup)
                AND (e.paramName = :paramName)
            """)
    AdapterParamEntity getBy(@Param("adapterId") int adapterId,
                             @Param("paramGroup") String paramGroup,
                             @Param("paramName") String paramName);


}
