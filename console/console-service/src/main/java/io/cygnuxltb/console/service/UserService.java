package io.cygnuxltb.console.service;

import io.cygnuxltb.console.persistence.dao.UserDao;
import io.cygnuxltb.console.persistence.entity.TbsUser;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public final class UserService {

    @Resource
    private UserDao dao;

    public boolean signIn(String sign, String password) {
        TbsUser user = dao.queryBy(sign, sign, sign, password);
        return user != null;
    }


}
