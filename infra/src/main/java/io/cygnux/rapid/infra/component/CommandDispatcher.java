package io.cygnux.rapid.infra.component;

import io.cygnux.rapid.infra.dto.req.TradingSwitchesReq;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public final class CommandDispatcher {

    @PostConstruct
    private void init() {
    }

    public boolean sendControlCommand(TradingSwitchesReq strategySwitch) {
        return false;
    }

    public boolean sendStrategyCommand(TradingSwitchesReq strategySwitch) {
        return false;
    }

}
