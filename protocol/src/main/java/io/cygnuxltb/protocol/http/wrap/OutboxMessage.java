package io.cygnuxltb.protocol.http.wrap;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class OutboxMessage<T> {

    private String title;

    private T content;

    public static void main(String[] args) {

//        TradingSwitchesReq switch1 = new TradingSwitchesReq()
//                .setProductId(1).setStrategyId(1)
//                .setInstrumentCode("ni").setTradable(true);
//        TradingSwitchesReq switch2 = new TradingSwitchesReq()
//                .setProductId(1).setStrategyId(1)
//                .setInstrumentCode("rb").setTradable(true);
//        TradingSwitchesReq switch3 = new TradingSwitchesReq()
//                .setProductId(1).setStrategyId(2)
//                .setInstrumentCode("TA").setTradable(true);
//        TradingSwitchesReq switch4 = new TradingSwitchesReq()
//                .setProductId(1).setStrategyId(2)
//                .setInstrumentCode("MA").setTradable(true);
//        TradingSwitchesReq switch5 = new TradingSwitchesReq()
//                .setProductId(1).setStrategyId(2)
//                .setInstrumentCode("cu").setTradable(true);
//        TradingSwitchesReq switch6 = new TradingSwitchesReq()
//                .setProductId(1).setStrategyId(3)
//                .setInstrumentCode("p").setTradable(true);
//
//        List<TradingSwitchesReq> list = new ArrayList<>();
//
//        Map<String, List<TradingSwitchesReq>> map = new HashMap<>();
//
//        map.put("1", list);
//
//        map.get("1").add(switch1);
//        map.get("1").add(switch2);
//        map.get("1").add(switch3);
//        map.get("1").add(switch4);
//        map.get("1").add(switch5);
//        map.get("1").add(switch6);
//
//        System.out.println(map);

    }

}
