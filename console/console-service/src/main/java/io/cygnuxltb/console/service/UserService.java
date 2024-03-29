package io.cygnuxltb.console.service;

import io.cygnuxltb.console.persistence.dao.UserDao;
import io.cygnuxltb.console.persistence.entity.SysUserEntity;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import static io.mercury.common.log4j2.Log4j2LoggerFactory.getLogger;

@Service
public final class UserService {

    private static final Logger log = getLogger(UserService.class);

    @Resource
    private UserDao dao;

    public int signin(String sign, String password) {
        SysUserEntity user = getUser(sign);
        if (user == null)
            return 0;
        else
            return user.getPassword().equals(password) ? 1 : -1;
    }

    public SysUserEntity getUser(String sign) {
        try {
            return dao.queryBy(sign, sign, sign);
        } catch (Exception e) {
            log.error("Get User has exception -> {}", e.getMessage(), e);
            return null;
        }
    }
    
}
