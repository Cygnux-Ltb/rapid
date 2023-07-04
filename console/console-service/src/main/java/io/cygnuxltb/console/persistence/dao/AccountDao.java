package io.cygnuxltb.console.persistence.dao;

import io.cygnuxltb.console.persistence.entity.TblAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Account Repository
 *
 * @author yellow013
 */
@Repository
public interface AccountDao extends JpaRepository<TblAccount, Long> {

    /**
     * @param brokerId String
     * @return List<AccountEntity>
     */
    List<TblAccount> queryByBrokerId(String brokerId);

    /**
     * @param investorId String
     * @return List<AccountEntity>
     */
    List<TblAccount> queryByInvestorId(String investorId);

    /**
     * @param accountId int
     * @return List<AccountEntity>
     */
    List<TblAccount> queryByAccountId(int accountId);

    /**
     * @param subAccountId int
     * @return List<AccountEntity>
     */
    List<TblAccount> queryBySubAccountId(int subAccountId);

}
