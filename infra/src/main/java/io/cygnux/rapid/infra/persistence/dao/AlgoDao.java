package io.cygnux.rapid.infra.persistence.dao;

import io.cygnux.rapid.infra.persistence.entity.AlgoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlgoDao extends JpaRepository<AlgoEntity, Integer> {

    /**
     * @param algoId int
     * @return MAlgoEntity
     */
    AlgoEntity queryByAlgoId(int algoId);

}
