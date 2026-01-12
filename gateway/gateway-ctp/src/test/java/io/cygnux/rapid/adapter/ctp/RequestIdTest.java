package io.cygnux.rapid.adapter.ctp;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class RequestIdTest {

    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);


        LocalTime time = LocalTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hmmssSSS");

        System.out.println(Integer.parseInt(formatter.format(time)) * 10 );

    }
}
