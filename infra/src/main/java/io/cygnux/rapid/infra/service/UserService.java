package io.cygnux.rapid.infra.service;

import io.cygnux.rapid.infra.component.ApplicationConfiguration;
import io.cygnux.rapid.infra.dto.common.GeneralOption;
import io.cygnux.rapid.infra.dto.resp.SigninStatus;
import io.cygnux.rapid.infra.persistence.JpaExecutor;
import io.cygnux.rapid.infra.persistence.dao.UserDao;
import io.cygnux.rapid.infra.persistence.entity.UserEntity;
import io.mercury.common.epoch.EpochUtil;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.eclipse.collections.api.map.ConcurrentMutableMap;
import org.eclipse.collections.impl.map.mutable.ConcurrentHashMap;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static io.mercury.common.log4j2.Log4j2LoggerFactory.getLogger;
import static io.mercury.common.util.StringSupport.isEquals;

@Service
public class UserService {

    private static final Logger log = getLogger(UserService.class);

    private static final int USER_SERVICE_ERROR = -1;

    private static final int SIGNIN_OK = 1;

    private static final int USER_DOES_NOT_EXIST = 10;

    private static final int WRONG_PASSWORD = 11;

    private static final int VERIFICATION_CODE_ERROR = 12;

    @Resource
    private UserDao userDao;

    @Resource
    private ApplicationConfiguration applicationConfiguration;

    private final List<GeneralOption> userOptions = new CopyOnWriteArrayList<>();

    private final ConcurrentMutableMap<Integer, String> usernameMap = new ConcurrentHashMap<>();

    @PostConstruct
    private void init() {
        if (applicationConfiguration.isInitializeData()) {
            log.info("INIT USER ADMIN, DELETE AND SAVE DATA");
            JpaExecutor.insertOrUpdate(userDao,
                    jpa -> {
                        UserEntity admin = jpa.queryBy("admin", null, null);
                        if (admin == null) {
                            admin = new UserEntity().setUserid(1000).setUsername("admin").setPassword("admin");
                        }
                        return admin;
                    }
            );
        }

        userDao.findAll().forEach(user -> {
            usernameMap.put(user.getUserid(), user.getUsername());
            userOptions.add(new GeneralOption(user.getUserid(), user.getUsername()));
        });

    }

    private boolean checkVerifyCode(String verifyCode) {
        return true;
    }

    public SigninStatus signin(String sign, String password, String verifyCode) {
        int statusCode;
        SigninStatus status = new SigninStatus().setAuthenticated(false);
        if (checkVerifyCode(verifyCode)) {
            try {
                UserEntity entity = userDao.queryBy(sign, sign, sign);
                if (entity == null)
                    statusCode = USER_DOES_NOT_EXIST;
                else {
                    statusCode = isEquals(entity.getPassword(), password)
                            ? SIGNIN_OK : WRONG_PASSWORD;
                    if (statusCode == SIGNIN_OK)
                        status.setUserid(entity.getUserid());
                }
            } catch (Exception e) {
                log.error("Get User has exception -> {}", e.getMessage(), e);
                statusCode = USER_SERVICE_ERROR;
            }
        } else {
            statusCode = VERIFICATION_CODE_ERROR;
        }
        return switch (statusCode) {
            case USER_SERVICE_ERROR -> status.setMessage("服务端错误");
            case USER_DOES_NOT_EXIST -> status.setMessage("用户不存在");
            case WRONG_PASSWORD -> status.setMessage("密码错误");
            case VERIFICATION_CODE_ERROR -> status.setMessage("验证码错误");
            default -> status.setMessage("验证成功")
                    .setAuthenticated(true)
                    .setSecurityCode(EpochUtil.getEpochMillis());
        };
    }

    public List<GeneralOption> getUserOptions() {
        return userOptions;
    }

    public String getUsername(int userid) {
        return usernameMap.get(userid);
    }

}
