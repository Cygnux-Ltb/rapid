package io.rapid.adaptor.ctp.param;

import io.mercury.common.config.ConfigOption;
import io.mercury.common.param.ParamKey;
import io.mercury.common.param.Params.ValueType;
import lombok.Getter;

/**
 * 用于读取FTDC配置信息
 *
 * @author yellow013
 */
public enum FtdcParamKey implements ParamKey, ConfigOption {

    /**
     * 交易服务器地址
     */
    TraderAddr("traderAddr"),

    /**
     * 行情服务器地址
     */
    MdAddr("mdAddr"),

    /**
     * 应用ID
     */
    AppId("appId"),

    /**
     * 经纪商ID
     */
    BrokerId("brokerId"),

    /**
     * 投资者ID
     */
    InvestorId("investorId"),

    /**
     * 账号ID
     */
    AccountId("accountId"),

    /**
     * 用户ID
     */
    UserId("userId"),

    /**
     * 密码
     */
    Password("password"),

    /**
     * 认证码
     */
    AuthCode("authCode"),

    /**
     * 客户端IP地址
     */
    IpAddr("ipAddr"),

    /**
     * 客户端MAC地址
     */
    MacAddr("macAddr"),

    /**
     * 结算货币
     */
    CurrencyId("currencyId"),

    /**
     * 交易日
     */
    TradingDay("tradingDay"),

    ;

    @Getter
    private final String paramName;

    @Getter
    private final ValueType valueType = ValueType.STRING;

    @Getter
    private final String configName;

    FtdcParamKey(String paramName) {
        this.paramName = paramName;
        this.configName = "ctp." + paramName;
    }

    @Override
    public int getParamId() {
        return ordinal();
    }

    public static void main(String[] args) {
        for (FtdcParamKey key : FtdcParamKey.values())
            System.out.println(key.getConfigName() + "=" + key);
    }

}
