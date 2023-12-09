package io.cygnuxltb.adaptor.ctp;

import com.typesafe.config.Config;
import ctp.thostapi.CThostFtdcReqAuthenticateField;
import ctp.thostapi.CThostFtdcReqUserLoginField;
import io.mercury.common.cfg.ConfigWrapper;
import io.mercury.common.param.Params;
import io.mercury.serialization.json.JsonWrapper;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.time.LocalDateTime;

import static io.cygnuxltb.adaptor.ctp.CtpAdaptorParamKey.AccountId;
import static io.cygnuxltb.adaptor.ctp.CtpAdaptorParamKey.AppId;
import static io.cygnuxltb.adaptor.ctp.CtpAdaptorParamKey.AuthCode;
import static io.cygnuxltb.adaptor.ctp.CtpAdaptorParamKey.BrokerId;
import static io.cygnuxltb.adaptor.ctp.CtpAdaptorParamKey.CurrencyId;
import static io.cygnuxltb.adaptor.ctp.CtpAdaptorParamKey.InvestorId;
import static io.cygnuxltb.adaptor.ctp.CtpAdaptorParamKey.IpAddr;
import static io.cygnuxltb.adaptor.ctp.CtpAdaptorParamKey.MacAddr;
import static io.cygnuxltb.adaptor.ctp.CtpAdaptorParamKey.MdAddr;
import static io.cygnuxltb.adaptor.ctp.CtpAdaptorParamKey.Password;
import static io.cygnuxltb.adaptor.ctp.CtpAdaptorParamKey.TraderAddr;
import static io.cygnuxltb.adaptor.ctp.CtpAdaptorParamKey.TradingDay;
import static io.cygnuxltb.adaptor.ctp.CtpAdaptorParamKey.UserId;
import static io.mercury.common.datetime.pattern.DatePattern.YYYYMMDD;
import static io.mercury.common.net.NetworkProperties.getLocalMacAddress;
import static io.rapid.core.instrument.futures.ChinaFutures.ChinaFuturesUtil.parseTradingDay;

@Getter
@Setter
@Accessors(chain = true)
@Configuration
@PropertySource("file:${user.home}/config/ctp.properties")
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
        var wrapper = new ConfigWrapper<CtpAdaptorParamKey>(config);
        return new CtpConfig()
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

    public static CtpConfig with(Params<CtpAdaptorParamKey> params) {
        return new CtpConfig()
                // 交易服务器地址
                .setTraderAddr(params.getString(TraderAddr))
                // 行情服务器地址
                .setMdAddr(params.getString(MdAddr))
                // 应用ID
                .setAppId(params.getString(AppId))
                // 经纪商ID
                .setBrokerId(params.getString(BrokerId))
                // 投资者ID
                .setInvestorId(params.getString(InvestorId))
                // 账号ID
                .setAccountId(params.getString(AccountId))
                // 用户ID
                .setUserId(params.getString(UserId))
                // 密码
                .setPassword(params.getString(Password))
                // 认证码
                .setAuthCode(params.getString(AuthCode))
                // 客户端IP地址
                .setIpAddr(params.getString(IpAddr))
                // 客户端MAC地址
                .setMacAddr(params.getString(MacAddr))
                // 结算货币
                .setCurrencyId(params.getString(CurrencyId))
                // 交易日
                .setTradingDay(params.getString(TradingDay));
    }

    public CThostFtdcReqAuthenticateField getReqAuthenticateField() {
        CThostFtdcReqAuthenticateField ReqField = new CThostFtdcReqAuthenticateField();
        ReqField.setAppID(this.getAppId());
        ReqField.setUserID(this.getUserId());
        ReqField.setBrokerID(this.getBrokerId());
        ReqField.setAuthCode(this.getAuthCode());
        return ReqField;
    }

    public CThostFtdcReqUserLoginField getReqUserLoginField() {
        CThostFtdcReqUserLoginField ReqField = new CThostFtdcReqUserLoginField();
        ReqField.setBrokerID(this.getBrokerId());
        ReqField.setUserID(this.getUserId());
        ReqField.setPassword(this.getPassword());
        ReqField.setClientIPAddress(this.getIpAddr());
        ReqField.setMacAddress(this.getMacAddr());
        return ReqField;
    }
    
}
