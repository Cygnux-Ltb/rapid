package io.cygnuxltb.protocol.http.pack;

import io.cygnuxltb.protocol.http.request.StrategySwitch;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Accessors(chain = true)
public class OutboxMessage<T> {

    private String title;

    private T content;

    public static void main(String[] args) {

        StrategySwitch switch1 = new StrategySwitch()
                .setProductId(1).setStrategyId(1)
                .setInstrumentCode("ni").setTradable(true);
        StrategySwitch switch2 = new StrategySwitch()
                .setProductId(1).setStrategyId(1)
                .setInstrumentCode("rb").setTradable(true);
        StrategySwitch switch3 = new StrategySwitch()
                .setProductId(1).setStrategyId(2)
                .setInstrumentCode("TA").setTradable(true);
        StrategySwitch switch4 = new StrategySwitch()
                .setProductId(1).setStrategyId(2)
                .setInstrumentCode("MA").setTradable(true);
        StrategySwitch switch5 = new StrategySwitch()
                .setProductId(1).setStrategyId(2)
                .setInstrumentCode("cu").setTradable(true);
        StrategySwitch switch6 = new StrategySwitch()
                .setProductId(1).setStrategyId(3)
                .setInstrumentCode("p").setTradable(true);

        List<StrategySwitch> list = new ArrayList<>();

        Map<String, List<StrategySwitch>> map = new HashMap<>();

        map.put("1", list);

        map.get("1").add(switch1);
        map.get("1").add(switch2);
        map.get("1").add(switch3);
        map.get("1").add(switch4);
        map.get("1").add(switch5);
        map.get("1").add(switch6);

        System.out.println(map);

    }

}
