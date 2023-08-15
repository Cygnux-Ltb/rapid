package io.cygnuxltb.jcts.core.mkd.impl;


import io.cygnuxltb.jcts.core.mkd.api.MarketDataDeleteOrder;
import io.cygnuxltb.jcts.core.mkd.api.MarketDataEvent;
import io.cygnuxltb.jcts.core.mkd.api.MarketDataIncrement;
import io.cygnuxltb.jcts.core.mkd.api.MarketDataMessage;
import io.cygnuxltb.jcts.core.mkd.api.MarketDataNewOrder;
import io.cygnuxltb.jcts.core.mkd.api.MarketDataReplaceOrder;
import io.cygnuxltb.jcts.core.mkd.api.MarketDataSnapshot;
import io.cygnuxltb.jcts.core.mkd.api.Visitor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class MarketDataSnapshotFunction implements Function<MarketDataMessage, MarketDataSnapshot> {
    public static final Visitor<Map<String, MarketDataEvent>, Map<String, MarketDataEvent>>
            EVENTS_VISITOR = new Visitor<>() {

        @Override
        public Map<String, MarketDataEvent> visit(final MarketDataNewOrder event, final Map<String, MarketDataEvent> events) {
            MarketDataEvent existing = events.put(event.getOrderId(), event);
            return events;
        }

        @Override
        public Map<String, MarketDataEvent> visit(final MarketDataReplaceOrder event, final Map<String, MarketDataEvent> events) {
            MarketDataEvent existing = events.remove(event.getPrevOrderId());
            MarketDataEvent existingNew = events.put(event.getOrderId(), event);
            return events;
        }

        @Override
        public Map<String, MarketDataEvent> visit(final MarketDataDeleteOrder event, final Map<String, MarketDataEvent> events) {
            MarketDataEvent existing = events.remove(event.getOrderId());
            return events;
        }

        @Override
        public Map<String, MarketDataEvent> visit(final MarketDataSnapshot message, final Map<String, MarketDataEvent> events) {
            events.clear();
            processEvents(message.getEvents(), events);
            return events;
        }

        @Override
        public Map<String, MarketDataEvent> visit(final MarketDataIncrement message, final Map<String, MarketDataEvent> events) {
            processEvents(message.getEvents(), events);
            return events;
        }

        private void processEvents(final List<? extends MarketDataEvent> messageEvents, final Map<String, MarketDataEvent> events) {
            for (MarketDataEvent event : messageEvents) {
                event.accept(this, events);
            }
        }

    };

    private final Map<String, MarketDataEvent> bookEvents = new HashMap<>();

    public MarketDataSnapshot apply(MarketDataMessage message) {
        message.accept(EVENTS_VISITOR, bookEvents);
        return DefaultMarketDataSnapshot.newBuilder()
                .withEventTimestamp(System.nanoTime())
                .withTriggerTimestamp(message.getTriggerTimestamp())
                .withEvents(bookEvents.values())
                .build();
    }

}
