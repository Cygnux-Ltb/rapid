package io.cygnuxltb.jcts.core.adaptor;

import java.time.LocalTime;

public interface AdaptorAvailableTime {

    boolean isRunningAllTime();

    LocalTime[] getStartTimes();

    LocalTime[] getEndTimes();

}
