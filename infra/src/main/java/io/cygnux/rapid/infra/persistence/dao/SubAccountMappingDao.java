package io.cygnux.rapid.infra.persistence.dao;

import io.cygnux.rapid.infra.persistence.entity.SubAccountMappingEntity;
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
public interface SubAccountMappingDao extends JpaRepository<SubAccountMappingEntity, Long> {

    /**
     * @param subAccountId int
     * @return List<SSubAccountParamEntity>
     */
    @Query("""
            SELECT e FROM #{#entityName} e WHERE 1 = 1
                AND (e.subAccountId = :subAccountId)
            """)
    List<SubAccountMappingEntity> queryBy(@Param("subAccountId") int subAccountId);

}
