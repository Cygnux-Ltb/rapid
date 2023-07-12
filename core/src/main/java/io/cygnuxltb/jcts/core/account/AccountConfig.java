package io.cygnuxltb.jcts.core.account;


import io.mercury.common.config.ConfigOption;

public enum AccountConfig implements ConfigOption {

    AccountId("sys.accountId"),

    BrokerId("sys.brokerId"),

    BrokerName("sys.brokerName"),

    InvestorId("sys.investorId"),

    Balance("sys.balance"),

    Credit("sys.credit"),

    Remark("sys.remark");

    private final String configName;

    AccountConfig(String configName) {
        this.configName = configName;
    }

    @Override
    public String getConfigName() {
        return configName;
    }
}
