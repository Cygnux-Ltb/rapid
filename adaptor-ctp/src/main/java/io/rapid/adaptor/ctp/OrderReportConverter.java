package io.rapid.adaptor.ctp;

import io.rapid.adaptor.ctp.consts.FtdcOffsetFlag;
import io.rapid.adaptor.ctp.event.trader.FtdcInputOrder;
import io.rapid.adaptor.ctp.event.trader.FtdcInputOrderAction;
import io.rapid.adaptor.ctp.event.trader.FtdcOrder;
import io.rapid.adaptor.ctp.event.trader.FtdcOrderAction;
import io.rapid.adaptor.ctp.event.trader.FtdcTrade;
import io.rapid.core.event.enums.OrdStatus;
import io.rapid.core.event.inbound.OrderReport;
import jakarta.annotation.Resource;
import org.slf4j.Logger;

import static io.mercury.common.epoch.HighResolutionEpoch.micros;
import static io.mercury.common.log4j2.Log4j2LoggerFactory.getLogger;
import static io.mercury.common.util.StringSupport.removeNonDigits;
import static io.rapid.adaptor.ctp.consts.FtdcDirection.withFtdcDirection;
import static io.rapid.adaptor.ctp.consts.FtdcOffsetFlag.withFtdcOffsetFlag;
import static io.rapid.adaptor.ctp.consts.FtdcOrderStatus.withFtdcOrderStatus;
import static java.lang.Integer.parseInt;

/**
 * OrderReportConverter
 *
 * @author yellow013
 */
public final class OrderReportConverter {

    private static final Logger log = getLogger(OrderReportConverter.class);

    @Resource
    private FastOrderRefAllocator orderRefAllocator;

    /**
     * 报单错误消息转换 <br>
     * <br>
     * FtdcInputOrder -> OrderReport
     *
     * @param order FtdcInputOrder
     * @return OrderReport
     */
    public OrderReport withFtdcInputOrder(FtdcInputOrder order) {
        String orderRef = order.OrderRef;
        long ordSysId = orderRefAllocator.getOrdSysId(orderRef);
        var event = OrderReport.builder()
                // 时间戳
                .epochMicros(micros())
                // OrdSysId
                .ordSysId(ordSysId)
                // 投资者ID
                .investorId(order.InvestorID)
                // 报单引用
                .orderRef(orderRef)
                // 交易所
                .exchangeCode(order.ExchangeID)
                // 合约代码
                .instrumentCode(order.InstrumentID)
                // 报单状态
                .status(OrdStatus.NEW_REJECTED)
                // 买卖方向
                .direction(withFtdcDirection((char) order.Direction))
                // 组合开平标志
                .action(FtdcOffsetFlag.withFtdcOffsetFlag(order.CombOffsetFlag))
                // 委托数量
                .offerQty(order.VolumeTotalOriginal)
                // 委托价格
                .offerPrice(order.LimitPrice)
                .build();
        log.info("FtdcInputOrder conversion to OrderReport -> {}", event);
        return event;
    }

    /**
     * 订单回报消息转换<br>
     * <br>
     * FtdcOrder -> OrderReport
     *
     * @param order FtdcOrder
     * @return OrderReport
     */
    public OrderReport withFtdcOrder(FtdcOrder order) {
        String orderRef = order.OrderRef;
        long ordSysId = orderRefAllocator.getOrdSysId(orderRef);
        var event = OrderReport.builder()
                // 时间戳
                .epochMicros(micros())
                // OrdSysId
                .ordSysId(ordSysId)
                // 交易日
                .tradingDay(parseInt(order.TradingDay))
                // 投资者ID
                .investorId(order.InvestorID)
                // 报单引用
                .orderRef(orderRef)
                // 报单编号
                .brokerOrdSysId(order.OrderSysID)
                // 交易所
                .exchangeCode(order.ExchangeID)
                // 合约代码
                .instrumentCode(order.InstrumentID)
                // 报单状态
                .status(withFtdcOrderStatus((char) order.OrderStatus))
                // 买卖方向
                .direction(withFtdcDirection((char) order.Direction))
                // 组合开平标志
                .action(FtdcOffsetFlag.withFtdcOffsetFlag(order.CombOffsetFlag))
                // 委托数量
                .offerQty(order.VolumeTotalOriginal)
                // 完成数量
                .filledQty(order.VolumeTraded)
                // 委托价格
                .offerPrice(order.LimitPrice)
                // 报单日期 + 委托时间
                .offerTime(removeNonDigits(order.InsertDate) + removeNonDigits(order.InsertTime))
                // 更新时间
                .updateTime(order.UpdateTime)
                .build();
        log.info("FtdcOrder conversion to OrderReport -> {}", event);
        return event;
    }

    /**
     * 成交回报消息转换<br>
     * <br>
     * FtdcTrade -> OrderReport
     *
     * @param trade FtdcTrade
     * @return OrderReport
     */
    public OrderReport withFtdcTrade(FtdcTrade trade) {
        var orderRef = trade.OrderRef;
        long ordSysId = orderRefAllocator.getOrdSysId(orderRef);
        var event = OrderReport.builder()
                // 微秒时间戳
                .epochMicros(micros())
                // OrdSysId
                .ordSysId(ordSysId)
                // 交易日
                .tradingDay(parseInt(trade.TradingDay))
                // 投资者ID
                .investorId(trade.InvestorID)
                // 报单引用
                .orderRef(orderRef)
                // 报单编号
                .brokerOrdSysId(trade.OrderSysID)
                // 交易所
                .exchangeCode(trade.ExchangeID)
                // 合约代码
                .instrumentCode(trade.InstrumentID)
                // 报单状态
                .status(OrdStatus.UNPROVIDED)
                // 买卖方向
                .direction(withFtdcDirection((char) trade.Direction))
                // 组合开平标志
                .action(withFtdcOffsetFlag((char) trade.OffsetFlag))
                // 完成数量
                .filledQty(trade.Volume)
                // 成交价格
                .tradePrice(trade.Price)
                // 最后修改时间
                .updateTime(removeNonDigits(trade.TradeDate) + removeNonDigits(trade.TradeTime))
                .build();
        log.info("FtdcTrade conversion to OrderEvent -> {}", event);
        return event;
    }

    /**
     * 撤单错误回报消息转换1<br>
     * <br>
     * FtdcInputOrderAction -> OrderReport
     *
     * @param inputOrderAction FtdcInputOrderAction
     * @return OrderReport
     */
    public OrderReport withFtdcInputOrderAction(FtdcInputOrderAction inputOrderAction) {
        log.warn("FtdcInputOrderAction conversion to OrderEvent no support");
        return null;
    }

    /**
     * 撤单错误回报消息转换2<br>
     * <br>
     * FtdcOrderAction -> OrderReport
     *
     * @param orderAction FtdcOrderAction
     * @return OrderReport
     */
    public OrderReport withFtdcOrderAction(FtdcOrderAction orderAction) {
        log.warn("FtdcOrderAction conversion to OrderEvent no support");
        return null;
    }

}
