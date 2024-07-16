package io.rapid.adaptor.ctp.param;

import io.mercury.common.param.impl.ImmutableParams;
import jakarta.annotation.Nonnull;

import java.util.Map;
import java.util.Properties;

public final class CtpParamImpl extends ImmutableParams implements CtpParam {

    /**
     * 根据传入的Key获取Map中的相应字段
     *
     * @param map Map<String, ?>
     * @throws NullPointerException     e
     * @throws IllegalArgumentException e
     */
    public CtpParamImpl(@Nonnull Map<String, String> map)
            throws NullPointerException, IllegalArgumentException {
        super(map, CtpParamKey.values());
    }

    /**
     * 根据传入的Key获取Properties中的相应字段
     *
     * @param prop Properties
     * @throws NullPointerException     e
     * @throws IllegalArgumentException e
     */
    public CtpParamImpl(@Nonnull Properties prop)
            throws NullPointerException, IllegalArgumentException {
        super(prop, CtpParamKey.values());
    }


    @Override
    public String getTraderAddr() {
        return getString(CtpParamKey.TraderAddr);
    }

    @Override
    public String getMdAddr() {
        return getString(CtpParamKey.MdAddr);
    }

    @Override
    public String getAppId() {
        return getString(CtpParamKey.AppId);
    }

    @Override
    public String getBrokerId() {
        return getString(CtpParamKey.BrokerId);
    }

    @Override
    public String getInvestorId() {
        return getString(CtpParamKey.InvestorId);
    }

    @Override
    public String getAccountId() {
        return getString(CtpParamKey.AccountId);
    }

    @Override
    public String getUserId() {
        return getString(CtpParamKey.UserId);
    }

    @Override
    public String getPassword() {
        return getString(CtpParamKey.Password);
    }

    @Override
    public String getAuthCode() {
        return getString(CtpParamKey.AuthCode);
    }

    @Override
    public String getIpAddr() {
        return getString(CtpParamKey.IpAddr);
    }

    @Override
    public String getMacAddr() {
        return getString(CtpParamKey.MacAddr);
    }

    @Override
    public String getTradingDay() {
        return getString(CtpParamKey.TradingDay);
    }

    @Override
    public String getCurrencyId() {
        return getString(CtpParamKey.CurrencyId);
    }

}
