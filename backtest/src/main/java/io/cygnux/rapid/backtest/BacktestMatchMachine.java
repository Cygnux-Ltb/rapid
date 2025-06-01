package io.cygnux.rapid.backtest;

import io.cygnux.rapid.core.event.InboundHandler;
import io.cygnux.rapid.core.event.inbound.MarketDataReport;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("backtestMatchMachine")
public final class BacktestMatchMachine {

    @Value("${marketdata.file.path:~/mkt}")
    private String marketDataPath;

    @Resource
    private InboundHandler inboundHandler;

    private MutableIntObjectMap<MarketDataReport> marketDataSet;

    private int index = 0;

    @PostConstruct
    private void init(){
        System.out.println("BacktestMatchMachine init -> " + marketDataPath);
    }


    public void doNext(){
        MarketDataReport removed = marketDataSet.remove(index);
        index++;
        inboundHandler.handleMarketDataReport(removed);
    }

}
