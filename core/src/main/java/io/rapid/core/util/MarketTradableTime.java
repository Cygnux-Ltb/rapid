package io.rapid.core.util;

import org.slf4j.Logger;

import java.time.LocalDateTime;

import static io.mercury.common.datetime.pattern.DateTimePattern.YYYY_MM_DD_HH_MM_SS_SSSSSS;
import static io.mercury.common.log4j2.Log4j2LoggerFactory.getLogger;
import static io.mercury.common.thread.ScheduleTaskExecutor.singleThreadSchedule;
import static java.lang.System.exit;
import static java.time.LocalDateTime.now;

public final class MarketTradableTime {

    private static final Logger log = getLogger(MarketTradableTime.class);

    public static void registerCloseTime(LocalDateTime datetime) {
        log.info("Register next close time -> [{}]", YYYY_MM_DD_HH_MM_SS_SSSSSS.fmt(datetime));
        singleThreadSchedule(datetime, () -> {
            log.info("Execution close function, current time -> [{}]",
                    YYYY_MM_DD_HH_MM_SS_SSSSSS.fmt(now()));
            exit(0);
        });
    }

}
