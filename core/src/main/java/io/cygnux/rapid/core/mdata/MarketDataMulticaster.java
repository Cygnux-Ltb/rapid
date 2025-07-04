package io.cygnux.rapid.core.mdata;

public final class MarketDataMulticaster<In> {
}
//        extends RunnableComponent implements Closeable {
//
//    private static final Logger log = getLogger(MarketDataMulticaster.class);
//
//    private RingMulticaster<FastMarketData, In> multicaster;
//
//    private final Builder<FastMarketData, In> builder;
//
//    public MarketDataMulticaster(String adaptorName,
//                                 @Nonnull EventFactory<FastMarketData> eventFactory,
//                                 @Nonnull EventTranslatorOneArg<FastMarketData, In> translator) {
//        this.builder = RingMulticaster
//                // 单生产者广播器
//                .withSingleProducer(eventFactory, translator)
//                // 设置缓冲区大小
//                .size(64)
//                // 设置AdaptorName加后缀
//                .setName(adaptorName + "-md-multicaster")
//                // 设置启动模式
//                .setStartMode(manual())
//                // 设置使用自旋等待策略
//                .setWaitStrategy(BusySpin);
//    }
//
//    public void publish(In in) {
//        multicaster.publishEvent(in);
//    }
//
//    /**
//     * @param handler MarketDataHandler<M>
//     */
//    public void addMarketDataHandler(MarketDataHandler handler) {
//        addEventHandler((FastMarketData event, long sequence, boolean endOfBatch) -> {
//            try {
//                handler.onMarketData(event);
//            } catch (Exception e) {
//                log.error("MarketDataHandler throw {}, MarketData -> {}",
//                        e.getClass().getSimpleName(), event, e);
//            }
//        });
//    }
//
//    /**
//     * @param handler EventHandler<M>
//     */
//    public void addEventHandler(EventHandler<FastMarketData> handler) {
//        builder.addHandler(handler);
//    }
//
//    public void startup() {
//        super.start();
//    }
//
//    @Override
//    public void close() throws IOException {
//        super.stop();
//    }
//
//    @Override
//    protected void start0() {
//        multicaster = builder.create();
//        multicaster.start();
//    }
//
//    @Override
//    protected void stop0() {
//        multicaster.stop();
//    }
//
//}
