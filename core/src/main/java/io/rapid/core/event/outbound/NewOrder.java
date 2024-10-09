package io.rapid.core.event.outbound;

import io.mercury.common.serialization.Copyable;
import io.mercury.serialization.json.JsonBean;
import io.rapid.core.event.enums.OrdType;
import io.rapid.core.event.enums.OrdValid;
import io.rapid.core.event.enums.TrdAction;
import io.rapid.core.event.enums.TrdDirection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 新订单
 */
@Getter
@Setter
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewOrder extends JsonBean implements Copyable<NewOrder> {

    /**
     * Epoch Microsecond Unit
     */
    private long generateTime;
    /**
     * Epoch Microsecond Unit
     */
    private long sendTime;
    /**
     * 订单ID
     */
    private long ordSysId;
    /**
     * 委托数量
     */
    private int offerQty;
    /**
     * 最小成交数量
     */
    private int minimumQty;
    /**
     * 委托价格
     */
    private double offerPrice;
    /**
     * 交易标的ID
     */
    private int instrumentId;
    /**
     * 交易标的代码
     */
    private String instrumentCode;
    /**
     * 交易所代码
     */
    private String exchangeCode;
    /**
     * 订单类型
     */
    private OrdType type;
    /**
     * 订单有效类型
     */
    private OrdValid valid;
    /**
     * 订单动作
     */
    private TrdAction action;
    /**
     * 订单方向
     */
    private TrdDirection direction;
    /**
     * 账户ID
     */
    private int accountId;
    /**
     * 经纪商ID
     */
    private String brokerId;
    /**
     * 子账户ID
     */
    private int subAccountId;
    /**
     * 策略ID
     */
    private int strategyId;
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
     * 操作原因
     */
    private String reason;
    /**
     * 操作来源
     */
    private String source;


    @Override
    public void copyFrom(NewOrder source) {
        // 1. 复制生成时间 (Epoch Microsecond Unit)
        this.generateTime = source.generateTime;
        // 2. 复制发送时间 (Epoch Microsecond Unit)
        this.sendTime = source.sendTime;
        // 3. 复制订单ID
        this.ordSysId = source.ordSysId;
        // 4. 复制委托数量
        this.offerQty = source.offerQty;
        // 5. 复制最小成交数量
        this.minimumQty = source.minimumQty;
        // 6. 复制委托价格
        this.offerPrice = source.offerPrice;
        // 7. 复制交易标的ID
        this.instrumentId = source.instrumentId;
        // 8. 复制交易标的代码
        this.instrumentCode = source.instrumentCode;
        // 9. 复制交易所代码
        this.exchangeCode = source.exchangeCode;
        // 10. 复制订单类型
        this.type = source.type;
        // 11. 复制订单有效类型
        this.valid = source.valid;
        // 12. 复制订单动作
        this.action = source.action;
        // 13. 复制订单方向
        this.direction = source.direction;
        // 14. 复制账户ID
        this.accountId = source.accountId;
        // 15. 复制经纪商ID
        this.brokerId = source.brokerId;
        // 16. 复制子账户ID
        this.subAccountId = source.subAccountId;
        // 17. 复制策略ID
        this.strategyId = source.strategyId;
        // 18. 复制延迟时间 (毫秒)
        this.delayMillis = source.delayMillis;
        // 19. 复制操作ID
        this.operatorId = source.operatorId;
        // 20. 复制备注信息
        this.remark = source.remark;
        // 21. 复制操作原因
        this.reason = source.reason;
        // 22. 复制操作来源
        this.source = source.source;
    }

}