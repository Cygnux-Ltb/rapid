package io.cygnux.rapid.adapter.ctp;

import io.mercury.common.state.AvailableTime;

import java.time.LocalTime;

public final class CtpAdapterAvailableTime implements AvailableTime {

    public static final CtpAdapterAvailableTime INSTANCE = new CtpAdapterAvailableTime();

    private CtpAdapterAvailableTime() {
    }

    @Override
    public boolean isAvailableAllTime() {
        // 非全时间交易
        return false;
    }

    @Override
    public LocalTime[] getStartTimes() {
        return new LocalTime[]{
                // 早盘开盘前10分钟
                LocalTime.of(8, 50),
                // 夜盘开盘前10分钟
                LocalTime.of(20, 50)
        };
    }

    @Override
    public LocalTime[] getStopTimes() {
        return new LocalTime[]{
                // 夜盘收盘后10分钟
                LocalTime.of(15, 10),
                // 夜盘收盘后10分钟
                LocalTime.of(2, 40)};
    }

}
