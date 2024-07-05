package io.rapid.core.mkd.copy;

import java.util.List;

public interface MarketDataMessage extends Visitable {

    long getTriggerTimestamp();

    long getEventTimestamp();

    List<? extends MarketDataEvent> getEvents();

}
