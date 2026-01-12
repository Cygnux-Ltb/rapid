package io.cygnux.rapid.gateway.ctp;


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

    default FtdcParams assertParams() {
        var errParamNames = new ArrayList<String>();
        if (isNullOrEmpty(getTraderAddr()))
            errParamNames.add("TraderAddr");
        if (isNullOrEmpty(getMdAddr()))
            errParamNames.add("MdAddr");
        if (isNullOrEmpty(getAppId()))
            errParamNames.add("AppId");
        if (isNullOrEmpty(getBrokerId()))
            errParamNames.add("BrokerId");
        if (isNullOrEmpty(getInvestorId()))
            errParamNames.add("InvestorId");
        if (isNullOrEmpty(getAccountId()))
            errParamNames.add("AccountId");
        if (isNullOrEmpty(getUserId()))
            errParamNames.add("UserId");
        if (isNullOrEmpty(getPassword()))
            errParamNames.add("Password");
        if (isNullOrEmpty(getAuthCode()))
            errParamNames.add("AuthCode");
        if (isNullOrEmpty(getIpAddr()))
            errParamNames.add("IpAddr");
        if (isNullOrEmpty(getMacAddr()))
            errParamNames.add("MacAddr");
        if (isNullOrEmpty(getTradingDay()))
            errParamNames.add("TradingDay");
        if (isNullOrEmpty(getCurrencyId()))
            errParamNames.add("CurrencyId");
        if (!errParamNames.isEmpty())
            throw new IllegalArgumentException("CTP param not set -> [" + String.join(" , ", errParamNames) + "]");
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
