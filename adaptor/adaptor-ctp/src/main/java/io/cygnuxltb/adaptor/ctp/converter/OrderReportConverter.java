package io.cygnuxltb.adaptor.ctp.converter;

import io.cygnuxltb.adaptor.ctp.OrderRefKeeper;
import io.cygnuxltb.adaptor.ctp.consts.FtdcConstMapper;
import io.cygnuxltb.adaptor.ctp.gateway.rsp.FtdcInputOrder;
import io.cygnuxltb.adaptor.ctp.gateway.rsp.FtdcInputOrderAction;
import io.cygnuxltb.adaptor.ctp.gateway.rsp.FtdcOrder;
import io.cygnuxltb.adaptor.ctp.gateway.rsp.FtdcOrderAction;
import io.cygnuxltb.adaptor.ctp.gateway.rsp.FtdcTrade;
import io.horizon.trader.serialization.avro.receive.AvroOrderEvent;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import org.slf4j.Logger;

import static io.horizon.market.instrument.futures.ChinaFutures.FixedMultiplier;
import static io.horizon.trader.order.enums.OrdStatus.NewRejected;
import static io.horizon.trader.order.enums.OrdStatus.Unprovided;
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
    public AvroOrderEvent withFtdcInputOrder(FtdcInputOrder order) {
        String orderRef = order.getOrderRef();
        long ordSysId = OrderRefKeeper.getOrdSysId(orderRef);
        var report = AvroOrderEvent.newBuilder()
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
                .setStatus(NewRejected.getTdxValue())
                // 买卖方向
                .setDirection(FtdcConstMapper.withDirection(order.getDirection()).getTdxValue())
                // 组合开平标志
                .setAction(FtdcConstMapper.withOffsetFlag(order.getCombOffsetFlag()).getTdxValue())
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
    public AvroOrderEvent withFtdcOrder(FtdcOrder order) {
        String orderRef = order.getOrderRef();
        long ordSysId = OrderRefKeeper.getOrdSysId(orderRef);
        var event = AvroOrderEvent.newBuilder()
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
                .setStatus(FtdcConstMapper.withOrderStatus(order.getOrderStatus()).getTdxValue())
                // 买卖方向
                .setDirection(FtdcConstMapper.withDirection(order.getDirection()).getTdxValue())
                // 组合开平标志
                .setAction(FtdcConstMapper.withOffsetFlag(order.getCombOffsetFlag()).getTdxValue())
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
    public AvroOrderEvent withFtdcTrade(FtdcTrade trade) {
        var orderRef = trade.getOrderRef();
        long ordSysId = OrderRefKeeper.getOrdSysId(orderRef);
        var builder = AvroOrderEvent.newBuilder();
        // 微秒时间戳
        builder.setEpochMicros(getEpochMicros());
        // OrdSysId
        builder.setOrdSysId(ordSysId);
        // 交易日
        builder.setTradingDay(parseInt(trade.getTradingDay()));
        // 投资者ID
        builder.setInvestorId(trade.getInvestorID());
        // 报单引用
        builder.setOrderRef(orderRef);
        // 报单编号
        builder.setBrokerOrdSysId(trade.getOrderSysID());
        // 交易所
        builder.setExchangeCode(trade.getExchangeID());
        // 合约代码
        builder.setInstrumentCode(trade.getInstrumentID());
        // 报单状态
        builder.setStatus(Unprovided.getTdxValue());
        // 买卖方向
        var direction = FtdcConstMapper.withDirection(trade.getDirection());
        builder.setDirection(direction.getTdxValue());
        // 组合开平标志
        var action = FtdcConstMapper.withOffsetFlag(trade.getOffsetFlag());
        builder.setAction(action.getTdxValue());
        // 完成数量
        builder.setFilledQty(trade.getVolume());
        // 成交价格
        builder.setTradePrice(FixedMultiplier.toLong(trade.getPrice()));
        // 最后修改时间
        builder.setUpdateTime(removeNonDigits(trade.getTradeDate()) + removeNonDigits(trade.getTradeTime()));

        var event = builder.build();
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
    public AvroOrderEvent withFtdcInputOrderAction(FtdcInputOrderAction inputOrderAction) {

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
    public AvroOrderEvent withFtdcOrderAction(FtdcOrderAction orderAction) {

        return null;
    }

}
