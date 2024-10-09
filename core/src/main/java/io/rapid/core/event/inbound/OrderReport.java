package io.rapid.core.event.inbound;

import io.mercury.common.serialization.Copyable;
import io.mercury.serialization.json.JsonBean;
import io.rapid.core.event.enums.OrdStatus;
import io.rapid.core.event.enums.TrdAction;
import io.rapid.core.event.enums.TrdDirection;
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
    public void copyFrom(OrderReport source) {
        // 复制微秒级时间戳
        this.epochMicros = source.getEpochMicros();
        // 复制订单系统ID
        this.ordSysId = source.getOrdSysId();
        // 复制交易日
        this.tradingDay = source.getTradingDay();
        // 复制经纪商ID
        this.brokerId = source.getBrokerId();
        // 复制投资者ID
        this.investorId = source.getInvestorId();
        // 复制订单引用
        this.orderRef = source.getOrderRef();
        // 复制经纪商订单系统ID
        this.brokerOrdSysId = source.getBrokerOrdSysId();
        // 复制交易所代码
        this.exchangeCode = source.getExchangeCode();
        // 复制交易标的代码
        this.instrumentCode = source.getInstrumentCode();
        // 复制订单状态
        this.status = source.getStatus();
        // 复制交易方向
        this.direction = source.getDirection();
        // 复制交易动作
        this.action = source.getAction();
        // 复制委托数量
        this.offerQty = source.getOfferQty();
        // 复制已成交数量
        this.filledQty = source.getFilledQty();
        // 复制委托价格
        this.offerPrice = source.getOfferPrice();
        // 复制成交价格
        this.tradePrice = source.getTradePrice();
        // 复制委托时间
        this.offerTime = source.getOfferTime();
        // 复制更新时间
        this.updateTime = source.getUpdateTime();
        // 复制消息
        this.msg = source.getMsg();
    }

}