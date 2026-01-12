package io.cygnux.rapid.adapter.ctp.param;

import com.typesafe.config.Config;
import io.cygnux.rapid.gateway.ctp.FtdcParams;
import io.mercury.common.config.ConfigWrapper;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

import static io.cygnux.rapid.core.instrument.futures.ChinaFutures.parseTradingDay;
import static io.cygnux.rapid.adapter.ctp.param.FtdcParamKey.ACCOUNT_ID;
import static io.cygnux.rapid.adapter.ctp.param.FtdcParamKey.APP_ID;
import static io.cygnux.rapid.adapter.ctp.param.FtdcParamKey.AUTH_CODE;
import static io.cygnux.rapid.adapter.ctp.param.FtdcParamKey.BROKER_ID;
import static io.cygnux.rapid.adapter.ctp.param.FtdcParamKey.CURRENCY_ID;
import static io.cygnux.rapid.adapter.ctp.param.FtdcParamKey.INVESTOR_ID;
import static io.cygnux.rapid.adapter.ctp.param.FtdcParamKey.IP_ADDR;
import static io.cygnux.rapid.adapter.ctp.param.FtdcParamKey.MAC_ADDR;
import static io.cygnux.rapid.adapter.ctp.param.FtdcParamKey.MD_ADDR;
import static io.cygnux.rapid.adapter.ctp.param.FtdcParamKey.PASSWORD;
import static io.cygnux.rapid.adapter.ctp.param.FtdcParamKey.TRADER_ADDR;
import static io.cygnux.rapid.adapter.ctp.param.FtdcParamKey.TRADING_DAY;
import static io.cygnux.rapid.adapter.ctp.param.FtdcParamKey.USERID;
import static io.mercury.common.datetime.pattern.impl.DatePattern.YYYYMMDD;
import static io.mercury.common.net.NetworkProperties.getLocalMacAddress;

@Getter
@Setter
@Accessors(chain = true)
public class MutableFtdcParams implements FtdcParams {

    private String traderAddr;

    private String mdAddr;

    private String appId;

    private String brokerId;

    private String investorId;

    private String accountId;

    private String userId;

    private String password;

    private String authCode;

    private String ipAddr;

    private String macAddr;

    private String tradingDay;

    private String currencyId;

    public MutableFtdcParams load(Config config) {
        var wrapper = new ConfigWrapper<FtdcParamKey>(config);
        return this
                // 交易服务器地址
                .setTraderAddr(wrapper.getStringOrThrows(TRADER_ADDR))
                // 行情服务器地址
                .setMdAddr(wrapper.getStringOrThrows(MD_ADDR))
                // 应用ID
                .setAppId(wrapper.getStringOrThrows(APP_ID))
                // 经纪商ID
                .setBrokerId(wrapper.getStringOrThrows(BROKER_ID))
                // 投资者ID
                .setInvestorId(wrapper.getStringOrThrows(INVESTOR_ID))
                // 账号ID
                .setAccountId(wrapper.getStringOrThrows(ACCOUNT_ID))
                // 用户ID
                .setUserId(wrapper.getStringOrThrows(USERID))
                // 密码
                .setPassword(wrapper.getStringOrThrows(PASSWORD))
                // 认证码
                .setAuthCode(wrapper.getStringOrThrows(AUTH_CODE))
                // 客户端IP地址
                .setIpAddr(wrapper.getString(IP_ADDR, "127.0.0.1"))
                // 客户端MAC地址
                .setMacAddr(wrapper.getString(MAC_ADDR, getLocalMacAddress()))
                // 结算货币
                .setCurrencyId(wrapper.getString(CURRENCY_ID, "CNY"))
                // 交易日
                .setTradingDay(wrapper.getString(TRADING_DAY, YYYYMMDD.fmt(parseTradingDay(LocalDateTime.now()))));
    }


}
