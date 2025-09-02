package io.cygnux.rapid.adaptor.ctp;

import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.cygnux.rapid.adaptor.ctp.consts.FtdcActionFlag;
import io.cygnux.rapid.adaptor.ctp.consts.FtdcContingentCondition;
import io.cygnux.rapid.adaptor.ctp.consts.FtdcDirection;
import io.cygnux.rapid.adaptor.ctp.consts.FtdcForceCloseReason;
import io.cygnux.rapid.adaptor.ctp.consts.FtdcHedgeFlag;
import io.cygnux.rapid.adaptor.ctp.consts.FtdcOffsetFlag;
import io.cygnux.rapid.adaptor.ctp.consts.FtdcOrderPrice;
import io.cygnux.rapid.adaptor.ctp.consts.FtdcTimeCondition;
import io.cygnux.rapid.adaptor.ctp.consts.FtdcVolumeCondition;
import io.cygnux.rapid.adaptor.ctp.param.FtdcParams;
import io.cygnux.rapid.core.adaptor.event.CancelOrder;
import io.cygnux.rapid.core.adaptor.event.NewOrder;
import lombok.Setter;
import org.rationalityfrontline.jctp.CThostFtdcInputOrderActionField;
import org.rationalityfrontline.jctp.CThostFtdcInputOrderField;
import org.slf4j.Logger;

/**
 * FtdcOrderConverter
 *
 * @author yellow013
 */
public final class FtdcReqConverter {

    private static final Logger log = Log4j2LoggerFactory.getLogger(FtdcReqConverter.class);

    // 经纪公司代码
    private final String brokerId;

    // 投资者代码
    private final String investorId;

    // 资金账号
    private final String accountId;

    // 用户代码
    private final String userId;

    // IP地址
    private final String ipAddress;

    // MAC地址
    private final String macAddress;

    @Setter
    private String tradingDay;

    FtdcReqConverter(FtdcParams params) {
        this.brokerId = params.getBrokerId();
        this.investorId = params.getInvestorId();
        this.accountId = params.getAccountId();
        this.userId = params.getUserId();
        this.ipAddress = params.getIpAddr();
        this.macAddress = params.getMacAddr();
        log.info("FtdcOrderConverter initialized, brokerId=={}, investorId=={}, accountId=={}, userId=={}",
                brokerId, investorId, accountId, userId);
    }

    /**
     * 订单转换为FTDC报单录入请求, 返回 CThostFtdcInputOrderField 对象
     *
     * @param order NewOrder
     * @return <pre>
     * struct CThostFtdcInputOrderField<br>
     * {<br>
     * ///经纪公司代码<br>
     * TThostFtdcBrokerIDType BrokerID;<br>
     * ///投资者代码<br>
     * TThostFtdcInvestorIDType InvestorID;<br>
     * ///合约代码<br>
     * TThostFtdcInstrumentIDType InstrumentID;<br>
     * ///报单引用<br>
     * TThostFtdcOrderRefType OrderRef;<br>
     * ///用户代码<br>
     * TThostFtdcUserIDType UserID;<br>
     * ///报单价格条件<br>
     * TThostFtdcOrderPriceTypeType OrderPriceType;<br>
     * ///买卖方向<br>
     * TThostFtdcDirectionType Direction;<br>
     * ///组合开平标志<br>
     * TThostFtdcCombOffsetFlagType CombOffsetFlag;<br>
     * ///组合投机套保标志<br>
     * TThostFtdcCombHedgeFlagType CombHedgeFlag;<br>
     * ///价格<br>
     * TThostFtdcPriceType LimitPrice;<br>
     * ///数量<br>
     * TThostFtdcVolumeType VolumeTotalOriginal;<br>
     * ///有效期类型<br>
     * TThostFtdcTimeConditionType TimeCondition;<br>
     * ///GTD日期<br>
     * TThostFtdcDateType GTDDate;<br><br>
     * ///成交量类型<br>
     * TThostFtdcVolumeConditionType VolumeCondition;<br>
     * ///最小成交量<br>
     * TThostFtdcVolumeType MinVolume;<br>
     * ///触发条件<br>
     * TThostFtdcContingentConditionType ContingentCondition;<br>
     * ///止损价<br>
     * TThostFtdcPriceType StopPrice;<br>
     * ///强平原因<br>
     * TThostFtdcForceCloseReasonType ForceCloseReason;<br>
     * ///自动挂起标志<br>
     * TThostFtdcBoolType IsAutoSuspend;<br>
     * ///业务单元<br>
     * TThostFtdcBusinessUnitType BusinessUnit;<br>
     * ///请求编号<br>
     * TThostFtdcRequestIDType RequestID;<br>
     * ///用户强评标志<br>
     * TThostFtdcBoolType UserForceClose;<br>
     * ///互换单标志<br>
     * TThostFtdcBoolType IsSwapOrder;<br>
     * ///交易所代码<br>
     * TThostFtdcExchangeIDType ExchangeID;<br>
     * ///投资单元代码<br>
     * TThostFtdcInvestUnitIDType InvestUnitID;<br>
     * ///资金账号<br>
     * TThostFtdcAccountIDType AccountID;<br>
     * ///币种代码<br>
     * TThostFtdcCurrencyIDType CurrencyID;<br>
     * ///交易编码<br>
     * TThostFtdcClientIDType ClientID;<br>
     * ///IP地址<br>
     * TThostFtdcIPAddressType IPAddress;<br>
     * ///MAC地址<br>
     * TThostFtdcMacAddressType MacAddress;<br>
     * };<br>
     *         </pre>
     */
    public CThostFtdcInputOrderField convertTo(NewOrder order) throws IllegalArgumentException {
        // 创建FTDC报单类型
        var inputOrderField = new CThostFtdcInputOrderField();
        // 经纪公司代码
        inputOrderField.setBrokerID(brokerId);
        // 投资者代码
        inputOrderField.setInvestorID(investorId);
        // 资金账号
        inputOrderField.setAccountID(accountId);
        // 用户代码
        inputOrderField.setUserID(userId);
        // IP地址
        inputOrderField.setIPAddress(ipAddress);
        // MAC地址
        inputOrderField.setMacAddress(macAddress);
        // 设置交易所ID
        inputOrderField.setExchangeID(order.getExchangeCode());
        log.info("Set CThostFtdcInputOrderField -> ExchangeID == {}", order.getExchangeCode());
        // 设置交易标的
        inputOrderField.setInstrumentID(order.getInstrumentCode());
        log.info("Set CThostFtdcInputOrderField -> InstrumentID == {}", order.getInstrumentCode());
        // 设置报单价格
        inputOrderField.setOrderPriceType(FtdcOrderPrice.LIMIT_PRICE);
        log.info("Set CThostFtdcInputOrderField -> OrderPriceType == LimitPrice");
        // 设置开平标识
        switch (order.getAction()) {
            case OPEN -> {
                // 设置为开仓
                inputOrderField.setCombOffsetFlag(FtdcOffsetFlag.OPEN_STR);
                log.info("Set CThostFtdcInputOrderField -> CombOffsetFlag == Open");
            }
            case CLOSE -> {
                // 设置为平仓
                inputOrderField.setCombOffsetFlag(FtdcOffsetFlag.CLOSE_STR);
                log.info("Set CThostFtdcInputOrderField -> CombOffsetFlag == Close");
            }
            case CLOSE_TODAY -> {
                // 设置为平今仓
                inputOrderField.setCombOffsetFlag(FtdcOffsetFlag.CLOSE_TODAY_STR);
                log.info("Set CThostFtdcInputOrderField -> CombOffsetFlag == CloseToday");
            }
            case CLOSE_YESTERDAY -> {
                // 设置为平昨仓
                inputOrderField.setCombOffsetFlag(FtdcOffsetFlag.CLOSE_YESTERDAY_STR);
                log.info("Set CThostFtdcInputOrderField -> CombOffsetFlag == CloseYesterday");
            }
            case INVALID -> {
                // 无效订单动作
                log.error("Order action is invalid, ordSysId==[{}]", order.getOrdSysId());
                throw new IllegalArgumentException("order action is invalid -> ordSysId == " + order.getOrdSysId());
            }
        }
        // 设置投机标识
        inputOrderField.setCombHedgeFlag(FtdcHedgeFlag.SPECULATION_STR);
        log.info("Set CThostFtdcInputOrderField -> CombHedgeFlag == Speculation");
        // 设置买卖方向
        switch (order.getDirection()) {
            case LONG -> {
                // 设置为买单
                inputOrderField.setDirection(FtdcDirection.BUY);
                log.info("Set CThostFtdcInputOrderField -> Direction == Buy");
            }
            case SHORT -> {
                // 设置为卖单
                inputOrderField.setDirection(FtdcDirection.SELL);
                log.info("Set CThostFtdcInputOrderField -> Direction == Sell");
            }
            case INVALID -> {
                // 无效订单方向
                log.error("Order direction is invalid, ordSysId==[{}]", order.getOrdSysId());
                throw new IllegalArgumentException("order direction is invalid -> ordSysId == " + order.getOrdSysId());
            }
        }
        // 设置价格
        double limitPrice = order.getOfferPrice();
        inputOrderField.setLimitPrice(limitPrice);
        log.info("Set CThostFtdcInputOrderField -> LimitPrice == {}", limitPrice);
        // 设置数量
        int volumeTotalOriginal = order.getOfferQty();
        inputOrderField.setVolumeTotalOriginal(volumeTotalOriginal);
        log.info("Set CThostFtdcInputOrderField -> VolumeTotalOriginal == {}", volumeTotalOriginal);
        // 设置有效期类型
        inputOrderField.setTimeCondition(FtdcTimeCondition.GFD);
        log.info("Set CThostFtdcInputOrderField -> TimeCondition == GFD");
        // 设置成交量类型
        inputOrderField.setVolumeCondition(FtdcVolumeCondition.AV);
        log.info("Set CThostFtdcInputOrderField -> VolumeCondition == AV");
        // 设置最小成交数量, 默认为1
        inputOrderField.setMinVolume(1);
        log.info("Set CThostFtdcInputOrderField -> MinVolume == 1");
        // 设置触发条件
        inputOrderField.setContingentCondition(FtdcContingentCondition.IMMEDIATELY);
        log.info("Set CThostFtdcInputOrderField -> ContingentCondition == Immediately");
        // 设置止损价格
        inputOrderField.setStopPrice(0.0D);
        log.info("Set CThostFtdcInputOrderField -> StopPrice == 0.0D");
        // 设置强平原因: 此处固定为非强平
        inputOrderField.setForceCloseReason(FtdcForceCloseReason.NOT_FORCE_CLOSE);
        log.info("Set CThostFtdcInputOrderField -> ForceCloseReason == NotForceClose");
        // 设置自动挂起标识
        inputOrderField.setIsAutoSuspend(0);
        log.info("Set CThostFtdcInputOrderField -> IsAutoSuspend == 0");
        // 返回FTDC新订单对象
        log.info("Set CThostFtdcInputOrderField finished");
        return inputOrderField;
    }

    /**
     * 订单转换为FTDC报单操作请求, 返回 CThostFtdcInputOrderActionField 对象
     *
     * @param order CancelOrder
     * @return <pre>
     * struct CThostFtdcInputOrderActionField<br>
     * {<br>
     * ///经纪公司代码<br>
     * TThostFtdcBrokerIDType BrokerID;<br>
     * ///投资者代码<br>
     * TThostFtdcInvestorIDType InvestorID;<br>
     * ///报单操作引用<br>
     * TThostFtdcOrderActionRefType OrderActionRef;<br>
     * ///报单引用<br>
     * TThostFtdcOrderRefType OrderRef;<br>
     * ///请求编号<br>
     * TThostFtdcRequestIDType RequestID;<br>
     * ///前置编号<br>
     * TThostFtdcFrontIDType FrontID;<br>
     * ///会话编号<br>
     * TThostFtdcSessionIDType SessionID;<br>
     * ///交易所代码<br>
     * TThostFtdcExchangeIDType ExchangeID;<br>
     * ///报单编号<br>
     * TThostFtdcOrderSysIDType OrderSysID;<br>
     * ///操作标志<br>
     * TThostFtdcActionFlagType ActionFlag;<br>
     * ///价格<br>
     * TThostFtdcPriceType LimitPrice;<br>
     * ///数量变化<br>
     * TThostFtdcVolumeType VolumeChange;<br>
     * ///用户代码<br>
     * TThostFtdcUserIDType UserID;<br>
     * ///合约代码<br>
     * TThostFtdcInstrumentIDType InstrumentID;<br>
     * ///投资单元代码<br>
     * TThostFtdcInvestUnitIDType InvestUnitID;<br>
     * ///IP地址<br>
     * TThostFtdcIPAddressType IPAddress;<br>
     * ///Mac地址<br>
     * TThostFtdcMacAddressType MacAddress;<br>
     * };<br>
     *         </pre>
     */
    public CThostFtdcInputOrderActionField convertTo(CancelOrder order) throws IllegalArgumentException {
        // 创建FTDC撤单类型
        var inputOrderActionField = new CThostFtdcInputOrderActionField();
        // 经纪公司代码
        inputOrderActionField.setBrokerID(brokerId);
        // 投资者代码
        inputOrderActionField.setInvestorID(investorId);
        // 用户代码
        inputOrderActionField.setUserID(userId);
        // IP地址
        inputOrderActionField.setIPAddress(ipAddress);
        // MAC地址
        inputOrderActionField.setMacAddress(macAddress);
        // 操作标志
        inputOrderActionField.setActionFlag(FtdcActionFlag.DELETE);
        // 交易所代码
        inputOrderActionField.setExchangeID(order.getExchangeCode());
        // 合约代码
        inputOrderActionField.setInstrumentID(order.getInstrumentCode());
        // 返回FTDC撤单对象
        log.info("Set CThostFtdcInputOrderActionField finished");
        return inputOrderActionField;
    }

}
