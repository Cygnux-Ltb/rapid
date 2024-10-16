package io.rapid.core.order.impl;

import io.rapid.core.event.enums.OrdType;
import io.rapid.core.event.enums.TrdAction;
import io.rapid.core.event.enums.TrdDirection;
import io.rapid.core.event.outbound.CancelOrder;
import io.rapid.core.event.outbound.NewOrder;
import io.rapid.core.order.AbstractOrder;
import io.rapid.core.order.TradeRecord;
import lombok.Getter;
import org.eclipse.collections.api.list.MutableList;
import org.slf4j.Logger;

import java.io.Serial;

import static io.mercury.common.collections.MutableLists.newFastList;
import static io.mercury.common.epoch.HighResolutionEpoch.micros;
import static io.rapid.core.order.attribute.OrdPrice.withOffer;
import static io.rapid.core.order.attribute.OrdQty.withOffer;

/**
 * 实际执行订单的最小执行单元, 不可再进行拆分, 可能根据合规, 账户情况等由ParentOrder拆分出多个ChildOrder
 *
 * @author yellow013
 * @creation 2018-01-14
 */
public class Order extends AbstractOrder {

    @Serial
    private static final long serialVersionUID = 6034876220144503779L;

    /**
     * 交易动作
     */
    @Getter
    private final TrdAction action;

    /**
     * 经纪商提供的唯一码, 可能有多个, 使用数组实现
     */
    @Getter
    private final String[] brokerIdentifier = new String[4];

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
    public Order(// 父订单
                 ParentOrder parentOrder,
                 // 账户ID, 委托数量, 委托价格
                 int accountId, int offerQty, double offerPrice,
                 // 订单类型, 交易方向, 交易动作
                 OrdType type, TrdDirection direction, TrdAction action) {
        super(// 策略ID, 子账户ID, 交易标的代码,
                parentOrder.getStrategyId(), parentOrder.getSubAccountId(), parentOrder.getInstrumentCode(),
                // 账户ID, 设置委托数量, 设置委托价格
                accountId, withOffer(offerQty), withOffer(offerPrice),
                // --------------------------
                type, direction);
        this.action = action;
        this.parentOrdSysId = parentOrder.getOrdSysId();
        parentOrder.addChild(this);
    }

    @Override
    public boolean isChild() {
        return true;
    }

    private static final String LogTemplate = "Msg : {}, ChildOrder attribute : ordSysId==[{}], "
            + "status==[{}], direction==[{}], type==[{}], action==[{}], "
            + "instrumentCode==[{}], qty -> {}, price -> {}, timestamp -> {}, trdRecords -> {}";

    @Override
    public void toLog(Logger log, String msg) {
        log.info(LogTemplate, msg, ordSysId, status, direction, type, action,
                instrumentCode, qty, price, timestamp, records);
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
                .subAccountId(subAccountId).instrumentCode(instrumentCode)
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
                .subAccountId(subAccountId).instrumentCode(instrumentCode)
                .build();
    }

}
