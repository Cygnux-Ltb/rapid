package io.cygnux.rapid.infra.service;


import io.cygnux.rapid.infra.dto.resp.AccountRsp;
import io.cygnux.rapid.infra.dto.resp.SubAccountMappingRsp;
import io.cygnux.rapid.infra.dto.resp.SubAccountRsp;
import io.cygnux.rapid.infra.persistence.dao.AccountDao;
import io.cygnux.rapid.infra.persistence.dao.AccountParamDao;
import io.cygnux.rapid.infra.persistence.dao.SubAccountDao;
import io.cygnux.rapid.infra.persistence.dao.SubAccountMappingDao;
import io.cygnux.rapid.infra.persistence.dao.SubAccountParamDao;
import io.cygnux.rapid.infra.persistence.entity.AccountEntity;
import io.cygnux.rapid.infra.persistence.entity.SubAccountEntity;
import io.cygnux.rapid.infra.service.util.RespConverter;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

import static io.cygnux.rapid.infra.service.util.RespConverter.fromEntity;

@Service
public class AccountService {

    private static final Logger log = Log4j2LoggerFactory.getLogger(AccountService.class);

    @Resource
    private AccountDao accountDao;

    @Resource
    private AccountParamDao accountParamDao;

    @Resource
    private SubAccountDao subAccountDao;

    @Resource
    private SubAccountParamDao subAccountParamDao;

    @Resource
    private SubAccountMappingDao subAccountMappingDao;

    /**
     * @param accountId int
     * @return AccountRsp
     */
    public AccountRsp getAccount(int accountId) {
        return toAccountRsp(accountDao.queryByAccountId(accountId));
    }

    /**
     * @return List<AccountRsp>
     */
    public List<AccountRsp> getAllAccount() {
        return accountDao.findAll()
                .stream()
                .map(this::toAccountRsp)
                .toList();
    }

    private AccountRsp toAccountRsp(AccountEntity entity) {
        return fromEntity(entity)
                .setParamList(accountParamDao
                        .queryByAccountId(entity.getAccountId())
                        .stream()
                        .map(RespConverter::fromEntity)
                        .toList());
    }

    /**
     * @param subAccountId int
     * @return SubAccountRsp
     */
    public SubAccountRsp getSubAccount(int subAccountId) {
        return toSubAccountRsp(subAccountDao.queryBy(subAccountId));
    }

    /**
     * @return List<SubAccountRsp>
     */
    public List<SubAccountRsp> getAllSubAccount() {
        return subAccountDao.findAll()
                .stream()
                .map(this::toSubAccountRsp).toList();
    }

    private SubAccountRsp toSubAccountRsp(SubAccountEntity entity) {
        return fromEntity(entity)
                .setParamList(subAccountParamDao
                        .queryBy(entity.getSubAccountId())
                        .stream()
                        .map(RespConverter::fromEntity)
                        .toList());
    }

    /**
     * @param subAccountId int
     * @return SubAccountMappingRsp
     */
    public SubAccountMappingRsp getSubAccountMapping(int subAccountId) {
        return new SubAccountMappingRsp().setSubAccountId(subAccountId)
                .setSubAccountMappings(subAccountMappingDao.queryBy(subAccountId)
                        .stream()
                        .map(RespConverter::fromEntity)
                        .toList());
    }

}