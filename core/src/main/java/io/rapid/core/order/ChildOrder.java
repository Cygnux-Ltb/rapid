package io.rapid.core.order;

import io.rapid.core.account.Account;
import io.rapid.core.account.AccountStorage;
import io.rapid.core.account.SubAccount;
import io.rapid.core.instrument.Instrument;
import io.rapid.core.instrument.InstrumentKeeper;
import io.rapid.core.order.attribute.OrdPrice;
import io.rapid.core.order.attribute.OrdQty;
import io.rapid.core.order.enums.OrdType;
import io.rapid.core.order.enums.TrdAction;
import io.rapid.core.order.enums.TrdDirection;
import io.rapid.core.serializable.avro.inbound.OrderEvent;
import io.rapid.core.serializable.avro.outbound.CancelOrder;
import io.rapid.core.serializable.avro.outbound.NewOrder;
import org.eclipse.collections.api.list.MutableList;
import org.slf4j.Logger;

import javax.annotation.Nonnull;
import java.io.Serial;

import static io.mercury.common.collections.MutableLists.newFastList;
import static io.rapid.core.account.SubAccount.ExternalOrderSubAccount;

/**
 * 实际执行订单的最小执行单元, 不可再进行拆分, 可能根据合规, 账户情况等由ParentOrder拆分出多个ChildOrder
 *
 * @author yellow013
 * @creation 2018-01-14
 */
public class ChildOrder extends AbstractOrder {

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
     * 子订单构造方法
     *
     * @param ordSysId     订单唯一ID
     * @param strategyId   策略ID
     * @param subAccountId 子账户ID
     * @param accountId    账户ID
     * @param instrument   交易标的
     * @param qty          数量
     * @param price        价格
     * @param type         订单类型
     * @param direction    交易方向
     * @param action       交易动作
     */
    protected ChildOrder(
            // 订单唯一ID, 策略ID
            long ordSysId, int strategyId,
            // 子账户ID, 账户ID, 交易标的
            int subAccountId, int accountId, @Nonnull Instrument instrument,
            // 数量, 价格
            @Nonnull OrdQty qty, @Nonnull OrdPrice price,
            // 订单类型, 交易方向, 交易动作
            @Nonnull OrdType type, @Nonnull TrdDirection direction, @Nonnull TrdAction action) {
        super(ordSysId, strategyId, subAccountId, accountId, instrument, qty, price, type, direction);
        this.action = action;
    }

    /**
     * 创建新订单
     *
     * @param allocator  OrdSysIdAllocator
     * @param strategyId int
     * @param subAccount SubAccount
     * @param account    Account
     * @param instrument Instrument
     * @param offerQty   int
     * @param offerPrice double
     * @param type       OrdType
     * @param direction  TrdDirection
     * @param action     TrdAction
     * @return ChildOrder
     */
    public static ChildOrder newOrder(
            // OrdSysIdAllocator
            @Nonnull OrdSysIdAllocator allocator,
            // strategyId, SubAccount, Account
            int strategyId, @Nonnull SubAccount subAccount, @Nonnull Account account,
            // Instrument, offerQty, offerPrice
            @Nonnull Instrument instrument, int offerQty, double offerPrice,
            // OrdType, TrdDirection, TrdAction
            @Nonnull OrdType type, @Nonnull TrdDirection direction, @Nonnull TrdAction action) {
        return new ChildOrder(
                // 使用strategyId生成ordSysId
                allocator.getOrdSysId(),
                // --------------------------
                strategyId, subAccount.getSubAccountId(), account.getAccountId(), instrument,
                // 设置委托数量
                OrdQty.withOffer(offerQty),
                // 设置委托价格
                OrdPrice.withOffer(offerPrice),
                // --------------------------
                type, direction, action);
    }

    /**
     * 用于构建外部来源的新订单, 通常是根据系统未托管的订单回报构建, 此时需要传递订单当前状态
     *
     * @param event OrderReport
     * @return ChildOrder
     */
    public static ChildOrder newExternalOrder(OrderEvent event) {
        var account = AccountStorage.getAccountByInvestorId(event.getInvestorId());
        var instrument = InstrumentKeeper.getInstrument(event.getInstrumentCode());
        var direction = TrdDirection.valueOf(event.getDirection());
        var action = TrdAction.valueOf(event.getAction());
        return new ChildOrder(event.getOrdSysId(),
                // -------------------------------
                // 外部订单使用的策略ID
                0,
                // 专用的处理外部来源订单的子账户
                ExternalOrderSubAccount.getSubAccountId(),
                // -------------------------------
                account.getAccountId(), instrument,
                // 以委托数量创建
                OrdQty.withOffer(event.getOfferQty()),
                // 以委托价格创建
                OrdPrice.withOffer(event.getOfferPrice()),
                // -------------------------------
                OrdType.Limited, direction, action);

    }

    /**
     * @param ordSysId   外部传入的ordSysId, 用于处理非系统订单
     * @param accountId  实际账户ID
     * @param instrument 交易标的
     * @param qty        委托数量
     * @param price      委托价格
     * @param direction  交易方向
     * @param action     交易动作
     * @return ChildOrder
     */
    @Deprecated
    public static ChildOrder newExternalOrder(
            //
            final long ordSysId,
            //
            final int accountId,
            //
            @Nonnull final Instrument instrument,
            //
            final OrdQty qty,
            //
            final OrdPrice price,
            //
            @Nonnull final TrdDirection direction,
            //
            @Nonnull final TrdAction action) {
        return new ChildOrder(ordSysId,
                // -------------------------------
                // 外部策略
                0,
                // -------------------------------
                ExternalOrderSubAccount.getSubAccountId(),
                // -------------------------------
                accountId, instrument, qty, price,
                // -------------------------------
                OrdType.Limited, direction, action);
    }

    @Override
    public int getOrdLevel() {
        return 0;
    }

    private static final String ChildOrderLogTemplate = "Msg : {}, ChildOrder attr : ordSysId==[{}], "
            + "status==[{}], direction==[{}], type==[{}], action==[{}], "
            + "instrument -> {}, qty -> {}, price -> {}, timestamp -> {}, trdRecords -> {}";

    @Override
    public void printLog(Logger logger, String msg) {
        logger.info(ChildOrderLogTemplate, msg, ordSysId, status, direction, type, action, instrument, qty, price,
                timestamp, records);
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

}
