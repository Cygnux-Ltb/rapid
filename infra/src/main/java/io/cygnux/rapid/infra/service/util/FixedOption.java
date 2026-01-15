package io.cygnux.rapid.infra.service.util;

import io.cygnux.rapid.core.types.adapter.enums.SupportedAdapter;
import io.cygnux.rapid.core.types.strategy.StrategyType;
import io.cygnux.rapid.infra.dto.common.GeneralOption;
import io.mercury.common.param.Params.ValueType;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Arrays.stream;

@Component
public class FixedOption {

    private final List<GeneralOption> paramTypeOptions = stream(ValueType.values())
            .map(Enum::name)
            .map(GeneralOption::new)
            .toList();

    private final List<GeneralOption> strategyTypeOptions = stream(StrategyType.values())
            .map(Enum::name)
            .map(GeneralOption::new)
            .toList();

    private final List<GeneralOption> adapterTypeOptions = stream(SupportedAdapter.values())
            .map(Enum::name)
            .map(GeneralOption::new)
            .toList();

    public List<GeneralOption> getParamTypeOptions() {
        return paramTypeOptions;
    }

    public List<GeneralOption> getStrategyTypeOptions() {
        return strategyTypeOptions;
    }

    public List<GeneralOption> getAdapterTypeOptions() {
        return adapterTypeOptions;
    }

}
