package io.cygnux.rapid.engine.status;

import io.mercury.common.collections.MutableMaps;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.eclipse.collections.api.map.primitive.ImmutableIntObjectMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Service
public final class ApplicationStatus {

    @Value("${application.userid:1}")
    private int userid;

    private final AtomicInteger currentStatus = new AtomicInteger(AppStatus.UNKNOWN.getCode());

    private ApplicationStatus() {
    }

    public AppStatus getStatus() {
        return AppStatus.valueOf(currentStatus.get());
    }

    public void setInitialization() {
        currentStatus.set(AppStatus.INITIALIZATION.getCode());
    }

    public void setOnline() {
        currentStatus.set(AppStatus.ONLINE.getCode());
    }

    public void setOffline() {
        currentStatus.set(AppStatus.OFFLINE.getCode());
    }

    public void setUnknown() {
        currentStatus.set(AppStatus.UNKNOWN.getCode());
    }

    @Getter
    @RequiredArgsConstructor
    public enum AppStatus {

        INITIALIZATION(0),

        ONLINE(1),

        OFFLINE(2),

        UNKNOWN(-1);

        private final int code;

        private static final ImmutableIntObjectMap<AppStatus> Map =
                MutableMaps.newIntObjectMap(AppStatus::getCode, AppStatus.values()).toImmutable();

        public int getCode() {
            return code;
        }

        public static AppStatus valueOf(int code) {
            var obj = Map.get(code);
            return obj == null ? UNKNOWN : obj;
        }

    }

    public static void main(String[] args) {

        System.out.println(Integer.MAX_VALUE);
        System.out.println(Long.MAX_VALUE);

        long zoned2018_1 = ZonedDateTime.of(LocalDate.of(2018, 11, 8),
                        LocalTime.MIN, ZoneId.systemDefault())
                .toInstant().getEpochSecond();

        System.out.println(zoned2018_1);

        long epochMilli = Instant.now().getEpochSecond();

        System.out.println(epochMilli);
        System.out.println(epochMilli - zoned2018_1);

        System.out.println();

    }

}
