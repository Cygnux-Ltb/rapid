package io.cygnuxltb.console.service;

import io.cygnuxltb.console.persistence.dao.UserDao;
import io.cygnuxltb.console.persistence.entity.TblSUser;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@Service
public final class UserService {

    private static final Logger log = Log4j2LoggerFactory.getLogger(UserService.class);

    @Resource
    private UserDao dao;

    public boolean signIn(String sign, String password) {
        TblSUser user = getUser(sign);
        if (user == null)
            return false;
        else
            return user.getPassword().equals(password);
    }


    public TblSUser getUser(String sign) {
        try {
            return dao.queryBy(sign, sign, sign);
        } catch (Exception e) {
            log.error("Get User has exception -> {}", e.getMessage(), e);
            return null;
        }

    }
}
