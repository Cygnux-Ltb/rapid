package io.cygnux.rapid.infra.persistence.dao;

import io.cygnux.rapid.infra.persistence.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Account Repository
 *
 * @author yellow013
 */
@Repository
public interface AccountDao extends JpaRepository<AccountEntity, Integer> {

    /**
     * @param accountId int
     * @return List<TAccountEntity>
     */
    AccountEntity queryByAccountId(int accountId);

    /**
     * @param brokerCode String
     * @return List<TAccountEntity>
     */
    List<AccountEntity> queryByBrokerCode(String brokerCode);

    /**
     * @param investorCode String
     * @return TAccountEntity
     */
    AccountEntity queryByInvestorCode(String investorCode);


}
