package io.rapid.core.adaptor;

import java.time.LocalTime;

public interface AdaptorAvailableTime {

    boolean isRunningAllTime();

    LocalTime[] getStartTimes();

    LocalTime[] getEndTimes();

}
