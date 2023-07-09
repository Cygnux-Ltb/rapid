package io.cygnuxltb.jcts.core.trader.adaptor;

import java.time.LocalTime;

public interface AdaptorType {

    boolean isRunningAllTime();

    LocalTime[] getStartTimes();

    LocalTime[] getEndTimes();

}
