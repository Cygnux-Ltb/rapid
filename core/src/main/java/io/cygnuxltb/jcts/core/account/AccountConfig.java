package io.cygnuxltb.jcts.core.account;


import io.mercury.common.config.ConfigOption;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum AccountConfig implements ConfigOption {

    AccountId("sys.accountId"),

    BrokerId("sys.brokerId"),

    BrokerName("sys.brokerName"),

    InvestorId("sys.investorId"),

    Balance("sys.balance"),

    Credit("sys.credit"),

    Remark("sys.remark");

    private final String configName;

    @Override
    public String getConfigName() {
        return configName;
    }

}
