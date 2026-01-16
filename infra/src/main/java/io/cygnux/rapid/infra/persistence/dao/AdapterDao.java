package io.cygnux.rapid.infra.persistence.dao;

import io.cygnux.rapid.infra.persistence.entity.AdapterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Param Repository
 *
 * @author yellow013
 */
@Repository
public interface AdapterDao extends JpaRepository<AdapterEntity, Integer> {

    AdapterEntity queryByAdapterId(int adapterId);

    @Query("""
            SELECT e FROM #{#entityName} e WHERE 1 = 1
                ORDER BY e.adapterId ASC
            """)
    List<AdapterEntity> queryAllWithOrderByAdapterId();

}
