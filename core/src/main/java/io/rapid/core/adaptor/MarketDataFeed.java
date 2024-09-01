package io.rapid.core.upstream;

import io.mercury.common.state.AvailableTime;
import io.mercury.common.state.Enableable;
import io.mercury.common.state.StartupException;
import io.rapid.core.instrument.Instrument;

import javax.annotation.Nonnull;
import java.io.IOException;

public interface MarketDataFeed extends AutoCloseable, Enableable {

    // ############################## 状态相关 ############################## //

    /**
     * @return Feed ID
     */
    String getFeedId();

    /**
     * 返回[可用时间]对象
     *
     * @return AvailableTime
     */
    AvailableTime getAvailableTime();

    /**
     * Adaptor 启动函数
     *
     * @return boolean
     */
    boolean startup() throws IOException, IllegalStateException, StartupException;

    // ############################## 行情相关 ############################## //

    /**
     * 订阅行情
     *
     * @param instruments Instrument[]
     */
    boolean subscribeMarketData(@Nonnull Instrument... instruments);

}
