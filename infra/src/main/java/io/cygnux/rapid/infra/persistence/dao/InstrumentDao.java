package io.cygnux.rapid.infra.persistence.dao;

import io.cygnux.rapid.infra.persistence.entity.InstrumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Instrument Repository
 *
 * @author yellow013
 */
@Repository
public interface InstrumentDao extends JpaRepository<InstrumentEntity, String> {

    /**
     * @param instrumentCode String
     * @return List<MInstrumentEntity>
     */
    @Query("""
            SELECT e FROM #{#entityName} e WHERE 1 = 1
                AND (e.instrumentCode LIKE :instrumentCode%)
            """)
    List<InstrumentEntity> queryBy(@Param("instrumentCode") String instrumentCode);

}
