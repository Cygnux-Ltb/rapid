package io.rapid.adaptor.ctp.param;

import io.mercury.common.param.impl.ImmutableParams;
import jakarta.annotation.Nonnull;

import java.util.Map;
import java.util.Properties;

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
