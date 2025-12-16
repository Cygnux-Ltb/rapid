package io.rapid.adaptor.ctp.gateway;

import org.rationalityfrontline.jctp.CThostFtdcDepthMarketDataField;
import org.rationalityfrontline.jctp.CThostFtdcMdApi;
import org.rationalityfrontline.jctp.CThostFtdcMdSpi;
import org.rationalityfrontline.jctp.CThostFtdcReqUserLoginField;
import org.rationalityfrontline.jctp.CThostFtdcRspInfoField;
import org.rationalityfrontline.jctp.CThostFtdcRspUserLoginField;

public class MdSpiImpl extends CThostFtdcMdSpi {

    final static String m_BrokerId = "9999";
    final static String m_UserId = "221873";
    final static String m_InvestorId = "221873";
    final static String m_PassWord = "asd@123456";
    final static String m_TradingDay = "20240906";
    final static String m_AccountId = "221873";
    final static String m_CurrencyId = "CNY";

    private final CThostFtdcMdApi m_mdapi;

    MdSpiImpl(CThostFtdcMdApi mdapi) {
        m_mdapi = mdapi;
    }

    public void OnFrontConnected() {
        System.out.println("On Front Connected");
        CThostFtdcReqUserLoginField field = new CThostFtdcReqUserLoginField();
        field.setBrokerID(m_BrokerId);
        field.setUserID(m_UserId);
        field.setPassword(m_PassWord);
        m_mdapi.ReqUserLogin(field, 0);

    }

    public void OnRspUserLogin(CThostFtdcRspUserLoginField pRspUserLogin,
                               CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean bIsLast) {
        if (pRspUserLogin != null) {
            System.out.printf("Brokerid[%s]\n", pRspUserLogin.getBrokerID());
        }
        if (pRspInfo != null) {
            System.out.printf("OnRspUserLogin ErrorMsg[%s]\n", pRspInfo.getErrorMsg());
        }
        String[] instruementid = new String[1];
        //订阅合约号修改这里，如果运行成功没收到行情，参见如下解决
        //https://blog.csdn.net/pjjing/article/details/100532276
        instruementid[0] = "au1912";
        m_mdapi.SubscribeMarketData(instruementid);
    }

    public void OnRtnDepthMarketData(CThostFtdcDepthMarketDataField pDepthMarketData) {
        if (pDepthMarketData != null) {
            System.out.printf("AskPrice1[%f]BidPrice1[%f]\n",
                    pDepthMarketData.getAskPrice1(), pDepthMarketData.getBidPrice1());
        } else {
            System.out.print("NULL obj\n");
        }
    }

    public static class MdApiDemo {
        static {
            System.loadLibrary("thostmduserapi_se");
            System.out.println("thostmduserapi_se loaded");
            System.loadLibrary("thostmduserapi_wrap");
            System.out.println("thostmduserapi_wrap loaded");
        }

        //这是7*24模拟环境，正式生产环境见群文档
        final static String ctp1_MdAddress = "tcp://180.168.146.187:10131";

        public static void main(String[] args) {
            // TODO Auto-generated method stub
            CThostFtdcMdApi mdApi = CThostFtdcMdApi.CreateFtdcMdApi();
            MdSpiImpl pMdspiImpl = new MdSpiImpl(mdApi);
            mdApi.RegisterSpi(pMdspiImpl);
            mdApi.RegisterFront(ctp1_MdAddress);
            mdApi.Init();
            mdApi.Join();
        }
    }

}

