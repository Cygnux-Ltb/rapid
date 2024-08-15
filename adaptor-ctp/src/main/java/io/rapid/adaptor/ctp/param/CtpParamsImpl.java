package io.rapid.adaptor.ctp.param;

import io.mercury.common.param.impl.ImmutableParams;
import jakarta.annotation.Nonnull;

import java.util.Map;
import java.util.Properties;

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

public final class CtpParamsImpl extends ImmutableParams implements CtpParams {

    /**
     * 根据传入的Key获取Map中的相应字段
     *
     * @param map Map<String, ?>
     * @throws NullPointerException     e
     * @throws IllegalArgumentException e
     */
    public CtpParamsImpl(@Nonnull Map<String, String> map)
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
    public CtpParamsImpl(@Nonnull Properties prop)
            throws NullPointerException, IllegalArgumentException {
        super(prop, CtpParamKey.values());
    }


    @Override
    public String getTraderAddr() {
        return getString(TraderAddr);
    }

    @Override
    public String getMdAddr() {
        return getString(MdAddr);
    }

    @Override
    public String getAppId() {
        return getString(AppId);
    }

    @Override
    public String getBrokerId() {
        return getString(BrokerId);
    }

    @Override
    public String getInvestorId() {
        return getString(InvestorId);
    }

    @Override
    public String getAccountId() {
        return getString(AccountId);
    }

    @Override
    public String getUserId() {
        return getString(UserId);
    }

    @Override
    public String getPassword() {
        return getString(Password);
    }

    @Override
    public String getAuthCode() {
        return getString(AuthCode);
    }

    @Override
    public String getIpAddr() {
        return getString(IpAddr);
    }

    @Override
    public String getMacAddr() {
        return getString(MacAddr);
    }

    @Override
    public String getTradingDay() {
        return getString(TradingDay);
    }

    @Override
    public String getCurrencyId() {
        return getString(CurrencyId);
    }

}
