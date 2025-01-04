package io.rapid.core.order;

import io.rapid.core.event.enums.OrdStatus;
import io.rapid.core.event.enums.OrdType;
import io.rapid.core.event.enums.OrdValid;
import io.rapid.core.event.enums.TrdDirection;
import io.rapid.core.instrument.Instrument;
import io.rapid.core.instrument.InstrumentKeeper;
import io.rapid.core.order.attribute.OrdPrice;
import io.rapid.core.order.attribute.OrdQty;
import io.rapid.core.order.attribute.OrdRemark;
import io.rapid.core.order.attribute.OrdTimestamp;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;

/**
 * Order基础实现
 *
 * @author yellow013
 */
public abstract non-sealed class AbstractOrder implements Order {

    @java.io.Serial
    private static final long serialVersionUID = -3444258095612091354L;

    /**
     * ordSysId
     */
    @Getter
    protected final long ordSysId;

    /**
     * 策略ID
     */
    @Getter
    protected final int strategyId;

    /**
     * 子账户ID
     */
    @Getter
    protected final int subAccountId;

    /**
     * 实际账户ID
     */
    @Getter
    protected final int accountId;

    /**
     * Instrument
     */
    @Getter
    protected final Instrument instrument;

    /**
     * 数量
     */
    @Getter
    protected final OrdQty qty;

    /**
     * 价格
     */
    @Getter
    protected final OrdPrice price;

    /**
     * 订单方向
     */
    @Getter
    protected final TrdDirection direction;

    /**
     * 订单类型(可变)
     */
    @Getter
    @Setter
    protected OrdType type;

    /**
     * 订单有效类型(可变)
     */
    @Getter
    @Setter
    protected OrdValid valid = OrdValid.defaultValid();

    /**
     * 订单状态(可变), 初始状态为[PENDING_NEW]
     */
    @Getter
    @Setter
    protected OrdStatus status = OrdStatus.PENDING_NEW;

    /**
     * 时间戳
     */
    @Getter
    protected final OrdTimestamp timestamp = OrdTimestamp.now();

    /**
     * 信号源, 用于追踪策略信号与订单的关联
     */
    @Getter
    @Setter
    protected String signalSource;

    /**
     * 订单备注(可添加新信息)
     */
    @Getter
    protected final OrdRemark remark = new OrdRemark();

    /**
     * @param strategyId     int
     * @param subAccountId   int
     * @param instrumentCode String
     * @param accountId      int
     * @param qty            OrdQty
     * @param price          OrdPrice
     * @param type           OrdType
     * @param direction      TrdDirection
     */
    protected AbstractOrder(int strategyId, int subAccountId, String instrumentCode, int accountId,
                            OrdQty qty, OrdPrice price, OrdType type, TrdDirection direction) {
        this.ordSysId = OrdSysIdAllocatorKeeper.nextOrdSysId(strategyId);
        this.strategyId = strategyId;
        this.subAccountId = subAccountId;
        this.instrument = InstrumentKeeper.getInstrumentByCode(instrumentCode);
        this.accountId = accountId;
        this.qty = qty;
        this.price = price;
        this.type = type;
        this.direction = direction;
    }

    private static final String LOG_TEMPLATE = "{}, Order attribute : "
            + "ordSysId==[{}], status==[{}], direction==[{}], type==[{}], "
            + "instrumentCode==[{}], price -> {}, qty -> {}, timestamp -> {}, remark -> {}";

    @Override
    public void logging(Logger log, String msg) {
        log.info(LOG_TEMPLATE, msg, ordSysId, status, direction, type,
                instrument.getInstrumentCode(), price, qty, timestamp, remark);
    }

}
