package io.rapid.adaptor.ctp;

import io.mercury.common.cfg.ConfigOption;
import io.mercury.common.param.ParamKey;
import io.mercury.common.param.Params.ValueType;

import static io.mercury.common.param.Params.ValueType.STRING;

/**
 * 用于读取FTDC配置信息
 *
 * @author yellow013
 */
public enum CtpAdaptorParamKey implements ParamKey, ConfigOption {

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
    UserId("userid"),

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

    private final String paramName;

    private final ValueType valueType = STRING;

    private final String configName;

    CtpAdaptorParamKey(String paramName) {
        this.paramName = paramName;
        this.configName = "ctp." + paramName;
    }

    @Override
    public String getParamName() {
        return paramName;
    }

    @Override
    public ValueType getValueType() {
        return valueType;
    }

    @Override
    public int getParamId() {
        return ordinal();
    }

    @Override
    public String getConfigName() {
        return configName;
    }

    public static void main(String[] args) {
        for (CtpAdaptorParamKey key : CtpAdaptorParamKey.values())
            System.out.println(key.getConfigName() + "=" + key);
    }

}
