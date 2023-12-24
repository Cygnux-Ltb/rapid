package io.cygnuxltb.console.persistence.dao;

import io.cygnuxltb.console.persistence.entity.TrdAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Account Repository
 *
 * @author yellow013
 */
@Repository
public interface AccountDao extends JpaRepository<TrdAccountEntity, Long> {

    /**
     * @param brokerId String
     * @return List<AccountEntity>
     */
    List<TrdAccountEntity> queryByBrokerId(String brokerId);

    /**
     * @param investorId String
     * @return List<AccountEntity>
     */
    List<TrdAccountEntity> queryByInvestorId(String investorId);

    /**
     * @param accountId int
     * @return List<AccountEntity>
     */
    List<TrdAccountEntity> queryByAccountId(int accountId);

    /**
     * @param subAccountId int
     * @return List<AccountEntity>
     */
    List<TrdAccountEntity> queryBySubAccountId(int subAccountId);

}
