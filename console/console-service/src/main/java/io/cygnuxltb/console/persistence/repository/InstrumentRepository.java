package io.cygnuxltb.console.persistence.repository;

import io.cygnuxltb.console.persistence.entity.market.InstrumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Instrument Repository
 *
 * @author yellow013
 */
@Repository
public interface InstrumentRepository extends JpaRepository<InstrumentEntity, Long> {

    /**
     * @param instrumentCode String
     * @return List<InstrumentEntity>
     */
    @Query("SELECT '*' FROM #{#entityName} e WHERE"
            + " e.instrumentCode LIKE :instrumentCode% ")
    List<InstrumentEntity> queryBy(@Nullable String instrumentCode);

}
