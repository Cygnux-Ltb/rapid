package io.rapid.adaptor.ctp.gateway;

import io.rapid.adaptor.ctp.CtpConfig;
import io.rapid.adaptor.ctp.gateway.event.FtdcEvent;
import io.rapid.adaptor.ctp.gateway.event.received.md.FtdcDepthMarketData;
import io.rapid.adaptor.ctp.gateway.event.received.trader.FtdcOrder;
import io.rapid.adaptor.ctp.gateway.event.received.trader.FtdcTrade;
import io.mercury.common.collections.queue.Queue;
import io.mercury.common.concurrent.queue.ScQueueWithJCT;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import org.junit.Test;
import org.slf4j.Logger;

public class CtpGatewayTest {

    private static final Logger log = Log4j2LoggerFactory.getLogger(CtpGatewayTest.class);

    // 标准CTP
//	private String TradeAddress = "tcp://180.168.146.187:10100";
//	private String MdAddress = "tcp://180.168.146.187:10110";

    // 7*24 CTP连通测试
    private String TradeAddr = "tcp://180.168.146.187:10130";
    private String MdAddr = "tcp://180.168.146.187:10131";

    private String BrokerId = "9999";
    private String InvestorId = "132796";
    private String UserId = "132796";
    private String AccountId = "132796";
    private String Password = "tc311911";

    private String TradingDay = "20200302";
    private String CurrencyId = "CNY";

    private String GatewayId = "simnow_test";

    @Test
    public void test() {

        final CtpConfig config = new CtpConfig().setTraderAddr(TradeAddr).setMdAddr(MdAddr).setBrokerId(BrokerId)
                .setInvestorId(InvestorId).setUserId(UserId).setAccountId(AccountId).setPassword(Password)
                .setTradingDay(TradingDay).setCurrencyId(CurrencyId);

        final Queue<FtdcEvent> queue = ScQueueWithJCT.mpscQueue("Simnow-Handle-Queue").capacity(128)
                .process(msg -> {
                    switch (msg.getType()) {
                        case DepthMarketData -> {
                            FtdcDepthMarketData depthMarketData = msg.getDepthMarketData();
                            log.info(
                                    "Handle CThostFtdcDepthMarketDataField -> InstrumentID==[{}]  UpdateTime==[{}]  UpdateMillisec==[{}]  AskPrice1==[{}]  BidPrice1==[{}]",
                                    depthMarketData.getInstrumentID(), depthMarketData.getUpdateTime(),
                                    depthMarketData.getUpdateMillisec(), depthMarketData.getAskPrice1(),
                                    depthMarketData.getBidPrice1());
                        }
                        case Order -> {
                            FtdcOrder order = msg.getOrder();
                            log.info("Handle RtnOrder -> OrderRef==[{}]", order.getOrderRef());
                        }
                        case Trade -> {
                            FtdcTrade trade = msg.getTrade();
                            log.info("Handle RtnTrade -> OrderRef==[{}]", trade.getOrderRef());
                        }
                        default -> {
                        }
                    }
                });

//        try (CtpGateway gateway = new CtpGateway(GatewayId, config, ALL, queue::enqueue)) {
//            gateway.startup();
//            gateway.SubscribeMarketData(new String[]{"rb2010"});
//            ThreadSupport.join();
//        } catch (IOException e) {
//            log.error("IOException -> {}", e.getMessage(), e);
//        }

    }

}
