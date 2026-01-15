package io.cygnux.rapid.console.controller.backend;

import io.cygnux.rapid.infra.dto.resp.AccountRsp;
import io.cygnux.rapid.infra.dto.resp.SubAccountMappingRsp;
import io.cygnux.rapid.infra.dto.resp.SubAccountRsp;
import io.cygnux.rapid.infra.service.AccountService;
import io.cygnux.rapid.core.types.field.AccountID;
import io.cygnux.rapid.core.types.field.SubAccountID;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static io.cygnux.rapid.console.controller.HttpParam.ACCOUNT_ID;
import static io.cygnux.rapid.console.controller.HttpParam.SUB_ACCOUNT_ID;

/**
 * [REST] - 3.账户服务
 */
@RestController("accountControllerV1b")
@RequestMapping(path = "/account/v1b", produces = "application/json;charset=utf-8")
public class AccountController {

    private static final Logger log = Log4j2LoggerFactory.getLogger(AccountController.class);

    @Resource
    private AccountService accountService;

    /**
     * 获取指定[交易账户]
     *
     * @param accountId 交易账户ID
     * @return AccountParamsRsp
     * @apiNote 获取1分钟BAR
     */
    @GetMapping
    public AccountRsp getAccount(@RequestParam(ACCOUNT_ID) int accountId) {
        log.info("GET AccountRsp with : accountId -> {}", accountId);
        AccountID.checkAccountId(accountId, log);
        return accountService.getAccount(accountId);
    }

    /**
     * 获取指定[系统子账户]
     *
     * @param subAccountId 交易账户ID
     * @return AccountParamsRsp
     * @apiNote 获取1分钟BAR
     */
    @GetMapping(path = "sub_account")
    public SubAccountRsp getSubAccount(@RequestParam(SUB_ACCOUNT_ID) int subAccountId) {
        log.info("GET SubAccountRsp with : subAccountId -> {}", subAccountId);
        SubAccountID.checkSubAccountId(subAccountId, log);
        return accountService.getSubAccount(subAccountId);
    }

    /**
     * @param subAccountId int
     * @return SubAccountRsp
     */
    @GetMapping(path = "mapping")
    public SubAccountMappingRsp getMapping(@RequestParam(SUB_ACCOUNT_ID) int subAccountId) {
        log.info("GET SubAccountMappingRsp with : subAccountId -> {}", subAccountId);
        SubAccountID.checkSubAccountId(subAccountId, log);
        return accountService.getSubAccountMapping(subAccountId);
    }

}
