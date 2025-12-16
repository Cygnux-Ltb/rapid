package io.cygnux.rapid.ctp.adaptor.component;

import io.cygnux.console.persistence.dao.AccountDao;
import io.cygnux.console.persistence.dao.AccountParamDao;
import io.cygnux.console.persistence.dao.AdaptorDao;
import io.cygnux.console.persistence.dao.AdaptorParamDao;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.mercury.serialization.json.JsonWriter;
import io.cygnux.gateway.ctp.param.FtdcParams;
import io.cygnux.gateway.ctp.param.ImmutableFtdcParams;
import io.cygnux.rapid.core.account.Account;
import jakarta.annotation.Resource;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Getter
@Setter
@Accessors(chain = true)
@Configuration
public class CtpConfiguration {

    private static final Logger log = Log4j2LoggerFactory.getLogger(CtpConfiguration.class);

    @Value("${trading.account.id:-1}")
    private int accountId;

    @Resource
    private AccountDao accountDao;

    @Resource
    private AccountParamDao accountParamDao;

    @Resource
    private AdaptorDao adaptorDao;

    @Resource
    private AdaptorParamDao adaptorParamDao;

    @Bean("account")
    public Account getAccount() {
        if (accountId == -1)
            throw new IllegalArgumentException("[accountId] not set");
        var entity = accountDao.queryByAccountId(accountId);
        log.info("INIT [Account] with AccountEntity -> {}", JsonWriter.toJson(entity));
        return new Account(entity.getAccountId(), entity.getBrokerCode(), entity.getInvestorCode(),
                (long) entity.getBalance(), (long) entity.getCredit());
    }

    /**
     * @return CtpParams
     */
    @Bean("params")
    public FtdcParams getParams() {
        var entity = accountDao.queryByAccountId(accountId);
        var map = new HashMap<String, String>();
        accountParamDao.queryByAccountId(entity.getAccountId())
                .forEach(e -> map.put(e.getParamName(), e.getParamValue()));
        adaptorParamDao.queryBy(entity.getAdaptorId(), entity.getAdaptorParamGroup())
                .forEach(e -> map.put(e.getParamName(), e.getParamValue()));
        return new ImmutableFtdcParams(map).assertCtpParams();
    }

}
