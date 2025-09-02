package io.cygnux.rapid.core.stream.event;

import io.cygnux.rapid.core.stream.enums.OrdStatus;
import io.cygnux.rapid.core.stream.enums.TrdAction;
import io.cygnux.rapid.core.stream.enums.TrdDirection;
import io.mercury.common.serialization.Copyable;
import io.mercury.serialization.json.JsonBean;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 订单回报
 */
@Getter
@Setter
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderReport extends JsonBean implements Copyable<OrderReport> {

    /**
     * 接收时[Epoch微秒]
     */
    private long epochMicros;
    private long ordSysId;
    private int tradingDay;
    private String brokerId;
    private String investorId;
    private int accountId;
    private String orderRef;
    private String brokerOrdSysId;
    private String exchangeCode;
    private String instrumentCode;
    private OrdStatus status;
    private TrdDirection direction;
    private TrdAction action;
    private int offerQty;
    private int filledQty;
    private double offerPrice;
    private double tradePrice;
    private String offerTime;
    private String updateTime;
    private String msg;

    @Override
    public void copyOf(OrderReport source) {
        // 复制微秒级时间戳
        this.epochMicros = source.epochMicros;
        // 复制订单系统ID
        this.ordSysId = source.ordSysId;
        // 复制交易日
        this.tradingDay = source.tradingDay;
        // 复制经纪商ID
        this.brokerId = source.brokerId;
        // 复制投资者ID
        this.investorId = source.investorId;
        // 复制真实账户ID
        this.accountId = source.accountId;
        // 复制订单引用
        this.orderRef = source.orderRef;
        // 复制经纪商订单系统ID
        this.brokerOrdSysId = source.brokerOrdSysId;
        // 复制交易所代码
        this.exchangeCode = source.exchangeCode;
        // 复制交易标的代码
        this.instrumentCode = source.instrumentCode;
        // 复制订单状态
        this.status = source.status;
        // 复制交易方向
        this.direction = source.direction;
        // 复制交易动作
        this.action = source.action;
        // 复制委托数量
        this.offerQty = source.offerQty;
        // 复制已成交数量
        this.filledQty = source.filledQty;
        // 复制委托价格
        this.offerPrice = source.offerPrice;
        // 复制成交价格
        this.tradePrice = source.tradePrice;
        // 复制委托时间
        this.offerTime = source.offerTime;
        // 复制更新时间
        this.updateTime = source.updateTime;
        // 复制消息
        this.msg = source.msg;
    }

    public OrderReport clear() {
        // 擦除微秒级时间戳
        this.epochMicros = 0L;
        // 擦除订单系统ID
        this.ordSysId = 0L;
        // 擦除交易日
        this.tradingDay = 0;
        // 擦除经纪商ID
        this.brokerId = null;
        // 擦除投资者ID
        this.investorId = null;
        // 擦除真实账户ID
        this.accountId = 0;
        // 擦除订单引用
        this.orderRef = null;
        // 擦除经纪商订单系统ID
        this.brokerOrdSysId = null;
        // 擦除交易所代码
        this.exchangeCode = null;
        // 擦除交易标的代码
        this.instrumentCode = null;
        // 擦除订单状态
        this.status = null;
        // 擦除交易方向
        this.direction = null;
        // 擦除交易动作
        this.action = null;
        // 擦除委托数量
        this.offerQty = 0;
        // 擦除已成交数量
        this.filledQty = 0;
        // 擦除委托价格
        this.offerPrice = Double.NaN;
        // 擦除成交价格
        this.tradePrice = Double.NaN;
        // 擦除委托时间
        this.offerTime = null;
        // 擦除更新时间
        this.updateTime = null;
        // 擦除消息
        this.msg = null;
        return this;
    }

}