package io.rapid.adaptor.ctp.param;

import ctp.thostapi.CThostFtdcReqAuthenticateField;
import ctp.thostapi.CThostFtdcReqUserLoginField;

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
