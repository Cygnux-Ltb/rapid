package io.cygnux.rapid.core.instrument;

import io.cygnux.rapid.core.instrument.futures.ChinaFutures;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


public class ChinaFuturesTest {

    @Test
    public void test() {
        LocalDate date = LocalDate.now();
        LocalTime time_0_0 = LocalTime.of(0, 0);
        for (int i = 5; ; i += 5) {
            LocalTime time = time_0_0.plusMinutes(i);
            LocalDateTime dateTime = ChinaFutures.nextCloseTime(LocalDateTime.of(date, time));
            System.out.println(time + " -> " + dateTime);
            if (time.equals(LocalTime.MIN)) {
                break;
            }
        }
    }

}
