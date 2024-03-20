package io.cygnuxltb.protocol.http.wrap;

import io.cygnuxltb.protocol.http.request.TradingSwitches;
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

        TradingSwitches switch1 = new TradingSwitches()
                .setProductId(1).setStrategyId(1)
                .setInstrumentCode("ni").setTradable(true);
        TradingSwitches switch2 = new TradingSwitches()
                .setProductId(1).setStrategyId(1)
                .setInstrumentCode("rb").setTradable(true);
        TradingSwitches switch3 = new TradingSwitches()
                .setProductId(1).setStrategyId(2)
                .setInstrumentCode("TA").setTradable(true);
        TradingSwitches switch4 = new TradingSwitches()
                .setProductId(1).setStrategyId(2)
                .setInstrumentCode("MA").setTradable(true);
        TradingSwitches switch5 = new TradingSwitches()
                .setProductId(1).setStrategyId(2)
                .setInstrumentCode("cu").setTradable(true);
        TradingSwitches switch6 = new TradingSwitches()
                .setProductId(1).setStrategyId(3)
                .setInstrumentCode("p").setTradable(true);

        List<TradingSwitches> list = new ArrayList<>();

        Map<String, List<TradingSwitches>> map = new HashMap<>();

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
