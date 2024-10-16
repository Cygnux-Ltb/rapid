/* Copyright (C) 2019 Interactive Brokers LLC. All rights reserved. This code is subject to the terms
 * and conditions of the IB API Non-Commercial License or the IB API Commercial License, as applicable. */

package com.ib.controller;

public enum LocationCode {

    BOND_US("BOND.US"),
    EFP("EFP"),
    FUT_ECBOT("FUT.ECBOT"),
    FUT_EU_BELFOX("FUT.EU.BELFOX"),
    FUT_EU_DTB("FUT.EU.DTB"),
    FUT_EU_FTA("FUT.EU.FTA"),
    FUT_EU_IDEM("FUT.EU.IDEM"),
    FUT_EU_LIFFE("FUT.EU.LIFFE"),
    FUT_EU_MEFFRV("FUT.EU.MEFFRV"),
    FUT_EU_MONEP("FUT.EU.MONEP"),
    FUT_EU("FUT.EU"),
    FUT_GLOBEX("FUT.GLOBEX"),
    FUT_HK_HKFE("FUT.HK.HKFE"),
    FUT_HK_JAPAN("FUT.HK.JAPAN"),
    FUT_HK_KSE("FUT.HK.KSE"),
    FUT_HK_NSE("FUT.HK.NSE"),
    FUT_HK_OSE_JPN("FUT.HK.OSE.JPN"),
    FUT_HK_SGX("FUT.HK.SGX"),
    FUT_HK_SNFE("FUT.HK.SNFE"),
    FUT_HK_TSE_JPN("FUT.HK.TSE.JPN"),
    FUT_HK("FUT.HK"),
    FUT_IPE("FUT.IPE"),
    FUT_NA_CDE("FUT.NA.CDE"),
    FUT_NA("FUT.NA"),
    FUT_NYBOT("FUT.NYBOT"),
    FUT_NYMEX("FUT.NYMEX"),
    FUT_NYSELIFFE("FUT.NYSELIFFE"),
    FUT_US("FUT.US"),
    IND_EU_BELFOX("IND.EU.BELFOX"),
    IND_EU_DTB("IND.EU.DTB"),
    IND_EU_FTA("IND.EU.FTA"),
    IND_EU_LIFFE("IND.EU.LIFFE"),
    IND_EU_MONEP("IND.EU.MONEP"),
    IND_EU("IND.EU"),
    IND_HK_HKFE("IND.HK.HKFE"),
    IND_HK_JAPAN("IND.HK.JAPAN"),
    IND_HK_KSE("IND.HK.KSE"),
    IND_HK_NSE("IND.HK.NSE"),
    IND_HK_OSE_JPN("IND.HK.OSE.JPN"),
    IND_HK_SGX("IND.HK.SGX"),
    IND_HK_SNFE("IND.HK.SNFE"),
    IND_HK_TSE_JPN("IND.HK.TSE.JPN"),
    IND_HK("IND.HK"),
    IND_US("IND.US"),
    SLB_AQS("SLB.AQS"),
    STK_AMEX("STK.AMEX"),
    STK_ARCA("STK.ARCA"),
    STK_EU_AEB("STK.EU.AEB"),
    STK_EU_BM("STK.EU.BM"),
    STK_EU_BVME("STK.EU.BVME"),
    STK_EU_EBS("STK.EU.EBS"),
    STK_EU_IBIS("STK.EU.IBIS"),
    STK_EU_IBIS_ETF("STK.EU.IBIS-ETF"),
    STK_EU_IBIS_EUSTARS("STK.EU.IBIS-EUSTARS"),
    STK_EU_IBIS_NEWX("STK.EU.IBIS-NEWX"),
    STK_EU_IBIS_USSTARS("STK.EU.IBIS-USSTARS"),
    STK_EU_IBIS_XETRA("STK.EU.IBIS-XETRA"),
    STK_EU_LSE("STK.EU.LSE"),
    STK_EU_SBF("STK.EU.SBF"),
    STK_EU_SBVM("STK.EU.SBVM"),
    STK_EU_SFB("STK.EU.SFB"),
    STK_EU_SWISS("STK.EU.SWISS"),
    STK_EU_VIRTX("STK.EU.VIRTX"),
    STK_EU("STK.EU"),
    STK_HK_ASX("STK.HK.ASX"),
    STK_HK_NSE("STK.HK.NSE"),
    STK_HK_SEHK("STK.HK.SEHK"),
    STK_HK_SGX("STK.HK.SGX"),
    STK_HK_TSE_JPN("STK.HK.TSE.JPN"),
    STK_HK("STK.HK"),
    STK_NA_CANADA("STK.NA.CANADA"),
    STK_NA_TSE("STK.NA.TSE"),
    STK_NA_VENTURE("STK.NA.VENTURE"),
    STK_NA("STK.NA"),
    STK_NASDAQ_NMS("STK.NASDAQ.NMS"),
    STK_NASDAQ_SCM("STK.NASDAQ.SCM"),
    STK_NASDAQ("STK.NASDAQ"),
    STK_NYSE("STK.NYSE"),
    STK_OTCBB("STK.OTCBB"),
    STK_PINK("STK.PINK"),
    STK_US_MAJOR("STK.US.MAJOR"),
    STK_US_MINOR("STK.US.MINOR"),
    STK_US("STK.US"),
    WAR_EU_ALL("WAR.EU.ALL");

    private final String code;

    LocationCode(final String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }
}
