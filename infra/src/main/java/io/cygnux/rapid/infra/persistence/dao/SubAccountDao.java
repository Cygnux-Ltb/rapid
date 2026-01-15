package io.cygnux.rapid.infra.persistence.dao;

import io.cygnux.rapid.infra.persistence.entity.SubAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Param Repository
 *
 * @author yellow013
 */
@Repository
public interface SubAccountDao extends JpaRepository<SubAccountEntity, Long> {

    /**
     * @param subAccountId int
     * @return List<SSubAccountParamEntity>
     */
    @Query("""
            SELECT e FROM #{#entityName} e WHERE 1 = 1
                AND (e.subAccountId = :subAccountId)
            """)
    SubAccountEntity queryBy(@Param("subAccountId") int subAccountId);

}
