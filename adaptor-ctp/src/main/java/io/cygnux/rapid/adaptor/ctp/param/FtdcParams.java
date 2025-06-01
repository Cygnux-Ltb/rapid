package io.cygnux.rapid.adaptor.ctp.param;


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
        var authenticateField = new CThostFtdcReqAuthenticateField();
        authenticateField.setAppID(getAppId());
        authenticateField.setUserID(getUserId());
        authenticateField.setBrokerID(getBrokerId());
        authenticateField.setAuthCode(getAuthCode());
        return authenticateField;
    }

    default CThostFtdcReqUserLoginField getReqUserLoginField() {
        var userLoginField = new CThostFtdcReqUserLoginField();
        userLoginField.setBrokerID(getBrokerId());
        userLoginField.setUserID(getUserId());
        userLoginField.setPassword(getPassword());
        userLoginField.setClientIPAddress(getIpAddr());
        userLoginField.setMacAddress(getMacAddr());
        userLoginField.setTradingDay(getTradingDay());
        return userLoginField;
    }

}
