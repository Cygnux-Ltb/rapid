package io.rapid.adaptor.ctp.param;

import ctp.thostapi.CThostFtdcReqAuthenticateField;
import ctp.thostapi.CThostFtdcReqUserLoginField;

import java.util.ArrayList;

import static io.mercury.common.util.StringSupport.isNullOrEmpty;

public interface CtpParams {

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

    default void assertCtpParams() {
        var paramNames = new ArrayList<String>();
        if (isNullOrEmpty(getTraderAddr()))
            paramNames.add("TraderAddr");
        if (isNullOrEmpty(getMdAddr()))
            paramNames.add("MdAddr");
        if (isNullOrEmpty(getAppId()))
            paramNames.add("AppId");
        if (isNullOrEmpty(getBrokerId()))
            paramNames.add("BrokerId");
        if (isNullOrEmpty(getInvestorId()))
            paramNames.add("InvestorId");
        if (isNullOrEmpty(getAccountId()))
            paramNames.add("AccountId");
        if (isNullOrEmpty(getUserId()))
            paramNames.add("UserId");
        if (isNullOrEmpty(getPassword()))
            paramNames.add("Password");
        if (isNullOrEmpty(getAuthCode()))
            paramNames.add("AuthCode");
        if (isNullOrEmpty(getIpAddr()))
            paramNames.add("IpAddr");
        if (isNullOrEmpty(getMacAddr()))
            paramNames.add("MacAddr");
        if (isNullOrEmpty(getTradingDay()))
            paramNames.add("TradingDay");
        if (isNullOrEmpty(getCurrencyId()))
            paramNames.add("CurrencyId");
        if (!paramNames.isEmpty())
            throw new IllegalArgumentException("CTP param not set -> [" + String.join(" , ", paramNames) + "]");
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
