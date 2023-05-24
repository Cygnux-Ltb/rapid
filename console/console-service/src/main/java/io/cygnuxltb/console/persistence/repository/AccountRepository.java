package io.cygnuxltb.console.persistence.dao;

import io.cygnuxltb.console.persistence.dao.base.BaseJpaRepository;
import io.cygnuxltb.console.persistence.entity.AccountEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Account DAO
 *
 * @author yellow013
 */
@Repository
public interface AccountRepository extends BaseJpaRepository<AccountEntity> {

    /**
     * @param brokerId String
     * @return List<AccountEntity>
     */
    List<AccountEntity> queryByBrokerId(String brokerId);

    /**
     * @param investorId String
     * @return List<AccountEntity>
     */
    List<AccountEntity> queryByInvestorId(String investorId);

    /**
     * @param accountId int
     * @return List<AccountEntity>
     */
    List<AccountEntity> queryByAccountId(int accountId);

    /**
     * @param subAccountId int
     * @return List<AccountEntity>
     */
    List<AccountEntity> queryBySubAccountId(int subAccountId);

}
