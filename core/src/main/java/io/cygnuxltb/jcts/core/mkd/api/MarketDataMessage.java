package io.cygnuxltb.jcts.core.mkd.api;

import io.horizon.market.data.api.MarketDataEvent;
import io.horizon.market.data.api.Visitable;

import java.util.List;

public interface MarketDataMessage extends Visitable {

    long getTriggerTimestamp();

    long getEventTimestamp();

    List<? extends MarketDataEvent> getEvents();

}
