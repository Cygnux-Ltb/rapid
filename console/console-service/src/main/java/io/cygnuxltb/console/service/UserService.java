package io.cygnuxltb.console.service;

import io.cygnuxltb.console.persistence.entity.sys.UserEntity;
import io.cygnuxltb.console.persistence.repository.UserRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public final class UserService {

    @Resource
    private UserRepository repo;

    public boolean signIn(String sign, String password) {
        List<UserEntity> list = repo.queryBy(sign, sign, sign, password);
        return list != null && list.size() > 0;
    }


}
