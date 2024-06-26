package io.rapid.core.order;

import io.rapid.core.instrument.Instrument;
import io.rapid.core.order.attribute.OrdPrice;
import io.rapid.core.order.attribute.OrdQty;
import io.rapid.core.order.attribute.OrdRemark;
import io.rapid.core.order.attribute.OrdTimestamp;
import io.rapid.core.order.enums.OrdStatus;
import io.rapid.core.order.enums.OrdType;
import io.rapid.core.order.enums.OrdValid;
import io.rapid.core.order.enums.TrdDirection;
import org.slf4j.Logger;

import javax.annotation.Nonnull;
import java.io.Serial;

/**
 * @author yellow013
 */
public abstract class AbstractOrder implements Order {

    @Serial
    private static final long serialVersionUID = -3444258095612091354L;

    // ordSysId
    protected final long ordSysId;

    // 策略Id
    protected final int strategyId;

    // 子账户Id
    protected final int subAccountId;

    // 实际账户Id
    protected final int accountId;

    // instrument
    protected final Instrument instrument;

    // 数量
    protected final OrdQty qty;

    // 价格
    protected final OrdPrice price;

    // 订单类型
    protected OrdType type;

    // 订单有效类型
    protected OrdValid valid;

    // 订单方向
    protected final TrdDirection direction;

    // 时间戳
    protected final OrdTimestamp timestamp;

    // 订单状态(可变)
    protected OrdStatus status;

    // 订单备注(可添加新信息)
    protected final OrdRemark remark;

    /**
     * @param ordSysId     long
     * @param strategyId   int
     * @param subAccountId int
     * @param accountId    int
     * @param instrument   Instrument
     * @param qty          OrdQty
     * @param price        OrdPrice
     * @param type         OrdType
     * @param direction    TrdDirection
     */
    protected AbstractOrder(long ordSysId, int strategyId, int subAccountId, int accountId,
                            @Nonnull Instrument instrument, @Nonnull OrdQty qty,
                            @Nonnull OrdPrice price, @Nonnull OrdType type,
                            @Nonnull TrdDirection direction) {
        this.ordSysId = ordSysId;
        this.strategyId = strategyId;
        this.subAccountId = subAccountId;
        this.accountId = accountId;
        this.instrument = instrument;
        this.qty = qty;
        this.price = price;
        this.type = type;
        // TODO
        this.valid = OrdValid.defaultValid();
        this.direction = direction;
        this.timestamp = OrdTimestamp.now();
        // TODO
        this.remark = new OrdRemark();
        this.status = OrdStatus.PendingNew;
    }

    @Override
    public long getOrdSysId() {
        return ordSysId;
    }

    @Override
    public int getStrategyId() {
        return strategyId;
    }

    @Override
    public int getSubAccountId() {
        return subAccountId;
    }

    @Override
    public int getAccountId() {
        return accountId;
    }

    @Override
    public Instrument getInstrument() {
        return instrument;
    }

    @Override
    public OrdQty getQty() {
        return qty;
    }

    @Override
    public OrdPrice getPrice() {
        return price;
    }

    @Override
    public OrdType getType() {
        return type;
    }

    @Override
    public OrdValid getValid() {
        return valid;
    }

    @Override
    public TrdDirection getDirection() {
        return direction;
    }

    @Override
    public OrdTimestamp getTimestamp() {
        return timestamp;
    }

    @Override
    public OrdStatus getStatus() {
        return status;
    }

    @Override
    public OrdRemark getRemark() {
        return remark;
    }

    @Override
    public Order setStatus(@Nonnull OrdStatus status) {
        this.status = status;
        return this;
    }

    @Override
    public void addRemark(@Nonnull String remark) {
        this.remark.add(remark);
    }

    private static final String LOG_TEMPLATE = "{}, Order attr : "
            + "ordSysId==[{}], status==[{}], direction==[{}], type==[{}], "
            + "instrument -> {}, price -> {}, qty -> {}, timestamp -> {}, remark -> {}";

    @Override
    public void printLog(Logger logger, String msg) {
        logger.info(LOG_TEMPLATE, msg, ordSysId, status, direction, type,
                instrument, price, qty, timestamp, remark);
    }

}
