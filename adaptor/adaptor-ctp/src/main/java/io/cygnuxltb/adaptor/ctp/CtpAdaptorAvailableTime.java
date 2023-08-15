package io.cygnuxltb.adaptor.ctp;

import io.cygnuxltb.jcts.core.adaptor.AdaptorAvailableTime;

import java.time.LocalTime;

public class CtpAdaptorType implements AdaptorAvailableTime {

    public static final AdaptorType INSTANCE = new CtpAdaptorType();

    @Override
    public boolean isRunningAllTime() {
        return false;
    }

    @Override
    public LocalTime[] getStartTimes() {
        return new LocalTime[]{
                LocalTime.of(8, 55),
                LocalTime.of(22, 55)
        };
    }

    @Override
    public LocalTime[] getEndTimes() {
        return new LocalTime[]{
                LocalTime.of(15, 10),
                LocalTime.of(2, 40)
        };
    }

}
