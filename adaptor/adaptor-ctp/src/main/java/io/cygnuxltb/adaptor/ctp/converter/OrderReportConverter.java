package io.cygnuxltb.adaptor.ctp.converter;

import io.cygnuxltb.adaptor.ctp.OrderRefKeeper;
import io.cygnuxltb.adaptor.ctp.gateway.rsp.FtdcInputOrder;
import io.cygnuxltb.adaptor.ctp.gateway.rsp.FtdcInputOrderAction;
import io.cygnuxltb.adaptor.ctp.gateway.rsp.FtdcOrder;
import io.cygnuxltb.adaptor.ctp.gateway.rsp.FtdcOrderAction;
import io.cygnuxltb.adaptor.ctp.gateway.rsp.FtdcTrade;
import io.cygnuxltb.jcts.core.ser.event.OrderEvent;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import org.slf4j.Logger;

import static io.cygnuxltb.adaptor.ctp.consts.FtdcConstMapper.withDirection;
import static io.cygnuxltb.adaptor.ctp.consts.FtdcConstMapper.withOffsetFlag;
import static io.cygnuxltb.adaptor.ctp.consts.FtdcConstMapper.withOrderStatus;
import static io.cygnuxltb.jcts.core.instrument.futures.ChinaFutures.FixedMultiplier;
import static io.cygnuxltb.jcts.core.ser.enums.OrdStatus.NEW_REJECTED;
import static io.cygnuxltb.jcts.core.ser.enums.OrdStatus.UNPROVIDED;
import static io.cygnuxltb.jcts.core.ser.event.OrderEvent.newBuilder;
import static io.mercury.common.datetime.EpochTime.getEpochMicros;
import static io.mercury.common.util.StringSupport.removeNonDigits;
import static java.lang.Integer.parseInt;

/**
 * OrderReportConverter
 *
 * @author yellow013
 */
public final class OrderReportConverter {

    private static final Logger log = Log4j2LoggerFactory.getLogger(OrderReportConverter.class);

    /**
     * 报单错误消息转换 <br>
     * <br>
     * FtdcInputOrder -> OrderReport
     *
     * @param order FtdcInputOrder
     * @return OrderReport
     */
    public OrderEvent withFtdcInputOrder(FtdcInputOrder order) {
        String orderRef = order.getOrderRef();
        long ordSysId = OrderRefKeeper.getOrdSysId(orderRef);
        var report = newBuilder()
                // 时间戳
                .setEpochMicros(getEpochMicros())
                // OrdSysId
                .setOrdSysId(ordSysId)
                // 投资者ID
                .setInvestorId(order.getInvestorID())
                // 报单引用
                .setOrderRef(orderRef)
                // 交易所
                .setExchangeCode(order.getExchangeID())
                // 合约代码
                .setInstrumentCode(order.getInstrumentID())
                // 报单状态
                .setStatus(NEW_REJECTED)
                // 买卖方向
                .setDirection(withDirection(order.getDirection()))
                // 组合开平标志
                .setAction(withOffsetFlag(order.getCombOffsetFlag()))
                // 委托数量
                .setOfferQty(order.getVolumeTotalOriginal())
                // 委托价格
                .setOfferPrice(FixedMultiplier.toLong(order.getLimitPrice()))
                .build();
        log.info("FtdcInputOrder conversion to OrderReport -> {}", report);
        return report;
    }

    /**
     * 订单回报消息转换<br>
     * <br>
     * FtdcOrder -> OrderReport
     *
     * @param order FtdcOrder
     * @return OrderReport
     */
    public OrderEvent withFtdcOrder(FtdcOrder order) {
        String orderRef = order.getOrderRef();
        long ordSysId = OrderRefKeeper.getOrdSysId(orderRef);
        var event = newBuilder()
                // 时间戳
                .setEpochMicros(getEpochMicros())
                // OrdSysId
                .setOrdSysId(ordSysId)
                // 交易日
                .setTradingDay(parseInt(order.getTradingDay()))
                // 投资者ID
                .setInvestorId(order.getInvestorID())
                // 报单引用
                .setOrderRef(orderRef)
                // 报单编号
                .setBrokerOrdSysId(order.getOrderSysID())
                // 交易所
                .setExchangeCode(order.getExchangeID())
                // 合约代码
                .setInstrumentCode(order.getInstrumentID())
                // 报单状态
                .setStatus(withOrderStatus(order.getOrderStatus()))
                // 买卖方向
                .setDirection(withDirection(order.getDirection()))
                // 组合开平标志
                .setAction(withOffsetFlag(order.getCombOffsetFlag()))
                // 委托数量
                .setOfferQty(order.getVolumeTotalOriginal())
                // 完成数量
                .setFilledQty(order.getVolumeTraded())
                // 委托价格
                .setOfferPrice(FixedMultiplier.toLong(order.getLimitPrice()))
                // 报单日期 + 委托时间
                .setOfferTime(removeNonDigits(order.getInsertDate()) + removeNonDigits(order.getInsertTime()))
                // 更新时间
                .setUpdateTime(order.getUpdateTime())
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
    public OrderEvent withFtdcTrade(FtdcTrade trade) {
        var orderRef = trade.getOrderRef();
        long ordSysId = OrderRefKeeper.getOrdSysId(orderRef);
        var event = newBuilder()
                // 微秒时间戳
                .setEpochMicros(getEpochMicros())
                // OrdSysId
                .setOrdSysId(ordSysId)
                // 交易日
                .setTradingDay(parseInt(trade.getTradingDay()))
                // 投资者ID
                .setInvestorId(trade.getInvestorID())
                // 报单引用
                .setOrderRef(orderRef)
                // 报单编号
                .setBrokerOrdSysId(trade.getOrderSysID())
                // 交易所
                .setExchangeCode(trade.getExchangeID())
                // 合约代码
                .setInstrumentCode(trade.getInstrumentID())
                // 报单状态
                .setStatus(UNPROVIDED)
                // 买卖方向
                .setDirection(withDirection(trade.getDirection()))
                // 组合开平标志
                .setAction(withOffsetFlag(trade.getOffsetFlag()))
                // 完成数量
                .setFilledQty(trade.getVolume())
                // 成交价格
                .setTradePrice(FixedMultiplier.toLong(trade.getPrice()))
                // 最后修改时间
                .setUpdateTime(removeNonDigits(trade.getTradeDate()) + removeNonDigits(trade.getTradeTime()))
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
    public OrderEvent withFtdcInputOrderAction(FtdcInputOrderAction inputOrderAction) {

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
    public OrderEvent withFtdcOrderAction(FtdcOrderAction orderAction) {

        return null;
    }

}
