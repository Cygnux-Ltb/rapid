package io.cygnux.rapid.adaptor.ctp.param;

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
    TRADER_ADDR("traderAddr"),

    /**
     * 行情服务器地址
     */
    MD_ADDR("mdAddr"),

    /**
     * 应用ID
     */
    APP_ID("appId"),

    /**
     * 经纪商ID
     */
    BROKER_ID("brokerId"),

    /**
     * 投资者ID
     */
    INVESTOR_ID("investorId"),

    /**
     * 账号ID
     */
    ACCOUNT_ID("accountId"),

    /**
     * 用户ID
     */
    USERID("userId"),

    /**
     * 密码
     */
    PASSWORD("password"),

    /**
     * 认证码
     */
    AUTH_CODE("authCode"),

    /**
     * 客户端IP地址
     */
    IP_ADDR("ipAddr"),

    /**
     * 客户端MAC地址
     */
    MAC_ADDR("macAddr"),

    /**
     * 结算货币
     */
    CURRENCY_ID("currencyId"),

    /**
     * 交易日
     */
    TRADING_DAY("tradingDay"),

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
