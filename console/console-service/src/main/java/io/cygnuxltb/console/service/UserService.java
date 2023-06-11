package io.cygnuxltb.console.service;

import io.cygnuxltb.console.persistence.dao.UserDao;
import io.cygnuxltb.console.persistence.entity.TbsUser;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public final class UserService {

    @Resource
    private UserDao dao;

    public boolean signIn(String sign, String password) {
        List<TbsUser> list = dao.queryBy(sign, sign, sign, password);
        return list != null && list.size() > 0;
    }


}
