package io.rapid.engine.strategy;

import io.mercury.common.annotation.AbstractFunction;
import io.mercury.common.collections.MutableMaps;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.mercury.common.param.Params;
import io.mercury.common.state.EnableableComponent;
import io.rapid.core.account.AccountManager;
import io.rapid.core.account.SubAccount;
import io.rapid.core.event.enums.OrdType;
import io.rapid.core.event.enums.TrdAction;
import io.rapid.core.event.enums.TrdDirection;
import io.rapid.core.instrument.Instrument;
import io.rapid.core.instrument.InstrumentKeeper;
import io.rapid.core.mdata.MarketDataManager;
import io.rapid.core.mdata.MarketDataSnapshot;
import io.rapid.core.mdata.SavedMarketData;
import io.rapid.core.order.OrdSysIdAllocator;
import io.rapid.core.order.OrdSysIdAllocatorKeeper;
import io.rapid.core.order.impl.Order;
import io.rapid.core.risk.CircuitBreaker;
import io.rapid.core.strategy.Strategy;
import io.rapid.core.strategy.StrategyEvent;
import io.rapid.core.strategy.StrategyManager;
import io.rapid.core.strategy.StrategySignal;
import io.rapid.core.strategy.StrategySignalHandler;
import io.rapid.engine.position.PositionKeeper;
import io.rapid.engine.trader.OrderKeeper;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.Getter;
import org.eclipse.collections.api.map.primitive.ImmutableIntObjectMap;
import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;
import org.eclipse.collections.api.map.primitive.MutableLongObjectMap;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

import static io.cygnuxltb.console.beans.ValueLimitation.MAX_STRATEGY_ID;
import static io.cygnuxltb.console.beans.ValueLimitation.MIN_STRATEGY_ID;
import static io.mercury.common.lang.Asserter.atWithinRange;
import static io.mercury.common.lang.Asserter.nonEmpty;
import static io.mercury.common.lang.Asserter.nonNull;
import static java.lang.Math.abs;

@Component
public abstract class BaseStrategy extends EnableableComponent implements Strategy, CircuitBreaker {

    private final static Logger log = Log4j2LoggerFactory.getLogger(BaseStrategy.class);

    /**
     * 策略ID
     */
    @Getter
    protected final int strategyId;

    /**
     * 策略名称
     */
    @Getter
    protected final String strategyName;

    /**
     * 子账号
     */
    @Getter
    protected final SubAccount subAccount;

    /**
     * 子账号ID
     */
    @Getter
    protected final int subAccountId;

    /**
     * 是否初始化成功
     */
    protected boolean initialized = false;

    /**
     * 记录当前策略所有的订单
     */
    @Getter
    protected final MutableLongObjectMap<io.rapid.core.order.Order> orders = MutableMaps.newLongObjectMap();

    /**
     * 策略参数
     */
    @Getter
    protected final Params params;

    /**
     * OrderSysID分配器
     */
    private final OrdSysIdAllocator allocator;

    @Resource
    protected AccountManager accountManager;

    @Resource
    protected MarketDataManager marketDataManager;

    @Resource
    protected StrategyManager strategyManager;

    /**
     * 策略订阅的合约列表
     */
    @Getter
    protected final MutableIntObjectMap<Instrument> instruments = MutableMaps.newIntObjectMap();

    @Resource(name = "coreScheduler")
    protected StrategySignalHandler signalHandler;

    protected BaseStrategy(int strategyId, String strategyName,
                           SubAccount subAccount, Params params,
                           ImmutableIntObjectMap<Instrument> instruments) {
        atWithinRange(strategyId, MIN_STRATEGY_ID, MAX_STRATEGY_ID, "strategyId");
        nonEmpty(strategyName, "strategyName");
        nonNull(subAccount, "subAccount");
        nonNull(params, "params");
        this.strategyId = strategyId;
        this.strategyName = strategyName;
        this.subAccount = subAccount;
        this.subAccountId = subAccount.getSubAccountId();
        this.params = params;
        this.allocator = OrdSysIdAllocatorKeeper.newAllocator(strategyId);
        log.info("Strategy -> {} initialized", strategyName);
        log.info("Strategy -> {} print params", params);
        params.showParams(log);
    }

    @PostConstruct
    private void init() {
        if (verification()) {
            // 将策略注册到管理器
            strategyManager.addStrategy(this);
        } else {
            log.info("Strategy -> {} validation failed", strategyName);
        }
    }

    protected abstract boolean verification();

    @Override
    public Strategy initialize(Supplier<Boolean> initializer) {
        if (initializer == null)
            initializer = () -> true;
        this.initialized = initializer.get();
        log.info("Initialize result initSuccess==[{}]", initialized);
        return this;
    }

    @Override
    public void acceptMarketData(@Nonnull SavedMarketData marketData) {
//        if (orders.notEmpty()) {
//            log.info("{} :: strategyOrders not empty, doing....", getStrategyName());
//            // TODO
//        }
        handleMarketData(marketData);
    }

    @AbstractFunction
    protected abstract void handleMarketData(SavedMarketData marketData);

    @Override
    public void onOrder(@Nonnull io.rapid.core.order.Order order) {
        order.toLog(log, "Call onOrder function");
        handleOrder(order);
    }

    @AbstractFunction
    protected abstract void handleOrder(io.rapid.core.order.Order order);

    @Override
    public void onStrategyEvent(@Nonnull StrategyEvent event) {
        log.info("{} :: Handle StrategyEvent -> {}", getStrategyName(), event);
        handleStrategyEvent(event);
    }

    @Override
    public void addInstrument(Instrument instrument) {
        instruments.put(instrument.getInstrumentId(), instrument);
        marketDataManager.
    }

    @Override
    public OrdSysIdAllocator getOrdSysIdAllocator() {
        return allocator;
    }

    @AbstractFunction
    protected abstract void handleStrategyEvent(@Nonnull StrategyEvent event);

    @Override
    public boolean enable() {
        if (initialized) {
            boolean enable = super.enable();
            if (enable)
                log.info("{}::enable success. strategyId==[{}], isEnable==[{}]",
                        strategyName, strategyId, isEnabled());
            else
                log.info(
                        "{}::enable failure. strategyId==[{}], isEnable==[{}]",
                        strategyName, strategyId, isEnabled());
            return enable;
        } else {
            log.info("{}::enable failure, strategy is not initialized. strategyId==[{}], isEnable==[{}]",
                    strategyName, strategyId, isEnabled());
            throw new IllegalStateException("Strategy has been initialized");
        }
    }

    @Override
    public boolean disable() {
        boolean disable = super.disable();
        if (disable)
            log.info("{}::disable strategy success. strategyId==[{}], isEnable==[{}]",
                    strategyName, strategyId, isEnabled());
        else
            log.info("{}::disable strategy failure, strategyId==[{}], isEnable==[{}]",
                    strategyName, strategyId, isEnabled());
        return disable;
    }

    @Override
    public void enableSubAccount(int subAccountId) {
        accountManager.setSubAccountTradable(subAccountId);
    }

    @Override
    public void disableSubAccount(int subAccountId) {
        accountManager.setSubAccountNotTradable(subAccountId);
    }

    @Override
    public void enableAccount(int accountId) {
        accountManager.setAccountTradable(accountId);
    }

    @Override
    public void disableAccount(int accountId) {
        accountManager.setAccountNotTradable(accountId);
    }

    @Override
    public void enableInstrument(int instrumentId) {
        InstrumentKeeper.setTradable(instrumentId);
    }

    @Override
    public void disableInstrument(int instrumentId) {
        InstrumentKeeper.setNotTradable(instrumentId);
    }

    @Override
    public void onException(Exception exception) {
        log.error("StrategyId -> [{}] throw exception -> [{}]",
                strategyId, exception.getMessage(), exception);
    }

    /**
     * 做市策略使用, 维持指定价位的挂单数量
     *
     * @param instrument Instrument
     * @param direction  TrdDirection
     * @param targetQty  int
     */
    protected void newTradeSignal(Instrument instrument, TrdDirection direction, int targetQty) {
        newTradeSignal(instrument, direction, targetQty, Double.NaN, -1);
    }

    /**
     * 做市策略使用, 维持指定价位的挂单数量
     *
     * @param instrument Instrument
     * @param direction  TrdDirection
     * @param targetQty  int
     * @param limitPrice long
     */
    protected void newTradeSignal(Instrument instrument, TrdDirection direction, int targetQty, double limitPrice) {
        newTradeSignal(instrument, direction, targetQty, limitPrice, 0);
    }

    /**
     * 做市策略使用, 维持指定价位的挂单数量
     *
     * @param instrument 交易标的
     * @param direction  交易方向
     * @param targetQty  目标数量
     * @param limitPrice 限定价格
     * @param floatTick  允许浮动点差
     */
    protected void newTradeSignal(Instrument instrument, TrdDirection direction, int targetQty, double limitPrice, int floatTick) {
        double offerPrice;
        if (Double.isNaN(limitPrice))
            offerPrice = getLevel1Price(instrument, direction);
        else
            offerPrice = limitPrice;

        var signal = new StrategySignal();

        signal.setInstrumentId(instrument.getInstrumentId());
        signal.setInstrumentCode(instrument.getInstrumentCode());
        signal.setDirection(direction);
        signal.setTargetQty(targetQty);
        signal.setOfferPrice(offerPrice);
        signal.setFloatTick(floatTick);

        log.info("Strategy -> {}, Created TradeSignal -> {}", strategyId, signal);
        signalHandler.onSignal(signal);
    }

//    /**
//     * 将StrategyOrder转换为需要执行的实际订单
//     */
//	private Function<StrategyOrder, MutableList<ActParentOrder>> strategyOrderConverter = strategyOrder -> {
//		MutableList<ActParentOrder> parentOrders = MutableLists.newFastList();
//		OrderBook instrumentOrderBook = OrderKeeper.getInstrumentOrderBook(strategyOrder.instrument());
//		int offerQty = strategyOrder.ordQty().offerQty();
//		switch (strategyOrder.direction()) {
//		case Long:
//			MutableLongObjectMap<Order> activeShortOrders = instrumentOrderBook.activeShortOrders();
//			if (activeShortOrders.notEmpty()) {
//				// TODO 当有活动的反向订单时选择撤单
//			}
//			// TODO 检查当前头寸, 如果有反向头寸, 选择平仓
//			// TODO 计算平仓后还需要开仓的数量
//			int needOpenLong = offerQty - 0;
//			ActParentOrder openLongOrder = strategyOrder.toActualOrder(TrdDirection.Long, needOpenLong, OrdType.Limit);
//			parentOrders.add(openLongOrder);
//			break;
//		case Short:
//			MutableLongObjectMap<Order> activeLongOrders = instrumentOrderBook.activeLongOrders();
//			if (activeLongOrders.notEmpty()) {
//				// TODO 当有活动的反向订单时选择撤单
//			}
//			// TODO 检查当前头寸, 如果有反向头寸, 选择平仓
//			// TODO 计算平仓后还需要开仓的数量
//			int needOpenShort = offerQty - 0;
//			ActParentOrder openShortOrder = strategyOrder.toActualOrder(TrdDirection.Short, needOpenShort,
//					OrdType.Limit);
//			parentOrders.add(openShortOrder);
//			break;
//		default:
//			break;
//		}
//		return parentOrders;
//	};

    /**
     * @param instrument Instrument
     * @param direction  TrdDirection
     * @return double
     */
    protected double getLevel1Price(Instrument instrument, TrdDirection direction) {
        MarketDataSnapshot snapshot = marketDataManager.getSnapshot(instrument);
        return switch (direction) {
            // 获取当前卖一价
            case LONG -> snapshot.getAskPrice1();
            // 获取当前买一价
            case SHORT -> snapshot.getBidPrice1();
            // 状态错误
            default -> throw new IllegalArgumentException("Direction is [Invalid]");
        };
    }

    /**
     * @param subAccountId int
     * @param instrument   Instrument
     * @return int
     */
    protected int getCurrentPosition(int subAccountId, Instrument instrument) {
        int position = PositionKeeper.getCurrentSubAccountPosition(subAccountId, instrument);
        if (position == 0)
            log.warn("{} :: No position, subAccountId==[{}], instrument -> {}", getStrategyName(), subAccountId,
                    instrument);
        return position;
    }

    protected void openPosition(Instrument instrument, int offerQty, TrdDirection direction) {
        openPosition(instrument, offerQty, getLevel1Price(instrument, direction), OrdType.LIMITED, direction);
    }

    /**
     * @param instrument 交易标的
     * @param offerQty   委托数量
     * @param ordType    订单类型
     * @param direction  多空方向
     */
    protected void openPosition(Instrument instrument, int offerQty, OrdType ordType, TrdDirection direction) {
        openPosition(instrument, offerQty, getLevel1Price(instrument, direction), ordType, direction);
    }

    /**
     * @param instrument 交易标的
     * @param offerQty   委托数量
     * @param offerPrice 委托价格
     * @param ordType    订单类型
     * @param direction  多空方向
     */
    protected void openPosition(Instrument instrument, int offerQty, double offerPrice, OrdType ordType,
                                TrdDirection direction) {
        final Order childOrder = OrderKeeper.createAndSaveChildOrder(allocator, strategyId, subAccount, account,
                instrument, abs(offerQty), offerPrice, ordType, direction, TrdAction.OPEN);
        childOrder.toLog(log, getStrategyName() + " :: Open position generate [ChildOrder]");
        saveOrder(childOrder);

        getTradingChannel().newOrder(childOrder.toNewOrder());
        childOrder.toLog(log, getStrategyName() + " :: Open position [ChildOrder] has been sent");
    }

    /**
     * @param instrument 交易标的
     */
    protected void closeAllPosition(Instrument instrument) {
        closeAllPosition(instrument, OrdType.LIMITED);
    }

    /**
     * @param instrument 交易标的
     * @param ordType    订单类型
     */
    protected void closeAllPosition(Instrument instrument, OrdType ordType) {
        final int position = getCurrentPosition(subAccountId, instrument);
        if (position == 0) {
            log.warn(
                    "{} :: Terminate execution close all positions, subAccountId==[{}], instrumentCode==[{}], position==[{}]",
                    getStrategyName(), subAccountId, instrument.getInstrumentCode(), position);
        } else {
            log.info("{} :: Execution close all positions, subAccountId==[{}], instrumentCode==[{}], position==[{}]",
                    getStrategyName(), subAccountId, instrument.getInstrumentCode(), position);
            double offerPrice;
            if (position > 0)
                offerPrice = getLevel1Price(instrument, TrdDirection.LONG);
            else
                offerPrice = getLevel1Price(instrument, TrdDirection.SHORT);
            closePosition(instrument, position, offerPrice, ordType);
        }
    }

    /**
     * @param instrument Instrument
     * @param offerPrice double
     */
    protected void closeAllPosition(Instrument instrument, double offerPrice) {
        final int position = getCurrentPosition(subAccountId, instrument);
        if (position == 0) {
            log.warn(
                    "{} :: Terminate execution close all positions, subAccountId==[{}], instrumentCode==[{}], position==[{}]",
                    getStrategyName(), subAccountId, instrument.getInstrumentCode(), position);
        } else {
            log.info("{} :: Execution close all positions, subAccountId==[{}], instrumentCode==[{}], position==[{}]",
                    getStrategyName(), subAccountId, instrument.getInstrumentCode(), position);
            closePosition(instrument, position, offerPrice, OrdType.LIMITED);
        }
    }

    /**
     * @param instrument 交易标的
     * @param offerPrice 委托价格
     * @param ordType    订单类型
     */
    protected void closeAllPosition(Instrument instrument, long offerPrice, OrdType ordType) {
        final int position = getCurrentPosition(subAccountId, instrument);
        if (position == 0) {
            log.warn(
                    "{} :: Terminate execution close all positions, subAccountId==[{}], instrumentCode==[{}], position==[{}]",
                    getStrategyName(), subAccountId, instrument.getInstrumentCode(), position);
        } else {
            log.info("{} :: Execution close all positions, subAccountId==[{}], instrumentCode==[{}], position==[{}]",
                    getStrategyName(), subAccountId, instrument.getInstrumentCode(), position);
            closePosition(instrument, position, offerPrice, ordType);
        }
    }

    /**
     * @param instrument 交易标的
     * @param offerQty   委托数量
     * @param offerPrice 委托价格
     */
    protected void closePosition(Instrument instrument, int offerQty, long offerPrice) {
        closePosition(instrument, offerQty, offerPrice, OrdType.LIMITED);
    }

    /**
     * @param instrument 交易标的
     * @param offerQty   委托数量
     * @param offerPrice 委托价格
     * @param ordType    订单类型
     */
    protected void closePosition(Instrument instrument, int offerQty, double offerPrice, OrdType ordType) {
        final Order childOrder = OrderKeeper
                .createAndSaveChildOrder(allocator, strategyId, subAccount, account,
                        instrument, abs(offerQty), offerPrice, ordType, offerQty > 0 ? TrdDirection.LONG : TrdDirection.SHORT,
                        TrdAction.CLOSE);

        childOrder.toLog(log, "Close position generate [ChildOrder]");
        saveOrder(childOrder);

        getTradingChannel().newOrder(childOrder.toNewOrder());
        childOrder.toLog(log, "Close position [ChildOrder] has been sent");
    }

    /**
     * 订单写入策略
     *
     * @param order Order
     */
    private void saveOrder(@Nonnull io.rapid.core.order.Order order) {
        orders.put(order.getOrdSysId(), order);
    }

}
