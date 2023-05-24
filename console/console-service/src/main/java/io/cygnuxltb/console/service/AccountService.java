package io.cygnuxltb.console.service;

import io.cygnuxltb.console.persistence.repository.AccountRepository;
import io.cygnuxltb.console.persistence.entity.AccountEntity;
import io.cygnuxltb.console.service.util.DtoUtil;
import io.cygnuxltb.protocol.http.outbound.AccountDTO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static io.cygnuxltb.console.persistence.util.DaoExecutor.select;

@Service
public class AccountService {

    @Resource
    private AccountRepository repo;

    public List<AccountDTO> getAccount(int accountId) {
        return select(AccountEntity.class,
                () -> repo.queryByAccountId(accountId))
                .stream()
                .map(DtoUtil::convertToDTO)
                .collect(Collectors.toList());
    }

}