package io.cygnux.rapid.infra.persistence.dao;

import io.cygnux.rapid.infra.persistence.entity.AccountParamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Account Repository
 *
 * @author yellow013
 */
@Repository
public interface AccountParamDao extends JpaRepository<AccountParamEntity, Integer> {

    /**
     * @param accountId int
     * @return List<TAccountParamEntity>
     */
    List<AccountParamEntity> queryByAccountId(int accountId);

}
