package io.rapid.adaptor.ctp;

import io.rapid.core.adaptor.AdaptorAvailableTime;

import java.time.LocalTime;

import static java.time.LocalTime.of;

public class CtpAdaptorAvailableTime implements AdaptorAvailableTime {

    public static final CtpAdaptorAvailableTime INSTANCE = new CtpAdaptorAvailableTime();

    @Override
    public boolean isRunningAllTime() {
        return false;
    }

    @Override
    public LocalTime[] getStartTimes() {
        return new LocalTime[]{of(8, 50), of(22, 50)};
    }

    @Override
    public LocalTime[] getEndTimes() {
        return new LocalTime[]{of(15, 10), of(2, 40)};
    }

}
