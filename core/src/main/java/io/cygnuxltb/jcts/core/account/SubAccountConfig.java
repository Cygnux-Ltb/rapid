package io.cygnuxltb.jcts.core.account;


import io.mercury.common.config.ConfigOption;

public enum SubAccountConfig implements ConfigOption {

    SubAccountId("sys.subAccountId"),

    SubAccountName("sys.subAccountName"),

    SubBalance("sys.subBalance"),

    SubCredit("sys.subCredit");

    private final String configName;

    SubAccountConfig(String configName) {
        this.configName = configName;
    }

    @Override
    public String getConfigName() {
        return configName;
    }

}
