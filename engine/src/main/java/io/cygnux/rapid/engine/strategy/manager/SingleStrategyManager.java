package io.cygnux.rapid.engine.strategy.manager;

import io.cygnux.console.service.StrategyService;
import io.cygnux.rapid.core.account.SubAccount;
import io.cygnux.rapid.core.event.received.OrderReport;
import io.cygnux.rapid.core.instrument.Instrument;
import io.cygnux.rapid.core.order.OrderKeeper;
import io.cygnux.rapid.core.strategy.Strategy;
import io.cygnux.rapid.core.strategy.StrategyEvent;
import io.cygnux.rapid.core.manager.StrategyManager;
import io.cygnux.rapid.core.strategy.StrategyParam;
import io.cygnux.rapid.engine.status.ApplicationStatus;
import io.mercury.common.collections.MutableSets;
import io.mercury.common.lang.Throws;
import io.mercury.common.util.ResourceUtil;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;
import org.slf4j.Logger;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.Set;

import static io.mercury.common.log4j2.Log4j2LoggerFactory.getLogger;

/**
 * 单策略调度器, 直接调用策略回调函数, 没有异步操作, 最简单并且速度最快的调度器
 *
 * @author yellow013
 */
@Primary
@Service("singleStrategyManager")
public class SingleStrategyManager implements StrategyManager {

    private static final Logger log = getLogger(SingleStrategyManager.class);

    @Resource(name = "inHeap")
    private OrderKeeper orderKeeper;

    @Resource
    private ApplicationStatus applicationStatus;

    @Resource
    private StrategyService strategyService;

    /**
     * Only one strategy
     */
    @Resource
    private Strategy strategy;

    @PostConstruct
    private void init() {
        this.strategy.enable();
    }

    @Override
    public void addStrategy(Strategy strategy) {
        Throws.unsupportedOperation("");
    }

    @Override
    public Strategy getStrategy(int strategyId) {
        return null;
    }

    @Override
    public void subscribeInstrument(Strategy strategy, Instrument... instruments) {

    }

    @Override
    public MutableIntObjectMap<Strategy> getStrategies() {
        return null;
    }

    @Override
    public MutableIntObjectMap<Instrument> getInstruments() {
        return null;
    }

    @Override
    public void onEvent(StrategyEvent event) {

    }

    @Override
    public Set<StrategyParam> getParams(int strategyId) {
        return strategyService.getStrategyParam(applicationStatus.getUserid(), strategyId)
                .stream()
                .map(resp -> new StrategyParam()
                        .setAlgoId(resp.getAlgoId())
                        .setAlgoName(resp.getAlgoDisplayName())
                        .setParamName(resp.getParamName())
                        .setParamType(resp.getParamType())
                        .setParamValue(resp.getParamValue())
                ).collect(MutableSets.newCollector());
    }

    @Override
    public SubAccount getSubAccount(int strategyId) {
        return null;
    }


    public void onOrderEvent(@Nonnull OrderReport event) {
        var order = orderKeeper.onOrderReport(event);
        // 调用策略实现的订单回调函数
        strategy.onOrder(order);
    }

//    @Override
//    public void onAdaptorEvent(@Nonnull AdaptorReport event) {
//        log.error("On Adaptor event -> {}", event);
//
//    }

    public void close() throws IOException {
        ResourceUtil.closeIgnoreException(strategy);
        log.info("Strategy [{}] closed", strategy.getStrategyName());
    }


}
