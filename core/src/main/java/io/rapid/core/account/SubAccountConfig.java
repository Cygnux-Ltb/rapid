package io.cygnuxltb.jcts.core.account;


import io.mercury.common.cfg.ConfigOption;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum SubAccountConfig implements ConfigOption {

    SubAccountId("sys.subAccountId"),

    SubAccountName("sys.subAccountName"),

    SubBalance("sys.subBalance"),

    SubCredit("sys.subCredit"),

    ;

    private final String configName;

    @Override
    public String getConfigName() {
        return configName;
    }

}
