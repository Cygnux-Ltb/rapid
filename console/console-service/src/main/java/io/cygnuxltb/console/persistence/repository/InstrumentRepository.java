package io.cygnuxltb.console.persistence.dao;

import io.cygnuxltb.console.persistence.dao.base.BaseJpaRepository;
import io.cygnuxltb.console.persistence.entity.InstrumentEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Instrument DAO
 *
 * @author yellow013
 */
@Repository
public interface InstrumentRepository extends BaseJpaRepository<InstrumentEntity> {

    /**
     * @param instrumentCode String
     * @return List<InstrumentEntity>
     */
    @Query("SELECT '*' FROM #{#entityName} e WHERE"
            + " e.instrumentCode LIKE :instrumentCode% ")
    List<InstrumentEntity> queryBy(@Nullable String instrumentCode);

}
