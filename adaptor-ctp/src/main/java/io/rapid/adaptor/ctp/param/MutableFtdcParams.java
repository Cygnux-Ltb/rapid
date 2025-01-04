package io.rapid.adaptor.ctp.param;

import com.typesafe.config.Config;
import io.mercury.common.config.ConfigWrapper;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

import static io.mercury.common.datetime.pattern.impl.DatePattern.YYYYMMDD;
import static io.mercury.common.net.NetworkProperties.getLocalMacAddress;
import static io.rapid.adaptor.ctp.param.FtdcParamKey.AccountId;
import static io.rapid.adaptor.ctp.param.FtdcParamKey.AppId;
import static io.rapid.adaptor.ctp.param.FtdcParamKey.AuthCode;
import static io.rapid.adaptor.ctp.param.FtdcParamKey.BrokerId;
import static io.rapid.adaptor.ctp.param.FtdcParamKey.CurrencyId;
import static io.rapid.adaptor.ctp.param.FtdcParamKey.InvestorId;
import static io.rapid.adaptor.ctp.param.FtdcParamKey.IpAddr;
import static io.rapid.adaptor.ctp.param.FtdcParamKey.MacAddr;
import static io.rapid.adaptor.ctp.param.FtdcParamKey.MdAddr;
import static io.rapid.adaptor.ctp.param.FtdcParamKey.Password;
import static io.rapid.adaptor.ctp.param.FtdcParamKey.TraderAddr;
import static io.rapid.adaptor.ctp.param.FtdcParamKey.TradingDay;
import static io.rapid.adaptor.ctp.param.FtdcParamKey.UserId;
import static io.rapid.core.instrument.futures.ChinaFutures.parseTradingDay;

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
                .setTraderAddr(wrapper.getStringOrThrows(TraderAddr))
                // 行情服务器地址
                .setMdAddr(wrapper.getStringOrThrows(MdAddr))
                // 应用ID
                .setAppId(wrapper.getStringOrThrows(AppId))
                // 经纪商ID
                .setBrokerId(wrapper.getStringOrThrows(BrokerId))
                // 投资者ID
                .setInvestorId(wrapper.getStringOrThrows(InvestorId))
                // 账号ID
                .setAccountId(wrapper.getStringOrThrows(AccountId))
                // 用户ID
                .setUserId(wrapper.getStringOrThrows(UserId))
                // 密码
                .setPassword(wrapper.getStringOrThrows(Password))
                // 认证码
                .setAuthCode(wrapper.getStringOrThrows(AuthCode))
                // 客户端IP地址
                .setIpAddr(wrapper.getString(IpAddr, "127.0.0.1"))
                // 客户端MAC地址
                .setMacAddr(wrapper.getString(MacAddr, getLocalMacAddress()))
                // 结算货币
                .setCurrencyId(wrapper.getString(CurrencyId, "CNY"))
                // 交易日
                .setTradingDay(wrapper.getString(TradingDay, YYYYMMDD.fmt(parseTradingDay(LocalDateTime.now()))));
    }


}
