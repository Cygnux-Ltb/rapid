package io.cygnux.rapid.core.types.order.impl;

import io.cygnux.rapid.core.types.account.SubAccount;
import io.cygnux.rapid.core.types.adapter.event.CancelOrder;
import io.cygnux.rapid.core.types.adapter.event.NewOrder;
import io.cygnux.rapid.core.types.field.StrategyID;
import io.cygnux.rapid.core.types.instrument.Instrument;
import io.cygnux.rapid.core.types.order.AbstractOrder;
import io.cygnux.rapid.core.types.order.attr.OrdPrice;
import io.cygnux.rapid.core.types.order.attr.OrdQty;
import io.cygnux.rapid.core.types.order.enums.OrdType;
import io.cygnux.rapid.core.types.trade.TradeRecord;
import io.cygnux.rapid.core.types.trade.enums.TrdAction;
import io.cygnux.rapid.core.types.trade.enums.TrdDirection;
import lombok.Getter;
import org.eclipse.collections.api.list.MutableList;
import org.slf4j.Logger;

import static io.cygnux.rapid.core.types.order.attr.OrdPrice.withOffer;
import static io.cygnux.rapid.core.types.order.attr.OrdQty.withOffer;
import static io.mercury.common.collections.MutableLists.newFastList;
import static io.mercury.common.epoch.HighResolutionEpoch.micros;

/**
 * 实际执行订单的最小执行单元, 不可再进行拆分, 可能根据合规, 账户情况等由ParentOrder拆分出多个ChildOrder
 *
 * @author yellow013
 * @creation 2018-01-14
 */
public class ChildOrder extends AbstractOrder {

    /**
     * 交易动作
     */
    @Getter
    private final TrdAction action;

    /**
     * 经纪商提供的唯一码, 可能有多个, 使用数组实现
     */
    @Getter
    private final String[] brokerRef = new String[4];

    /**
     * 订单成交列表
     */
    @Getter
    private final MutableList<TradeRecord> records = newFastList(4);

    /**
     * 父订单[ORD_SYS_ID]
     */
    @Getter
    private final long parentOrdSysId;

    /**
     * 子订单构造方法
     *
     * @param parentOrder 父订单
     * @param accountId   实际账户ID
     * @param offerQty    委托数量
     * @param offerPrice  委托价格
     * @param type        订单类型
     * @param direction   交易方向
     * @param action      交易动作
     */
    private ChildOrder(long ordSysId,
                       // 父订单
                       ParentOrder parentOrder,
                       // 账户ID, 委托数量, 委托价格
                       int accountId, int offerQty, double offerPrice,
                       // 订单类型, 交易方向, 交易动作
                       OrdType type, TrdDirection direction, TrdAction action) {
        super(ordSysId,
                // 策略ID, 子账户ID, 交易标的代码
                parentOrder.getStrategyId(), parentOrder.getSubAccountId(),
                parentOrder.getInstrument(),
                // 账户ID
                accountId,
                // 设置委托数量, 设置委托价格
                withOffer(offerQty), withOffer(offerPrice),
                type, direction);
        this.action = action;
        this.parentOrdSysId = parentOrder.getOrdSysId();
        parentOrder.addChild(this);
    }

    public static ChildOrder newWithParent(long ordSysId,
                                           // 父订单
                                           ParentOrder parentOrder,
                                           // 账户ID, 委托数量, 委托价格
                                           int accountId, int offerQty, double offerPrice,
                                           // 订单类型, 交易方向, 交易动作
                                           OrdType type, TrdDirection direction, TrdAction action) {
        return new ChildOrder(ordSysId, parentOrder, accountId, offerQty, offerPrice, type, direction, action);
    }

    /**
     * @param ordSysId   long
     * @param accountId  int
     * @param instrument Instrument
     * @param qty        OrdQty
     * @param price      OrdPrice
     * @param direction  TrdDirection
     * @param action     TrdAction
     */
    private ChildOrder(long ordSysId,
                       int accountId, Instrument instrument,
                       OrdQty qty, OrdPrice price,
                       TrdDirection direction, TrdAction action) {
        super(ordSysId,
                StrategyID.EXTERNAL_ORDER_STRATEGY_ID,
                // 专用的处理外部来源订单的子账户
                SubAccount.EXTERNAL_ORDER_SUB_ACCOUNT.getSubAccountId(),
                // 交易标的, 实际账户ID, 委托数量, 委托价格
                instrument, accountId, qty, price,
                OrdType.defaultType(), direction);
        this.action = action;
        this.parentOrdSysId = -1;
    }

    public static ChildOrder newWithExternal(long ordSysId, int accountId, Instrument instrument,
                                             OrdQty qty, OrdPrice price,
                                             TrdDirection direction, TrdAction action) {
        return new ChildOrder(ordSysId, accountId, instrument, qty, price, direction, action);
    }

    public void setBrokerRef0(String brokerRef) {
        this.brokerRef[0] = brokerRef;
    }

    public void setBrokerRef1(String brokerRef) {
        this.brokerRef[1] = brokerRef;
    }

    public void setBrokerRef2(String brokerRef) {
        this.brokerRef[2] = brokerRef;
    }

    public void setBrokerRef3(String brokerRef) {
        this.brokerRef[3] = brokerRef;
    }

    @Override
    public boolean isChild() {
        return true;
    }

    private static final String LogTemplate = "Msg : {}, ChildOrder attribute : ordSysId==[{}], "
                                              + "status==[{}], direction==[{}], type==[{}], action==[{}], "
                                              + "instrumentCode==[{}], qty -> {}, price -> {}, timestamp -> {}, trdRecords -> {}";

    @Override
    public void logging(Logger log, String msg) {
        log.info(LogTemplate, msg, ordSysId, status, direction, type, action,
                instrument.getInstrumentCode(), qty, price, timestamp, records);
    }

    /**
     * @return TradeRecord
     */
    public TradeRecord getFirstRecord() {
        return records.getFirst();
    }

    /**
     * @return TradeRecord
     */
    public TradeRecord getLastRecord() {
        return records.getLast();
    }

    /**
     * @param epochMicros long
     * @param tradePrice  long
     * @param tradeQty    int
     */
    public void addRecord(long epochMicros, double tradePrice, int tradeQty) {
        records.add(new TradeRecord(ordSysId, records.size() + 1,
                epochMicros, tradePrice, tradeQty));
    }

    /**
     * @return double
     */
    public double fillAndGetAvgTradePrice() {
        return price.calcAvgTradePrice(this).getAvgTradePrice();
    }


    /**
     * @return NewOrder
     */
    public NewOrder toNewOrder() {
        return NewOrder.builder().ordSysId(ordSysId)
                .accountId(accountId).strategyId(strategyId).generateTime(micros())
                .subAccountId(subAccountId).instrumentCode(instrument.getInstrumentCode())
                .offerQty(qty.getOfferQty()).offerPrice(price.getOfferPrice())
                .action(action).direction(direction)
                .valid(valid).type(type)
                .build();
    }

    /**
     * @return CancelOrder
     */
    public CancelOrder toCancelOrder() {
        return CancelOrder.builder().ordSysId(ordSysId)
                .accountId(accountId).strategyId(strategyId).generateTime(micros())
                .subAccountId(subAccountId).instrumentCode(instrument.getInstrumentCode())
                .build();
    }

}
