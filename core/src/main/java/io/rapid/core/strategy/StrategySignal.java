package io.rapid.core.strategy;

import io.mercury.common.serialization.Copyable;
import io.mercury.serialization.json.JsonBean;
import io.rapid.core.event.enums.OrdValid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import static io.mercury.common.util.StringSupport.isNullOrEmpty;
import static java.lang.Math.abs;


/**
 * 策略信号
 */
@Getter
@Setter
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StrategySignal extends JsonBean implements Copyable<StrategySignal> {

    /**
     * Epoch Microsecond Unit
     */
    private long generateTime;
    /**
     * Epoch Microsecond Unit
     */
    private long sendTime;
    /**
     * 子账户ID [不可为空]
     */
    private int subAccountId;
    /**
     * 策略ID [不可为空]
     */
    private int strategyId;
    /**
     * 交易标的ID [不可为空]
     */
    private int instrumentId;
    /**
     * 交易标的代码 [不可为空]
     */
    private String instrumentCode;
    /**
     * 订单有效类型
     */
    private OrdValid valid;
    /**
     * 订单水位 (做市策略使用)
     */
    private int orderWatermark;
    /**
     * 目标数量
     */
    private int targetQty;
    /**
     * 最小成交数量
     */
    private int minimumQty;
    /**
     * 委托价格
     */
    private double offerPrice;
    /**
     * 允许浮动点数
     */
    private int floatTick;
    /**
     * 止盈
     */
    private double stopProfit;
    /**
     * 止损
     */
    private double stopLoss;
    /**
     * 订单比例
     */
    private double ratioOfBalance;
    /**
     * 胜率
     */
    private double ratioOfWin;
    /**
     * 延迟时间
     */
    private long delayMillis;
    /**
     * 操作ID
     */
    private String operatorId;
    /**
     * 备注
     */
    private String remark;

    /**
     * TargetQty ABS
     *
     * @return int
     */
    public int getTargetQtyAbs() {
        return abs(targetQty);
    }

    /**
     * OrderWatermark ABS
     *
     * @return int
     */
    public int getOrderWatermarkAbs() {
        return abs(orderWatermark);
    }

    @Override
    public void copyFrom(StrategySignal source) {
        // 复制生成时间 (Epoch Microsecond Unit)
        this.generateTime = source.generateTime;
        // 复制发送时间 (Epoch Microsecond Unit)
        this.sendTime = source.sendTime;
        // 复制子账户ID
        this.subAccountId = source.subAccountId;
        // 复制策略ID
        this.strategyId = source.strategyId;
        // 复制交易标的ID
        this.instrumentId = source.instrumentId;
        // 复制交易标的代码
        this.instrumentCode = source.instrumentCode;
        // 复制订单有效类型
        this.valid = source.valid;
        // 复制订单水位
        this.orderWatermark = source.orderWatermark;
        // 复制目标数量
        this.targetQty = source.targetQty;
        // 复制最小成交数量
        this.minimumQty = source.minimumQty;
        // 复制委托价格
        this.offerPrice = source.offerPrice;
        // 复制允许浮动点数
        this.floatTick = source.floatTick;
        // 复制止盈价格
        this.stopProfit = source.stopProfit;
        // 复制止损价格
        this.stopLoss = source.stopLoss;
        // 复制订单比例
        this.ratioOfBalance = source.ratioOfBalance;
        // 复制胜率
        this.ratioOfWin = source.ratioOfWin;
        // 复制延迟时间
        this.delayMillis = source.delayMillis;
        // 复制操作ID
        this.operatorId = source.operatorId;
        // 复制备注
        this.remark = source.remark;
    }

    public boolean isVerification() {
        // instrument 未提供
        if (isNullOrEmpty(instrumentCode) && instrumentId == 0)
            return false;
        // subAccountId or strategyId 未提供
        if (subAccountId == 0 || strategyId == 0)
            return false;
        // targetQty or orderWatermark 未提供
        return targetQty != 0 || orderWatermark != 0;
    }


}