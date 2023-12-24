package io.cygnuxltb.console.component;

import io.cygnuxltb.console.persistence.entity.SysParamEntity;
import io.cygnuxltb.protocol.http.request.StrategySwitch;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public final class CommandDispatcher {

    @PostConstruct
    private void init() {
    }

    public boolean sendControlCommand(StrategySwitch strategySwitch) {
        return false;
    }

    public boolean sendStrategyCommand(StrategySwitch strategySwitch) {
        return false;
    }

    public boolean sendParams(List<SysParamEntity> params) {
        return false;
    }

}
