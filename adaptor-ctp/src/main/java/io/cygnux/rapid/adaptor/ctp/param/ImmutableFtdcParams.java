package io.cygnux.rapid.adaptor.ctp.param;

import io.mercury.common.param.impl.ImmutableParams;
import jakarta.annotation.Nonnull;

import java.util.Map;
import java.util.Properties;

import static io.cygnux.rapid.adaptor.ctp.param.FtdcParamKey.ACCOUNT_ID;
import static io.cygnux.rapid.adaptor.ctp.param.FtdcParamKey.APP_ID;
import static io.cygnux.rapid.adaptor.ctp.param.FtdcParamKey.AUTH_CODE;
import static io.cygnux.rapid.adaptor.ctp.param.FtdcParamKey.BROKER_ID;
import static io.cygnux.rapid.adaptor.ctp.param.FtdcParamKey.CURRENCY_ID;
import static io.cygnux.rapid.adaptor.ctp.param.FtdcParamKey.INVESTOR_ID;
import static io.cygnux.rapid.adaptor.ctp.param.FtdcParamKey.IP_ADDR;
import static io.cygnux.rapid.adaptor.ctp.param.FtdcParamKey.MAC_ADDR;
import static io.cygnux.rapid.adaptor.ctp.param.FtdcParamKey.MD_ADDR;
import static io.cygnux.rapid.adaptor.ctp.param.FtdcParamKey.PASSWORD;
import static io.cygnux.rapid.adaptor.ctp.param.FtdcParamKey.TRADER_ADDR;
import static io.cygnux.rapid.adaptor.ctp.param.FtdcParamKey.TRADING_DAY;
import static io.cygnux.rapid.adaptor.ctp.param.FtdcParamKey.USERID;

public final class ImmutableFtdcParams extends ImmutableParams implements FtdcParams {

    /**
     * 根据传入的Key获取Map中的相应字段
     *
     * @param map Map<String, ?>
     * @throws NullPointerException     e
     * @throws IllegalArgumentException e
     */
    public ImmutableFtdcParams(@Nonnull Map<String, String> map)
            throws NullPointerException, IllegalArgumentException {
        super(map, FtdcParamKey.values());
    }

    /**
     * 根据传入的Key获取Properties中的相应字段
     *
     * @param prop Properties
     * @throws NullPointerException     e
     * @throws IllegalArgumentException e
     */
    public ImmutableFtdcParams(@Nonnull Properties prop)
            throws NullPointerException, IllegalArgumentException {
        super(prop, FtdcParamKey.values());
    }


    @Override
    public String getTraderAddr() {
        return getString(TRADER_ADDR);
    }

    @Override
    public String getMdAddr() {
        return getString(MD_ADDR);
    }

    @Override
    public String getAppId() {
        return getString(APP_ID);
    }

    @Override
    public String getBrokerId() {
        return getString(BROKER_ID);
    }

    @Override
    public String getInvestorId() {
        return getString(INVESTOR_ID);
    }

    @Override
    public String getAccountId() {
        return getString(ACCOUNT_ID);
    }

    @Override
    public String getUserId() {
        return getString(USERID);
    }

    @Override
    public String getPassword() {
        return getString(PASSWORD);
    }

    @Override
    public String getAuthCode() {
        return getString(AUTH_CODE);
    }

    @Override
    public String getIpAddr() {
        return getString(IP_ADDR);
    }

    @Override
    public String getMacAddr() {
        return getString(MAC_ADDR);
    }

    @Override
    public String getTradingDay() {
        return getString(TRADING_DAY);
    }

    @Override
    public String getCurrencyId() {
        return getString(CURRENCY_ID);
    }

}
