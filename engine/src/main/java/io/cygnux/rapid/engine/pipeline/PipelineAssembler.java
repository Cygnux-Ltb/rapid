package io.cygnux.rapid.engine.pipeline;

import com.lmax.disruptor.BusySpinWaitStrategy;
import com.lmax.disruptor.ExceptionHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import io.cygnux.rapid.core.manager.AccountManager;
import io.cygnux.rapid.core.manager.AdapterManager;
import io.cygnux.rapid.core.manager.MarketDataManager;
import io.cygnux.rapid.core.manager.OrderManager;
import io.cygnux.rapid.core.manager.PositionManager;
import io.cygnux.rapid.core.manager.StrategyManager;
import io.cygnux.rapid.core.strategy.StrategySignalAggregator;
import io.cygnux.rapid.core.event.SharedEvent;
import io.cygnux.rapid.core.event.SharedEventFeeder;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Component
@Order
public class PipelineAssembler {

    private static final Logger log = Log4j2LoggerFactory.getLogger(PipelineAssembler.class);

    @Resource
    private AdapterManager adapterManager;

    @Resource
    private MarketDataManager marketDataManager;

    @Resource
    private AccountManager accountManager;

    @Resource
    private OrderManager orderManager;

    @Resource
    private PositionManager positionManager;

    @Resource
    private StrategyManager strategyManager;

    @Resource
    private StrategySignalAggregator signalHandler;

    @Resource
    private SharedEventFeeder eventFeeder;

    private Disruptor<SharedEvent> disruptor;

    @PostConstruct
    public void assembly() {
        disruptor = new Disruptor<>(SharedEvent.EVENT_FACTORY, 1024,
                (Runnable runnable) -> Thread.ofPlatform().name("pipeline-worker").start(runnable),
                ProducerType.SINGLE, new BusySpinWaitStrategy());
        disruptor.setDefaultExceptionHandler(new InternalExceptionHandler());
        // 顺序处理链
        disruptor
                /// 1. 管理器同时更新状态
                .handleEventsWith(
                        adapterManager,
                        marketDataManager,
                        accountManager,
                        orderManager,
                        positionManager)
                /// 2. 策略处理
                .then(strategyManager.strategies())
                /// 3. 信号处理
                .then(signalHandler);


        RingBuffer<SharedEvent> ringBuffer = disruptor.getRingBuffer();

        // 模拟行情驱动
        for (int i = 0; i < 100; i++) {
            long seq = ringBuffer.next();
            try {

            } finally {
                ringBuffer.publish(seq);
            }
        }
        disruptor.start();
    }

    // 异常处理器
    static class InternalExceptionHandler implements ExceptionHandler<SharedEvent> {
        public void handleEventException(Throwable e, long seq, SharedEvent event) {
            log.error("handle event has exception: {}, seq: {}, event: {}", e.getMessage(), seq, event, e);
        }

        public void handleOnStartException(Throwable e) {
            log.error("start has exception: {}", e.getMessage(), e);
        }

        public void handleOnShutdownException(Throwable e) {
            log.error("shutdown has exception: {}", e.getMessage(), e);
        }
    }

    @PreDestroy
    public void destroy() {
        if (disruptor != null) {
            disruptor.shutdown();
        }
    }

}
