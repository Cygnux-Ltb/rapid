package io.rapid.adaptor.ctp.component;

import com.typesafe.config.Config;
import io.mercury.common.config.ConfigWrapper;
import io.mercury.serialization.json.JsonWrapper;
import io.rapid.adaptor.ctp.param.CtpParams;
import io.rapid.adaptor.ctp.param.CtpParamKey;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.time.LocalDateTime;

import static io.mercury.common.datetime.pattern.impl.DatePattern.YYYYMMDD;
import static io.mercury.common.net.NetworkProperties.getLocalMacAddress;
import static io.rapid.adaptor.ctp.param.CtpParamKey.AccountId;
import static io.rapid.adaptor.ctp.param.CtpParamKey.AppId;
import static io.rapid.adaptor.ctp.param.CtpParamKey.AuthCode;
import static io.rapid.adaptor.ctp.param.CtpParamKey.BrokerId;
import static io.rapid.adaptor.ctp.param.CtpParamKey.CurrencyId;
import static io.rapid.adaptor.ctp.param.CtpParamKey.InvestorId;
import static io.rapid.adaptor.ctp.param.CtpParamKey.IpAddr;
import static io.rapid.adaptor.ctp.param.CtpParamKey.MacAddr;
import static io.rapid.adaptor.ctp.param.CtpParamKey.MdAddr;
import static io.rapid.adaptor.ctp.param.CtpParamKey.Password;
import static io.rapid.adaptor.ctp.param.CtpParamKey.TraderAddr;
import static io.rapid.adaptor.ctp.param.CtpParamKey.TradingDay;
import static io.rapid.adaptor.ctp.param.CtpParamKey.UserId;
import static io.rapid.core.instrument.futures.ChinaFutures.ChinaFuturesUtil.parseTradingDay;

@Getter
@Setter
@Accessors(chain = true)
@Configuration
@PropertySource("file:${user.home}/config/ctp.properties")
public class CtpConfigLoader implements CtpParams {

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

    public CtpConfigLoader load(Config config) {
        var wrapper = new ConfigWrapper<CtpParamKey>(config);
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
