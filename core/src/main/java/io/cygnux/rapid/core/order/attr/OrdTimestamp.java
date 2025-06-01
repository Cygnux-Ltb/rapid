package io.cygnux.rapid.core.order.attr;

import io.mercury.common.epoch.HighResolutionEpoch;
import io.mercury.common.serialization.specific.JsonSerializable;
import lombok.Getter;

import javax.annotation.Nonnull;

/**
 * 时间单位为Epoch微秒
 *
 * @author yellow013
 */
public final class OrdTimestamp implements JsonSerializable {

    @Getter
    private final long generateTime;

    @Getter
    private long sendTime;

    @Getter
    private long firstReportTime;

    @Getter
    private long finishTime;

    private OrdTimestamp() {
        this.generateTime = HighResolutionEpoch.micros();
    }

    /**
     * 初始化订单生成时间
     */
    public static OrdTimestamp now() {
        return new OrdTimestamp();
    }

    /**
     * 添加发送时间
     *
     * @return OrdTimestamp
     */
    public OrdTimestamp fillSendTime() {
        this.sendTime = HighResolutionEpoch.micros();
        return this;
    }

    /**
     * 添加首次收到订单回报的时间
     *
     * @return OrdTimestamp
     */
    public OrdTimestamp fillFirstReportTime() {
        this.firstReportTime = HighResolutionEpoch.micros();
        return this;
    }

    /**
     * 添加最终完成时间
     *
     * @return OrdTimestamp
     */
    public OrdTimestamp fillFinishTime() {
        this.finishTime = HighResolutionEpoch.micros();
        return this;
    }

    /**
     * 生成时间到发送时间
     *
     * @return long
     */
    public long durationByGenerateToSend() {
        if (sendTime == 0)
            return -1;
        return sendTime - generateTime;
    }

    /**
     * 生成时间到第一次回报时间
     *
     * @return long
     */
    public long durationByGenerateToFirstReport() {
        if (firstReportTime == 0)
            return -1;
        return firstReportTime - generateTime;
    }

    /**
     * 生成时间到完成时间
     *
     * @return long
     */
    public long durationByGenerateToFinish() {
        if (finishTime == 0)
            return -1;
        return finishTime - generateTime;
    }

    /**
     * 发送时间到第一次回报时间
     *
     * @return long
     */
    public long durationBySendToFirstReport() {
        if (sendTime == 0 || firstReportTime == 0)
            return -1;
        return firstReportTime - sendTime;
    }

    /**
     * 发送时间到完成时间
     *
     * @return long
     */
    public long durationBySendToFinish() {
        if (sendTime == 0 || finishTime == 0)
            return -1;
        return finishTime - sendTime;
    }

    private static final String GenerateTimeTitle = "{\"generateTime\" : ";
    private static final String SendTimeTitle = ", \"sendTime\" : ";
    private static final String FirstReportTimeTitle = ", \"firstReportTime\" : ";
    private static final String FinishTimeTitle = ", \"finishTime\" : ";
    private static final String End = "}";

    @Override
    public String toString() {
        return GenerateTimeTitle + generateTime
                + SendTimeTitle + sendTime
                + FirstReportTimeTitle + firstReportTime
                + FinishTimeTitle + finishTime
                + End;
    }

    @Nonnull
    @Override
    public String toJson() {
        return toString();
    }

}
