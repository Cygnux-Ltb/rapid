package io.rapid.core.mkd;

import io.rapid.core.instrument.Instrument;
import io.mercury.common.datetime.Timestamp;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.Serial;

public final class DepthMarketData extends BasicMarketData {

    public DepthMarketData(@Nonnull Instrument instrument, long epochMillis, int depth) {
        super(instrument, epochMillis, depth);
    }

    public DepthMarketData(@Nonnull Instrument instrument, long epochMillis, @Nullable Timestamp timestamp, int depth) {
        super(instrument, epochMillis, timestamp, depth);
    }

    public double getBidPrice(int level) {
        return bidPrices[level];
    }

    public int getBidVolume(int level) {
        return bidVolumes[level];
    }

    public double getAskPrice(int level) {
        return askPrices[level];
    }

    public int getAskVolume(int level) {
        return askVolumes[level];
    }

    public static class QuoteLevelOverflowException extends RuntimeException {
        @Serial
        private static final long serialVersionUID = 2602076635184902103L;

        public QuoteLevelOverflowException(String msg) {
            super(msg);
        }

    }

}
