package io.rapid.core.order.impl;

import io.rapid.core.event.enums.OrdType;
import io.rapid.core.event.enums.TrdAction;
import io.rapid.core.event.enums.TrdDirection;
import io.rapid.core.event.outbound.CancelOrder;
import io.rapid.core.event.outbound.NewOrder;
import io.rapid.core.instrument.Instrument;
import io.rapid.core.order.AbstractOrder;
import io.rapid.core.order.TradeRecord;
import io.rapid.core.order.attribute.OrdPrice;
import io.rapid.core.order.attribute.OrdQty;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.eclipse.collections.api.list.MutableList;
import org.slf4j.Logger;

import java.io.Serial;

import static io.mercury.common.collections.MutableLists.newFastList;

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
    protected final TrdAction action;

    /**
     * 经纪商提供的唯一码, 可能有多个, 使用数组实现
     */
    protected final String[] brokerIdentifier = new String[4];

    /**
     * 订单成交列表
     */
    protected final MutableList<TradeRecord> records = newFastList(4);

    /**
     * @param strategyId     策略ID
     * @param subAccountId   子账户ID
     * @param accountId      账户ID
     * @param instrumentCode 交易标的代码
     * @param offerQty       委托数量
     * @param offerPrice     委托价格
     * @param type           订单类型
     * @param direction      交易方向
     * @param action         交易动作
     */
    private Order(// 策略ID, 子账户ID, 账户ID
                  int strategyId, int subAccountId, int accountId,
                  // 交易标的代码, 委托数量, 委托价格
                  String instrumentCode, int offerQty, double offerPrice,
                  // 订单类型, 交易方向, 交易动作
                  OrdType type, TrdDirection direction, TrdAction action) {
        this(// --------------------------
                strategyId, subAccountId, accountId, instrumentCode,
                // 设置委托数量
                OrdQty.withOffer(offerQty),
                // 设置委托价格
                OrdPrice.withOffer(offerPrice),
                // --------------------------
                type, direction, action);
    }

    /**
     * 子订单构造方法
     *
     * @param strategyId     策略ID
     * @param subAccountId   子账户ID
     * @param accountId      账户ID
     * @param instrumentCode 交易标的代码
     * @param qty            数量
     * @param price          价格
     * @param type           订单类型
     * @param direction      交易方向
     * @param action         交易动作
     */
    public Order(// 策略ID, 子账户ID, 账户ID
                 int strategyId, int subAccountId, int accountId,
                 // 交易标的代码, 数量, 价格
                 String instrumentCode, OrdQty qty, OrdPrice price,
                 // 订单类型, 交易方向, 交易动作
                 OrdType type, TrdDirection direction, TrdAction action) {
        super(strategyId, subAccountId, accountId, instrumentCode, qty, price, type, direction);
        this.action = action;
    }


    @Override
    public int getOrdLevel() {
        return 0;
    }

    private static final String LogTemplate = "Msg : {}, ChildOrder attribute : ordSysId==[{}], "
            + "status==[{}], direction==[{}], type==[{}], action==[{}], "
            + "instrumentCode==[{}], qty -> {}, price -> {}, timestamp -> {}, trdRecords -> {}";

    @Override
    public void toLog(Logger log, String msg) {
        log.info(LogTemplate, msg, ordSysId, status, direction, type, action,
                instrumentCode, qty, price, timestamp, records);
    }

    public TrdAction getAction() {
        return action;
    }

    public String[] getBrokerIdentifier() {
        return brokerIdentifier;
    }

    public MutableList<TradeRecord> getRecords() {
        return records;
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
        // TODO
        return null;
    }

    /**
     * @return CancelOrder
     */
    public CancelOrder toCancelOrder() {
        // TODO
        return null;
    }

    @Setter
    @Accessors(chain = true, fluent = true)
    public static class Builder {

        // 策略ID
        private int strategyId;
        // 子账户ID
        private int subAccountId;

        // 账户ID
        private int accountId;
        // 交易标的
        private Instrument instrument;

        // 委托数量
        private int offerQty;
        // 委托价格
        private double offerPrice;

        // 订单类型
        private OrdType type;
        // 交易方向
        private TrdDirection direction;
        // 交易动作
        private TrdAction action;

    }

}
