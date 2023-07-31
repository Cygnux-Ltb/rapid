package io.cygnuxltb.adaptor.ctp.converter;

import ctp.thostapi.CThostFtdcInputOrderActionField;
import ctp.thostapi.CThostFtdcInputOrderField;
import io.cygnuxltb.adaptor.ctp.CtpConfiguration;
import io.cygnuxltb.adaptor.ctp.consts.FtdcConst;
import io.cygnuxltb.jcts.core.ser.req.CancelOrder;
import io.cygnuxltb.jcts.core.ser.req.NewOrder;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import org.slf4j.Logger;

/**
 * FtdcOrderConverter
 *
 * @author yellow013
 */
public final class FtdcOrderConverter {

    private static final Logger log = Log4j2LoggerFactory.getLogger(FtdcOrderConverter.class);

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

    public FtdcOrderConverter(CtpConfiguration config) {
        this.brokerId = config.getBrokerId();
        this.investorId = config.getInvestorId();
        this.accountId = config.getAccountId();
        this.userId = config.getUserId();
        this.ipAddress = config.getIpAddr();
        this.macAddress = config.getMacAddr();
        log.info("FtdcOrderConverter initialized, brokerId=={}, investorId=={}, accountId=={}, userId=={}",
                brokerId, investorId, accountId, userId);
    }

    /**
     * 订单转换为FTDC报单录入请求, 返回 CThostFtdcInputOrderField 对象
     *
     * @param request NewOrder
     * @return <pre>
     * struct CThostFtdcInputOrderField
     * {
     * ///经纪公司代码
     * TThostFtdcBrokerIDType BrokerID;
     * ///投资者代码
     * TThostFtdcInvestorIDType InvestorID;
     * ///合约代码
     * TThostFtdcInstrumentIDType InstrumentID;
     * ///报单引用
     * TThostFtdcOrderRefType OrderRef;
     * ///用户代码
     * TThostFtdcUserIDType UserID;
     * ///报单价格条件
     * TThostFtdcOrderPriceTypeType OrderPriceType;
     * ///买卖方向
     * TThostFtdcDirectionType Direction;
     * ///组合开平标志
     * TThostFtdcCombOffsetFlagType CombOffsetFlag;
     * ///组合投机套保标志
     * TThostFtdcCombHedgeFlagType CombHedgeFlag;
     * ///价格
     * TThostFtdcPriceType LimitPrice;
     * ///数量
     * TThostFtdcVolumeType VolumeTotalOriginal;
     * ///有效期类型
     * TThostFtdcTimeConditionType TimeCondition;
     * ///GTD日期
     * TThostFtdcDateType GTDDate;
     * ///成交量类型
     * TThostFtdcVolumeConditionType VolumeCondition;
     * ///最小成交量
     * TThostFtdcVolumeType MinVolume;
     * ///触发条件
     * TThostFtdcContingentConditionType ContingentCondition;
     * ///止损价
     * TThostFtdcPriceType StopPrice;
     * ///强平原因
     * TThostFtdcForceCloseReasonType ForceCloseReason;
     * ///自动挂起标志
     * TThostFtdcBoolType IsAutoSuspend;
     * ///业务单元
     * TThostFtdcBusinessUnitType BusinessUnit;
     * ///请求编号
     * TThostFtdcRequestIDType RequestID;
     * ///用户强评标志
     * TThostFtdcBoolType UserForceClose;
     * ///互换单标志
     * TThostFtdcBoolType IsSwapOrder;
     * ///交易所代码
     * TThostFtdcExchangeIDType ExchangeID;
     * ///投资单元代码
     * TThostFtdcInvestUnitIDType InvestUnitID;
     * ///资金账号
     * TThostFtdcAccountIDType AccountID;
     * ///币种代码
     * TThostFtdcCurrencyIDType CurrencyID;
     * ///交易编码
     * TThostFtdcClientIDType ClientID;
     * ///IP地址
     * TThostFtdcIPAddressType IPAddress;
     * ///MAC地址
     * TThostFtdcMacAddressType MacAddress;
     * };
     *         </pre>
     */
    public CThostFtdcInputOrderField convertToInputOrder(NewOrder request) {
        // 创建FTDC报单类型
        var field = new CThostFtdcInputOrderField();
        // 经纪公司代码
        field.setBrokerID(brokerId);
        // 投资者代码
        field.setInvestorID(investorId);
        // 资金账号
        field.setAccountID(accountId);
        // 用户代码
        field.setUserID(userId);
        // IP地址
        field.setIPAddress(ipAddress);
        // MAC地址
        field.setMacAddress(macAddress);
        // 设置交易所ID
        field.setExchangeID(request.getExchangeCode());
        log.info("Set CThostFtdcInputOrderField -> ExchangeID == {}", request.getExchangeCode());
        // 设置交易标的
        field.setInstrumentID(request.getInstrumentCode());
        log.info("Set CThostFtdcInputOrderField -> InstrumentID == {}", request.getInstrumentCode());
        // 设置报单价格
        field.setOrderPriceType(FtdcConst.FtdcOrderPrice.LIMIT_PRICE);
        log.info("Set CThostFtdcInputOrderField -> OrderPriceType == LimitPrice");
        // 设置开平标识
        switch (request.getAction()) {
            case OPEN -> {
                // 设置为开仓
                field.setCombOffsetFlag(FtdcConst.FtdcOffsetFlag.OPEN_STR);
                log.info("Set CThostFtdcInputOrderField -> CombOffsetFlag == Open");
            }
            case CLOSE -> {
                // 设置为平仓
                field.setCombOffsetFlag(FtdcConst.FtdcOffsetFlag.CLOSE_STR);
                log.info("Set CThostFtdcInputOrderField -> CombOffsetFlag == Close");
            }
            case CLOSE_TODAY -> {
                // 设置为平今仓
                field.setCombOffsetFlag(FtdcConst.FtdcOffsetFlag.CLOSE_TODAY_STR);
                log.info("Set CThostFtdcInputOrderField -> CombOffsetFlag == CloseToday");
            }
            case CLOSE_YESTERDAY -> {
                // 设置为平昨仓
                field.setCombOffsetFlag(FtdcConst.FtdcOffsetFlag.CLOSE_YESTERDAY_STR);
                log.info("Set CThostFtdcInputOrderField -> CombOffsetFlag == CloseYesterday");
            }
            case INVALID -> {
                // 无效订单动作
                log.error("request action is invalid, ordSysId==[{}]", request.getOrdSysId());
                throw new IllegalStateException("request action is invalid -> ordSysId == " + request.getOrdSysId());
            }
        }
        // 设置投机标识
        field.setCombHedgeFlag(FtdcConst.FtdcHedgeFlag.SPECULATION_STR);
        log.info("Set CThostFtdcInputOrderField -> CombHedgeFlag == Speculation");
        // 设置买卖方向
        switch (request.getDirection()) {
            case LONG -> {
                // 设置为买单
                field.setDirection(FtdcConst.FtdcDirection.BUY);
                log.info("Set CThostFtdcInputOrderField -> Direction == Buy");
            }
            case SHORT -> {
                // 设置为卖单
                field.setDirection(FtdcConst.FtdcDirection.SELL);
                log.info("Set CThostFtdcInputOrderField -> Direction == Sell");
            }
            case INVALID -> {
                // 无效订单方向
                log.error("request direction is invalid, ordSysId==[{}]", request.getOrdSysId());
                throw new IllegalStateException("request direction is invalid -> ordSysId == " + request.getOrdSysId());
            }
        }
        // 设置价格
        double limitPrice = request.getOfferPrice();
        field.setLimitPrice(limitPrice);
        log.info("Set CThostFtdcInputOrderField -> LimitPrice == {}", limitPrice);
        // 设置数量
        int volumeTotalOriginal = request.getOfferQty();
        field.setVolumeTotalOriginal(volumeTotalOriginal);
        log.info("Set CThostFtdcInputOrderField -> VolumeTotalOriginal == {}", volumeTotalOriginal);
        // 设置有效期类型
        field.setTimeCondition(FtdcConst.FtdcTimeCondition.GFD);
        log.info("Set CThostFtdcInputOrderField -> TimeCondition == GFD");
        // 设置成交量类型
        field.setVolumeCondition(FtdcConst.FtdcVolumeCondition.AV);
        log.info("Set CThostFtdcInputOrderField -> VolumeCondition == AV");
        // 设置最小成交数量, 默认为1
        field.setMinVolume(1);
        log.info("Set CThostFtdcInputOrderField -> MinVolume == 1");
        // 设置触发条件
        field.setContingentCondition(FtdcConst.FtdcContingentCondition.IMMEDIATELY);
        log.info("Set CThostFtdcInputOrderField -> ContingentCondition == Immediately");
        // 设置止损价格
        field.setStopPrice(0.0D);
        log.info("Set CThostFtdcInputOrderField -> StopPrice == 0.0D");
        // 设置强平原因: 此处固定为非强平
        field.setForceCloseReason(FtdcConst.FtdcForceCloseReason.NOT_FORCE_CLOSE);
        log.info("Set CThostFtdcInputOrderField -> ForceCloseReason == NotForceClose");
        // 设置自动挂起标识
        field.setIsAutoSuspend(0);
        log.info("Set CThostFtdcInputOrderField -> IsAutoSuspend == 0");
        // 返回FTDC新订单对象
        log.info("Set CThostFtdcInputOrderField finished");
        return field;
    }

    /**
     * 订单转换为FTDC报单操作请求, 返回 CThostFtdcInputOrderActionField 对象
     *
     * @param request CancelOrder
     * @return <pre>
     * struct CThostFtdcInputOrderActionField
     * {
     * ///经纪公司代码
     * TThostFtdcBrokerIDType BrokerID;
     * ///投资者代码
     * TThostFtdcInvestorIDType InvestorID;
     * ///报单操作引用
     * TThostFtdcOrderActionRefType OrderActionRef;
     * ///报单引用
     * TThostFtdcOrderRefType OrderRef;
     * ///请求编号
     * TThostFtdcRequestIDType RequestID;
     * ///前置编号
     * TThostFtdcFrontIDType FrontID;
     * ///会话编号
     * TThostFtdcSessionIDType SessionID;
     * ///交易所代码
     * TThostFtdcExchangeIDType ExchangeID;
     * ///报单编号
     * TThostFtdcOrderSysIDType OrderSysID;
     * ///操作标志
     * TThostFtdcActionFlagType ActionFlag;
     * ///价格
     * TThostFtdcPriceType LimitPrice;
     * ///数量变化
     * TThostFtdcVolumeType VolumeChange;
     * ///用户代码
     * TThostFtdcUserIDType UserID;
     * ///合约代码
     * TThostFtdcInstrumentIDType InstrumentID;
     * ///投资单元代码
     * TThostFtdcInvestUnitIDType InvestUnitID;
     * ///IP地址
     * TThostFtdcIPAddressType IPAddress;
     * ///Mac地址
     * TThostFtdcMacAddressType MacAddress;
     * };
     *         </pre>
     */
    public CThostFtdcInputOrderActionField convertToInputOrderAction(CancelOrder request) {
        // 创建FTDC撤单类型
        var field = new CThostFtdcInputOrderActionField();
        // 经纪公司代码
        field.setBrokerID(brokerId);
        // 投资者代码
        field.setInvestorID(investorId);
        // 用户代码
        field.setUserID(userId);
        // IP地址
        field.setIPAddress(ipAddress);
        // MAC地址
        field.setMacAddress(macAddress);
        // 操作标志
        field.setActionFlag(FtdcConst.FtdcActionFlag.DELETE);
        // 交易所代码
        field.setExchangeID(request.getExchangeCode());
        // 合约代码
        field.setInstrumentID(request.getInstrumentCode());
        // 返回FTDC撤单对象
        log.info("Set CThostFtdcInputOrderActionField finished");
        return field;
    }

}
