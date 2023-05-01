package io.cygnuxltb.channel.ctp;

import com.typesafe.config.Config;
import io.cygnuxltb.channel.ctp.adaptor.CtpAdaptorParamKey;
import io.mercury.common.config.ConfigWrapper;
import io.mercury.common.param.Params;
import io.mercury.serialization.json.JsonWrapper;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

import static io.horizon.market.instrument.futures.ChinaFutures.ChinaFuturesUtil.parseTradingDay;
import static io.mercury.common.datetime.pattern.DatePattern.YYYYMMDD;
import static io.mercury.common.net.NetworkProperties.getLocalMacAddress;

@Getter
@Setter
@Accessors(chain = true)
@Configuration
public class CtpConfig {

    @Value("traderAddr")
    private String traderAddr;
    @Value("mdAddr")
    private String mdAddr;

    @Value("appId")
    private String appId;
    @Value("brokerId")
    private String brokerId;
    @Value("investorId")
    private String investorId;
    @Value("accountId")
    private String accountId;
    @Value("userId")
    private String userId;

    @Value("password")
    private String password;
    @Value("authCode")
    private String authCode;

    @Value("ipAddr")
    private String ipAddr;
    @Value("macAddr")
    private String macAddr;

    @Value("tradingDay")
    private String tradingDay;
    @Value("currencyId")
    private String currencyId;

    @Override
    public String toString() {
        return JsonWrapper.toJson(this);
    }

    public static CtpConfig with(Config config) {
        ConfigWrapper<CtpAdaptorParamKey> wrapper = new ConfigWrapper<>(config);
        return new CtpConfig()
                // 交易服务器地址
                .setTraderAddr(wrapper.getStringOrThrows(CtpAdaptorParamKey.TraderAddr))
                // 行情服务器地址
                .setMdAddr(wrapper.getStringOrThrows(CtpAdaptorParamKey.MdAddr))
                // 应用ID
                .setAppId(wrapper.getStringOrThrows(CtpAdaptorParamKey.AppId))
                // 经纪商ID
                .setBrokerId(wrapper.getStringOrThrows(CtpAdaptorParamKey.BrokerId))
                // 投资者ID
                .setInvestorId(wrapper.getStringOrThrows(CtpAdaptorParamKey.InvestorId))
                // 账号ID
                .setAccountId(wrapper.getStringOrThrows(CtpAdaptorParamKey.AccountId))
                // 用户ID
                .setUserId(wrapper.getStringOrThrows(CtpAdaptorParamKey.UserId))
                // 密码
                .setPassword(wrapper.getStringOrThrows(CtpAdaptorParamKey.Password))
                // 认证码
                .setAuthCode(wrapper.getStringOrThrows(CtpAdaptorParamKey.AuthCode))
                // 客户端IP地址
                .setIpAddr(wrapper.getString(CtpAdaptorParamKey.IpAddr, "127.0.0.1"))
                // 客户端MAC地址
                .setMacAddr(wrapper.getString(CtpAdaptorParamKey.MacAddr, getLocalMacAddress()))
                // 结算货币
                .setCurrencyId(wrapper.getString(CtpAdaptorParamKey.CurrencyId, "CNY"))
                // 交易日
                .setTradingDay(wrapper.getString(CtpAdaptorParamKey.TradingDay, YYYYMMDD.format(parseTradingDay(LocalDateTime.now()))));
    }

    public static CtpConfig with(Params<CtpAdaptorParamKey> params) {
        return new CtpConfig()
                // 交易服务器地址
                .setTraderAddr(params.getString(CtpAdaptorParamKey.TraderAddr))
                // 行情服务器地址
                .setMdAddr(params.getString(CtpAdaptorParamKey.MdAddr))
                // 应用ID
                .setAppId(params.getString(CtpAdaptorParamKey.AppId))
                // 经纪商ID
                .setBrokerId(params.getString(CtpAdaptorParamKey.BrokerId))
                // 投资者ID
                .setInvestorId(params.getString(CtpAdaptorParamKey.InvestorId))
                // 账号ID
                .setAccountId(params.getString(CtpAdaptorParamKey.AccountId))
                // 用户ID
                .setUserId(params.getString(CtpAdaptorParamKey.UserId))
                // 密码
                .setPassword(params.getString(CtpAdaptorParamKey.Password))
                // 认证码
                .setAuthCode(params.getString(CtpAdaptorParamKey.AuthCode))
                // 客户端IP地址
                .setIpAddr(params.getString(CtpAdaptorParamKey.IpAddr))
                // 客户端MAC地址
                .setMacAddr(params.getString(CtpAdaptorParamKey.MacAddr))
                // 结算货币
                .setCurrencyId(params.getString(CtpAdaptorParamKey.CurrencyId))
                // 交易日
                .setTradingDay(params.getString(CtpAdaptorParamKey.TradingDay));
    }

}
