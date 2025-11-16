package io.cygnux.rapid.core.shared.event;

import io.mercury.common.serialization.Copyable;
import io.mercury.serialization.json.JsonBean;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 控制指令
 */
@Getter
@Setter
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ControlCommand extends JsonBean implements Copyable<ControlCommand> {

    private int strategyId = -1;

    private CommandType type;

    @Override
    public void copyOf(ControlCommand source) {
        this.strategyId = source.strategyId;
        this.type = source.type;
    }

    public enum CommandType {
        INIT, START, STOP
    }

}
