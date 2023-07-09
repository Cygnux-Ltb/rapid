package io.cygnuxltb.jcts.core.mkd;


import io.cygnuxltb.jcts.core.mkd.api.MarketDataIncrement;
import io.cygnuxltb.jcts.core.mkd.api.MarketDataSnapshot;
import io.cygnuxltb.jcts.core.mkd.api.Side;
import io.cygnuxltb.jcts.core.mkd.impl.DefaultMarketDataIncrement;
import io.cygnuxltb.jcts.core.mkd.impl.DefaultMarketDataNewOrder;
import io.cygnuxltb.jcts.core.mkd.impl.DefaultMarketDataReplaceOrder;
import io.cygnuxltb.jcts.core.mkd.impl.DefaultMarketDataSnapshot;
import io.cygnuxltb.jcts.core.mkd.impl.MarketDataSnapshotFunction;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;

public class MarketDataSnapshotFunctionTest {

    @Test
    public void testWithEventsProvided() {

        MarketDataIncrement increment1 = DefaultMarketDataIncrement.newBuilder()
                .withTriggerTimestamp(1)
                .withEventTimestamp(2)
                .withEvent(DefaultMarketDataNewOrder.newBuilder()
                        .withOrderId("1")
                        .withMarket("CNX")
                        .withInstrument("AUDUSD")
                        .withPrice(1.345)
                        .withQty(1000000)
                        .withSide(Side.BID).build())
                .build();

        MarketDataIncrement increment2 = DefaultMarketDataIncrement.newBuilder()
                .withTriggerTimestamp(3)
                .withEventTimestamp(4)
                .withEvent(DefaultMarketDataNewOrder.newBuilder()
                        .withOrderId("2")
                        .withMarket("CNX")
                        .withInstrument("AUDUSD")
                        .withPrice(1.543)
                        .withQty(1000000)
                        .withSide(Side.ASK).build())
                .build();

        MarketDataIncrement increment3 = DefaultMarketDataIncrement.newBuilder()
                .withTriggerTimestamp(5)
                .withEventTimestamp(6)
                .withEvent(DefaultMarketDataReplaceOrder.newBuilder()
                        .withOrderId("3")
                        .withPrevOrderId("1")
                        .withMarket("CNX")
                        .withInstrument("AUDUSD")
                        .withPrice(1.355)
                        .withQty(1000000)
                        .withSide(Side.BID).build())
                .build();


        MarketDataSnapshotFunction function = new MarketDataSnapshotFunction();
        MarketDataSnapshot snapshot1 = function.apply(increment1);
        MarketDataSnapshot expectedSnapshot1 = DefaultMarketDataSnapshot.newBuilder()
                .withTriggerTimestamp(1)
                .withEventTimestamp(2)
                .withEvent(DefaultMarketDataNewOrder.newBuilder()
                        .withOrderId("1")
                        .withMarket("CNX")
                        .withInstrument("AUDUSD")
                        .withPrice(1.345)
                        .withQty(1000000)
                        .withSide(Side.BID).build())
                .build();

        MatcherAssert.assertThat(snapshot1, MarketDataSnapshotMatcher.matches(expectedSnapshot1));


        MarketDataSnapshot snapshot2 = function.apply(increment2);
        MarketDataSnapshot expectedSnapshot2 = DefaultMarketDataSnapshot.newBuilder()
                .withTriggerTimestamp(3)
                .withEventTimestamp(4)
                .withEvent(DefaultMarketDataNewOrder.newBuilder()
                        .withOrderId("1")
                        .withMarket("CNX")
                        .withInstrument("AUDUSD")
                        .withPrice(1.345)
                        .withQty(1000000)
                        .withSide(Side.BID).build())
                .withEvent(DefaultMarketDataNewOrder.newBuilder()
                        .withOrderId("2")
                        .withMarket("CNX")
                        .withInstrument("AUDUSD")
                        .withPrice(1.543)
                        .withQty(1000000)
                        .withSide(Side.ASK).build())
                .build();

        MatcherAssert.assertThat(snapshot2, MarketDataSnapshotMatcher.matches(expectedSnapshot2));


        MarketDataSnapshot snapshot3 = function.apply(increment3);
        MarketDataSnapshot expectedSnapshot3 = DefaultMarketDataSnapshot.newBuilder()
                .withTriggerTimestamp(5)
                .withEventTimestamp(6)
                .withEvent(DefaultMarketDataNewOrder.newBuilder()
                        .withOrderId("2")
                        .withMarket("CNX")
                        .withInstrument("AUDUSD")
                        .withPrice(1.543)
                        .withQty(1000000)
                        .withSide(Side.ASK).build())
                .withEvent(DefaultMarketDataReplaceOrder.newBuilder()
                        .withOrderId("3")
                        .withPrevOrderId("1")
                        .withMarket("CNX")
                        .withInstrument("AUDUSD")
                        .withPrice(1.355)
                        .withQty(1000000)
                        .withSide(Side.BID).build())
                .build();


        MatcherAssert.assertThat(snapshot3, MarketDataSnapshotMatcher.matches(expectedSnapshot3));

    }


    @Test
    public void testWithNestedOrderBuilder() throws Exception {

        MarketDataIncrement increment1 = DefaultMarketDataIncrement.newBuilder()
                .withTriggerTimestamp(1)
                .withEventTimestamp(2)
                .addNewOrder()
                .withOrderId("1")
                .withMarket("CNX")
                .withInstrument("AUDUSD")
                .withPrice(1.345)
                .withQty(1000000)
                .withSide(Side.BID)
                .end()
                .build();

        MarketDataIncrement increment2 = DefaultMarketDataIncrement.newBuilder()
                .withTriggerTimestamp(3)
                .withEventTimestamp(4)
                .addNewOrder()
                .withOrderId("2")
                .withMarket("CNX")
                .withInstrument("AUDUSD")
                .withPrice(1.543)
                .withQty(1000000)
                .withSide(Side.ASK)
                .end()
                .build();

        MarketDataIncrement increment3 = DefaultMarketDataIncrement.newBuilder()
                .withTriggerTimestamp(5)
                .withEventTimestamp(6)
                .addReplaceOrder()
                .withOrderId("3")
                .withPrevOrderId("1")
                .withMarket("CNX")
                .withInstrument("AUDUSD")
                .withPrice(1.355)
                .withQty(1000000)
                .withSide(Side.BID)
                .end()
                .build();


        MarketDataSnapshotFunction function = new MarketDataSnapshotFunction();
        MarketDataSnapshot snapshot1 = function.apply(increment1);
        MarketDataSnapshot expectedSnapshot1 = DefaultMarketDataSnapshot.newBuilder()
                .withTriggerTimestamp(1)
                .withEventTimestamp(2)
                .addNewOrder()
                .withOrderId("1")
                .withMarket("CNX")
                .withInstrument("AUDUSD")
                .withPrice(1.345)
                .withQty(1000000)
                .withSide(Side.BID)
                .end()
                .build();

        MatcherAssert.assertThat(snapshot1, MarketDataSnapshotMatcher.matches(expectedSnapshot1));


        MarketDataSnapshot snapshot2 = function.apply(increment2);
        MarketDataSnapshot expectedSnapshot2 = DefaultMarketDataSnapshot.newBuilder()
                .withTriggerTimestamp(3)
                .withEventTimestamp(4)
                .addNewOrder()
                .withOrderId("1")
                .withMarket("CNX")
                .withInstrument("AUDUSD")
                .withPrice(1.345)
                .withQty(1000000)
                .withSide(Side.BID)
                .end()
                .addNewOrder()
                .withOrderId("2")
                .withMarket("CNX")
                .withInstrument("AUDUSD")
                .withPrice(1.543)
                .withQty(1000000)
                .withSide(Side.ASK)
                .end()
                .build();

        MatcherAssert.assertThat(snapshot2, MarketDataSnapshotMatcher.matches(expectedSnapshot2));


        MarketDataSnapshot snapshot3 = function.apply(increment3);
        MarketDataSnapshot expectedSnapshot3 = DefaultMarketDataSnapshot.newBuilder()
                .withTriggerTimestamp(5)
                .withEventTimestamp(6)
                .addNewOrder()
                .withOrderId("2")
                .withMarket("CNX")
                .withInstrument("AUDUSD")
                .withPrice(1.543)
                .withQty(1000000)
                .withSide(Side.ASK)
                .end()
                .addReplaceOrder()
                .withOrderId("3")
                .withPrevOrderId("1")
                .withMarket("CNX")
                .withInstrument("AUDUSD")
                .withPrice(1.355)
                .withQty(1000000)
                .withSide(Side.BID)
                .end()
                .build();


        MatcherAssert.assertThat(snapshot3, MarketDataSnapshotMatcher.matches(expectedSnapshot3));

    }

}