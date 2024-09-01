package io.rapid.core.adaptor;

public interface Adaptor extends TraderAdaptor, MarketDataFeed {

    // ############################## 状态相关 ############################## //

    @Override
    default String getFeedId() {
        return getAdaptorId();
    }

}
