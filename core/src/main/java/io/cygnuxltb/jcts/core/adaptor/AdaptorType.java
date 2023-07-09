package io.cygnuxltb.jcts.core.adaptor;

import java.time.LocalTime;

public interface AdaptorType {

    boolean isRunningAllTime();

    LocalTime[] getStartTimes();

    LocalTime[] getEndTimes();

}
