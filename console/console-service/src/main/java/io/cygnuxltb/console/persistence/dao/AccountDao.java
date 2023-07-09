package io.cygnuxltb.console.persistence.dao;

import io.cygnuxltb.console.persistence.entity.TblTAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Account Repository
 *
 * @author yellow013
 */
@Repository
public interface AccountDao extends JpaRepository<TblTAccount, Long> {

    /**
     * @param brokerId String
     * @return List<AccountEntity>
     */
    List<TblTAccount> queryByBrokerId(String brokerId);

    /**
     * @param investorId String
     * @return List<AccountEntity>
     */
    List<TblTAccount> queryByInvestorId(String investorId);

    /**
     * @param accountId int
     * @return List<AccountEntity>
     */
    List<TblTAccount> queryByAccountId(int accountId);

    /**
     * @param subAccountId int
     * @return List<AccountEntity>
     */
    List<TblTAccount> queryBySubAccountId(int subAccountId);

}
