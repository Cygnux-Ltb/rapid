package io.rapid.adaptor.ctp.param;


import org.rationalityfrontline.jctp.CThostFtdcReqAuthenticateField;
import org.rationalityfrontline.jctp.CThostFtdcReqUserLoginField;

import java.util.ArrayList;

import static io.mercury.common.util.StringSupport.isNullOrEmpty;

public interface FtdcParams {

    String getTraderAddr();

    String getMdAddr();

    String getAppId();

    String getBrokerId();

    String getInvestorId();

    String getAccountId();

    String getUserId();

    String getPassword();

    String getAuthCode();

    String getIpAddr();

    String getMacAddr();

    String getTradingDay();

    String getCurrencyId();

    default FtdcParams assertCtpParams() {
        var errorParamNames = new ArrayList<String>();
        if (isNullOrEmpty(getTraderAddr()))
            errorParamNames.add("TraderAddr");
        if (isNullOrEmpty(getMdAddr()))
            errorParamNames.add("MdAddr");
        if (isNullOrEmpty(getAppId()))
            errorParamNames.add("AppId");
        if (isNullOrEmpty(getBrokerId()))
            errorParamNames.add("BrokerId");
        if (isNullOrEmpty(getInvestorId()))
            errorParamNames.add("InvestorId");
        if (isNullOrEmpty(getAccountId()))
            errorParamNames.add("AccountId");
        if (isNullOrEmpty(getUserId()))
            errorParamNames.add("UserId");
        if (isNullOrEmpty(getPassword()))
            errorParamNames.add("Password");
        if (isNullOrEmpty(getAuthCode()))
            errorParamNames.add("AuthCode");
        if (isNullOrEmpty(getIpAddr()))
            errorParamNames.add("IpAddr");
        if (isNullOrEmpty(getMacAddr()))
            errorParamNames.add("MacAddr");
        if (isNullOrEmpty(getTradingDay()))
            errorParamNames.add("TradingDay");
        if (isNullOrEmpty(getCurrencyId()))
            errorParamNames.add("CurrencyId");
        if (!errorParamNames.isEmpty())
            throw new IllegalArgumentException("CTP param not set -> [" + String.join(" , ", errorParamNames) + "]");
        return this;
    }

    default CThostFtdcReqAuthenticateField getReqAuthenticateField() {
        var Field = new CThostFtdcReqAuthenticateField();
        Field.setAppID(getAppId());
        Field.setUserID(getUserId());
        Field.setBrokerID(getBrokerId());
        Field.setAuthCode(getAuthCode());
        return Field;
    }

    default CThostFtdcReqUserLoginField getReqUserLoginField() {
        var Field = new CThostFtdcReqUserLoginField();
        Field.setBrokerID(getBrokerId());
        Field.setUserID(getUserId());
        Field.setPassword(getPassword());
        Field.setClientIPAddress(getIpAddr());
        Field.setMacAddress(getMacAddr());
        Field.setTradingDay(getTradingDay());
        return Field;
    }

}
