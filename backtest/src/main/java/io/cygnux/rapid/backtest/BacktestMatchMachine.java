package io.cygnux.rapid.backtest;

import io.rapid.core.event.InboundHandler;
import io.rapid.core.event.inbound.MarketDataReport;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.eclipse.collections.api.set.MutableSet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("backtestMatchMachine")
public final class BacktestMatchMachine {

    @Value("${marketdata.file.path:~/mkt}")
    private String marketDataPath;

    @Resource
    private InboundHandler inboundHandler;

    private MutableSet<MarketDataReport> marketDataSet;

    @PostConstruct
    private void init(){
        System.out.println("BacktestMatchMachine init -> " + marketDataPath);
    }


    public MarketDataReport doNext(){

        return null;
    }

}
