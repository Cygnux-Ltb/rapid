package io.cygnuxltb.console.service;

import io.cygnuxltb.console.persistence.dao.AccountDao;
import io.cygnuxltb.console.persistence.entity.TrdAccountEntity;
import io.cygnuxltb.console.service.util.DtoUtil;
import io.cygnuxltb.protocol.http.response.dto.AccountDTO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

import static io.cygnuxltb.console.persistence.JpaExecutor.select;
import static java.util.stream.Collectors.toList;

@Service
public final class AccountService {

    @Resource
    private AccountDao dao;

    /**
     * @param accountId int
     * @return List<AccountDTO>
     */
    public List<AccountDTO> getAccount(int accountId) {
        return select(TrdAccountEntity.class,
                () -> dao.queryByAccountId(accountId))
                .stream()
                .map(DtoUtil::toDto)
                .collect(toList());
    }

}