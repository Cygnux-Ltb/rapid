package io.cygnuxltb.jcts.engine.strategy;

import io.cygnuxltb.jcts.engine.position.PositionKeeper;
import io.cygnuxltb.jcts.engine.trader.OrderKeeper;
import io.mercury.common.annotation.AbstractFunction;
import io.mercury.common.collections.ImmutableSets;
import io.mercury.common.collections.MutableMaps;
import io.mercury.common.fsm.EnableableComponent;
import io.mercury.common.lang.Asserter;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.mercury.common.param.ParamKey;
import io.mercury.common.param.Params;
import io.mercury.common.sequence.SnowflakeAlgo;
import io.rapid.core.account.Account;
import io.rapid.core.account.AccountStorage;
import io.rapid.core.account.SubAccount;
import io.rapid.core.adaptor.TraderAdaptor;
import io.rapid.core.instrument.Instrument;
import io.rapid.core.instrument.InstrumentKeeper;
import io.rapid.core.mkd.FastMarketData;
import io.rapid.core.mkd.MarketDataKeeper;
import io.rapid.core.order.ChildOrder;
import io.rapid.core.order.OrdSysIdAllocator;
import io.rapid.core.order.Order;
import io.rapid.core.order.enums.OrdType;
import io.rapid.core.order.enums.TrdAction;
import io.rapid.core.order.enums.TrdDirection;
import io.rapid.core.protocol.avro.request.QueryBalance;
import io.rapid.core.protocol.avro.request.QueryPositions;
import io.rapid.core.risk.CircuitBreaker;
import io.rapid.core.strategy.Strategy;
import io.rapid.core.strategy.StrategyEvent;
import org.eclipse.collections.api.map.primitive.MutableLongObjectMap;
import org.eclipse.collections.api.set.ImmutableSet;
import org.slf4j.Logger;

import javax.annotation.Nonnull;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static java.lang.Math.abs;

public abstract class BaseStrategy<K extends ParamKey>
        extends EnableableComponent
        implements Strategy, CircuitBreaker {

    private final static Logger log = Log4j2LoggerFactory.getLogger(BaseStrategy.class);

    // 策略ID
    protected final int id;

    // 策略名称
    protected final String name;

    // 子账号
    protected final SubAccount subAccount;
    protected final int subAccountId;

    // 实际账号
    protected final Account account;
    protected final int accountId;

    // 是否初始化成功
    private boolean initSuccess = false;

    // 记录当前策略所有的订单
    protected final MutableLongObjectMap<Order> orders = MutableMaps.newLongObjectHashMap();

    // 策略参数
    protected final Params<K> params;

    // TODO
    protected QueryPositions queryPositions;

    // TODO
    protected QueryBalance queryBalance;

    private final OrdSysIdAllocator allocator;

    // 策略订阅的合约列表
    protected ImmutableSet<Instrument> instruments;

    // 策略订阅的合约
    protected Instrument instrument;

    protected final boolean isSingleInstrument;

    protected BaseStrategy(int id,
                           @Nonnull String name,
                           @Nonnull SubAccount subAccount,
                           @Nonnull Params<K> params,
                           @Nonnull Instrument... instruments) {
        Asserter.atWithinRange(id, 1, MAX_STRATEGY_ID, "strategyId");
        Asserter.nonEmpty(name, "strategyName");
        Asserter.nonNull(subAccount, "subAccount");
        this.id = id;
        this.name = name;
        this.subAccount = subAccount;
        this.subAccountId = subAccount.getSubAccountId();
        this.account = AccountStorage.getAccountBySubAccountId(subAccount.getSubAccountId());
        this.accountId = account.getAccountId();
        this.params = params;
        this.queryPositions = QueryPositions.newBuilder().setAccountId(accountId).setBrokerId(account.getBrokerId())
                .setOperatorId(name).setStrategyId(id).setSubAccountId(subAccountId).build();
        this.queryBalance = QueryBalance.newBuilder().setAccountId(accountId).setBrokerId(account.getBrokerId())
                .setOperatorId(name).setStrategyId(id).setSubAccountId(subAccountId).build();
        var snowflake = new SnowflakeAlgo(id);
        this.allocator = snowflake::next;
        this.isSingleInstrument = (instruments.length == 1);
        if (isSingleInstrument) {
            this.instrument = instruments[0];
        } else {
            this.instrument = null;
        }
        this.instruments = ImmutableSets.getSetFactory().fromStream(Stream.of(instruments));

    }

    public int getStrategyId() {
        return id;
    }

    public String getStrategyName() {
        return name;
    }

    public SubAccount getSubAccount() {
        return subAccount;
    }

    public Account getAccount() {
        return account;
    }

    public MutableLongObjectMap<Order> getOrders() {
        return orders;
    }

    public Params<K> getParams() {
        return params;
    }

    @Override
    public Strategy initialize(@Nonnull Supplier<Boolean> initializer) {
        Asserter.nonNull(initializer, "initializer");
        this.initSuccess = initializer.get();
        log.info("Initialize result initSuccess==[{}]", initSuccess);
        // TODO 设置StrategyKeeper
        // StrategyKeeper.putStrategy(this);
        return this;
    }

    @Override
    public void onMarketData(@Nonnull FastMarketData marketData) {
        if (orders.notEmpty()) {
            log.info("{} :: strategyOrders not empty, doing....", getStrategyName());
            // TODO
        }
        handleMarketData(marketData);
    }

    @AbstractFunction
    protected abstract void handleMarketData(FastMarketData marketData);

    @Override
    public void onOrder(@Nonnull Order order) {
        order.printLog(log, "Call onOrder function");
        handleOrder(order);
    }

    @AbstractFunction
    protected abstract void handleOrder(Order order);

    @Override
    public void onStrategyEvent(@Nonnull StrategyEvent event) {
        // TODO
        log.info("{} :: Handle StrategyControlEvent -> {}", getStrategyName(), event);
    }

    @Override
    public boolean enable() {
        if (initSuccess) {
            boolean enable = super.enable();
            if (enable) {
                log.info("{} :: enable strategy success. strategyId==[{}], initSuccess==[{}], isEnable==[{}]",
                        name, id, initSuccess, isEnabled());
            } else {
                log.info(
                        "{} :: enable strategy failure, strategy is enabled. strategyId==[{}], initSuccess==[{}], isEnable==[{}]",
                        name, id, initSuccess, isEnabled());
            }
            return enable;
        } else {
            log.info(
                    "{} :: enable strategy failure, strategy is not initialized. strategyId==[{}], initSuccess==[{}], isEnable==[{}]",
                    getStrategyName(), id, initSuccess, isEnabled());
            throw new IllegalStateException("Strategy has been initialized");
        }
    }

    @Override
    public boolean disable() {
        boolean disable = super.disable();
        if (disable) {
            log.info("{} :: disable strategy success. strategyId==[{}], isEnable==[{}]", getStrategyName(), id,
                    isEnabled());
        } else {
            log.info("{} :: disable strategy failure, strategy is disabled. strategyId==[{}], isEnable==[{}]",
                    getStrategyName(), id, isEnabled());
        }
        return disable;
    }

    @Override
    public void enableAccount(int accountId) {
        AccountStorage.setAccountTradable(accountId);
    }

    @Override
    public void disableAccount(int accountId) {
        AccountStorage.setAccountNotTradable(accountId);
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
    public void onThrowable(Throwable throwable) {
        log.error("StrategyId -> [{}] throw exception -> [{}]",
                id, throwable.getMessage(), throwable);
    }

    /**
     * 做市策略使用, 维持指定价位的挂单数量
     *
     * @param instrument Instrument
     * @param direction  TrdDirection
     * @param targetQty  int
     */
    void orderWatermark(Instrument instrument, TrdDirection direction, int targetQty) {
        orderWatermark(instrument, direction, targetQty, -1L, -1);
    }

    /**
     * 做市策略使用, 维持指定价位的挂单数量
     *
     * @param instrument Instrument
     * @param direction  TrdDirection
     * @param targetQty  int
     * @param limitPrice long
     */
    void orderWatermark(Instrument instrument, TrdDirection direction, int targetQty, long limitPrice) {
        orderWatermark(instrument, direction, targetQty, limitPrice, 0);
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
    void orderWatermark(Instrument instrument, TrdDirection direction, int targetQty, long limitPrice, int floatTick) {
//		long offerPrice = 0L;
//		if (limitPrice > 0)
//			offerPrice = limitPrice;
//		else
//			offerPrice = getLevel1Price(instrument, direction);

        // 创建策略订单
//		StrategyOrder strategyOrder = new StrategyOrder(strategyId, accountId, subAccountId, instrument,
//				OrdQty.withOffer(targetQty), OrdPrice.withOffer(offerPrice), OrdType.Limit, direction);
//		orders.put(strategyOrder.uniqueId(), strategyOrder);

        // 转换为实际订单
//		MutableList<ActParentOrder> parentOrders = strategyOrderConverter.apply(strategyOrder);

        // 存储订单
        // TODO 未完成全部逻辑
//		ActParentOrder parentOrder = parentOrders.getFirst();
//		orders.put(parentOrder.uniqueId(), parentOrder);

        // 转为实际执行的子订单
        // ActChildOrder childOrder = parentOrder.toChildOrder();
        // orders.put(childOrder.uniqueId(), childOrder);

        // getAdaptor(instrument).newOrder(childOrder);

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
        MarketDataKeeper.MarketDataSnapshot snapshot = MarketDataKeeper.getSnapshot(instrument);
        return switch (direction) {
            // 获取当前卖一价
            case Long -> snapshot.getAskPrice1();
            // 获取当前买一价
            case Short -> snapshot.getBidPrice1();
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
        openPosition(instrument, offerQty, getLevel1Price(instrument, direction), OrdType.Limited, direction);
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
        final ChildOrder childOrder = OrderKeeper.createAndSaveChildOrder(allocator, id, subAccount, account,
                instrument, abs(offerQty), offerPrice, ordType, direction, TrdAction.Open);
        childOrder.printLog(log, getStrategyName() + " :: Open position generate [ChildOrder]");
        saveOrder(childOrder);

        getTraderAdaptor().newOrder(childOrder.toNewOrder());
        childOrder.printLog(log, getStrategyName() + " :: Open position [ChildOrder] has been sent");
    }

    /**
     * @param instrument 交易标的
     */
    protected void closeAllPosition(Instrument instrument) {
        closeAllPosition(instrument, OrdType.Limited);
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
                offerPrice = getLevel1Price(instrument, TrdDirection.Long);
            else
                offerPrice = getLevel1Price(instrument, TrdDirection.Short);
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
            closePosition(instrument, position, offerPrice, OrdType.Limited);
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
        closePosition(instrument, offerQty, offerPrice, OrdType.Limited);
    }

    /**
     * @param instrument 交易标的
     * @param offerQty   委托数量
     * @param offerPrice 委托价格
     * @param ordType    订单类型
     */
    protected void closePosition(Instrument instrument, int offerQty, double offerPrice, OrdType ordType) {
        final ChildOrder childOrder = OrderKeeper.createAndSaveChildOrder(allocator, id, subAccount, account,
                instrument, abs(offerQty), offerPrice, ordType, offerQty > 0 ? TrdDirection.Long : TrdDirection.Short,
                TrdAction.Close);

        childOrder.printLog(log, "Close position generate [ChildOrder]");
        saveOrder(childOrder);

        getTraderAdaptor().newOrder(childOrder.toNewOrder());
        childOrder.printLog(log, "Close position [ChildOrder] has been sent");
    }

    /**
     * 订单写入策略
     *
     * @param order Order
     */
    private void saveOrder(@Nonnull Order order) {
        orders.put(order.getOrdSysId(), order);
    }


    /**
     * 由策略自行决定在交易不同Instrument时使用哪个TraderAdaptor
     *
     * @return Adaptor
     */
    protected TraderAdaptor getTraderAdaptor() {
        //TODO
        return null;
    }

}
