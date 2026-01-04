package io.cygnux.rapid.adapter.ctp;

import static io.mercury.common.util.CharUtil.decimalCharToInt;

public class TimeTest {

    public static void main(String[] args) {

        String time = "12:54:30";

        System.out.println(time);

        int i = decimalCharToInt(time.charAt(0)) * 100000
                + decimalCharToInt(time.charAt(1)) * 10000
                + decimalCharToInt(time.charAt(3)) * 1000
                + decimalCharToInt(time.charAt(4)) * 100
                + decimalCharToInt(time.charAt(6)) * 10
                + decimalCharToInt(time.charAt(7));

        System.out.println(decimalCharToInt(time.charAt(0)) * 100000);
        System.out.println(decimalCharToInt(time.charAt(1)) * 10000);
        System.out.println(decimalCharToInt(time.charAt(3)) * 1000);
        System.out.println(decimalCharToInt(time.charAt(4)) * 100);
        System.out.println(decimalCharToInt(time.charAt(6)) * 10);
        System.out.println(decimalCharToInt(time.charAt(7)));

        System.out.println(i);

    }

}
