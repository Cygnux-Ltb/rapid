package io.cygnux.rapid.adapter.ctp;

import io.cygnux.rapid.adapter.ctp.param.ImmutableFtdcParams;
import io.cygnux.rapid.gateway.ctp.FtdcParams;
import io.cygnux.rapid.gateway.ctp.event.FtdcRspEvent;
import io.cygnux.rapid.gateway.ctp.event.FtdcRspType;
import io.cygnux.rapid.gateway.ctp.event.md.FtdcDepthMarketData;
import io.cygnux.rapid.gateway.ctp.event.trader.FtdcOrder;
import io.cygnux.rapid.gateway.ctp.event.trader.FtdcTrade;
import io.mercury.common.collections.queue.Queue;
import io.mercury.common.concurrent.queue.SingleConsumerQueueWithJCT;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import org.junit.Test;
import org.slf4j.Logger;

import java.util.HashMap;

public class CtpGatewayTest {

    private static final Logger log = Log4j2LoggerFactory.getLogger(CtpGatewayTest.class);

    // 标准CTP
//	private String TradeAddress = "tcp://180.168.146.187:10100";
//	private String MdAddress = "tcp://180.168.146.187:10110";

    private final String GatewayId = "simnow_test";

    @Test
    public void test() {

        var map = new HashMap<String, String>();

        // 7*24 CTP连通测试
        map.put("TradeAddr", "tcp://180.168.146.187:10130");
        map.put("MdAddr", "tcp://180.168.146.187:10131");
        map.put("BrokerId", "9999");
        map.put("InvestorId", "132796");
        map.put("UserId", "132796");
        map.put("AccountId", "132796");
        map.put("Password", "tc311911");
        map.put("TradingDay", "20200302");
        map.put("CurrencyId", "CNY");

        final FtdcParams config = new ImmutableFtdcParams(map);

        final Queue<FtdcRspEvent> queue = SingleConsumerQueueWithJCT.mpscQueue("Simnow-Handle-Queue").capacity(128)
                .process(msg -> {
                    switch (msg.getType()) {
                        case FtdcRspType.FTDC_DEPTH_MARKET_DATA -> {
                            FtdcDepthMarketData depthMarketData = msg.getDepthMarketData();
//                            log.info(
//                                    "Handle CThostFtdcDepthMarketDataField -> InstrumentID==[{}]  UpdateTime==[{}]  UpdateMillisec==[{}]  AskPrice1==[{}]  BidPrice1==[{}]",
//                                    depthMarketData.getInstrumentID(), depthMarketData.getUpdateTime(),
//                                    depthMarketData.getUpdateMillisec(), depthMarketData.getAskPrice1(),
//                                    depthMarketData.getBidPrice1());
                        }
                        case FTDC_ORDER -> {
                            FtdcOrder order = msg.getOrder();
                            log.info("Handle RtnOrder -> OrderRef==[{}]", order.OrderRef);
                        }
                        case FTDC_TRADE -> {
                            FtdcTrade trade = msg.getTrade();
                            log.info("Handle RtnTrade -> OrderRef==[{}]", trade.OrderRef);
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
