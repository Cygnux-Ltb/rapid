package io.cygnux.rapid.adapter.jctp;

import org.rationalityfrontline.jctp.CThostFtdcTraderApi;
import org.rationalityfrontline.jctp.jctpJNI;

public class JctpTest {

    public static void main(String[] args) {
        if (jctpJNI.libraryLoaded()) {
            System.out.println(CThostFtdcTraderApi.GetApiVersion());
            // do something with CTP, here we print its sdk version
            // release native gc root in jni, jctp will be unavailable after doing this
            jctpJNI.release();
        }
    }

}
