package io.cygnux.rapid.engine.position;

import io.mercury.common.collections.MutableMaps;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.mercury.common.util.BitOperator;
import io.mercury.serialization.json.JsonWriter;
import io.cygnux.rapid.core.event.enums.TrdDirection;
import io.cygnux.rapid.core.instrument.Instrument;
import io.cygnux.rapid.core.order.impl.ChildOrder;
import org.eclipse.collections.api.map.primitive.MutableLongIntMap;
import org.slf4j.Logger;

import javax.annotation.concurrent.NotThreadSafe;
import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;

import static java.lang.Math.abs;

/**
 * 统一管理子账户仓位信息<br>
 * 1更新仓位的入口<br>
 * 2记录子账号的仓位信息<br>
 *
 * @author yellow013
 */
@NotThreadSafe
public final class PositionKeeper implements Serializable {

    @Serial
    private static final long serialVersionUID = -23036653515185236L;

    /**
     * Logger
     */
    private static final Logger log = Log4j2LoggerFactory.getLogger(PositionKeeper.class);

    /**
     * [subAccount]的[instrument]持仓数量<br>
     * 使用mergeId作为主键<br>
     * 高位subAccountId<br>
     * 低位instrumentId
     */
    private static final MutableLongIntMap SubAccountInstrumentPos = MutableMaps.newLongIntMap();

    /**
     * [subAccount]的[instrument]最大多仓持仓限制<br>
     * 使用mergeId作为主键<br>
     * 高位subAccountId<br>
     * 低位instrumentId
     */
    private static final MutableLongIntMap SubAccountInstrumentLongLimit = MutableMaps.newLongIntMap();

    /**
     * [subAccount]的[instrument]最大空仓持仓限制<br>
     * 使用mergeId作为主键<br>
     * 高位subAccountId<br>
     * 低位instrumentId
     */
    private static final MutableLongIntMap SubAccountInstrumentShortLimit = MutableMaps.newLongIntMap();

    /**
     * [account]的[instrument]持仓数量<br>
     * 使用mergeId作为主键<br>
     * 高位accountId<br>
     * 低位instrumentId
     */
    private static final MutableLongIntMap AccountInstrumentPos = MutableMaps.newLongIntMap();

    /**
     * [account]的[instrument]最大多仓持仓限制<br>
     * 使用mergeId作为主键<br>
     * 高位accountId<br>
     * 低位instrumentId
     */
    private static final MutableLongIntMap AccountInstrumentLongLimit = MutableMaps.newLongIntMap();

    /**
     * [account]的[instrument]最大空仓持仓限制<br>
     * 使用mergeId作为主键<br>
     * 高位accountId<br>
     * 低位instrumentId
     */
    private static final MutableLongIntMap AccountInstrumentShortLimit = MutableMaps.newLongIntMap();

    private PositionKeeper() {
    }

    /**
     * 合并持仓主键
     *
     * @param highPos    subAccountId or accountId
     * @param instrument Instrument impl
     * @return long
     */
    private static long mergePositionsKey(int highPos, Instrument instrument) {
        return BitOperator.merge(highPos, instrument.getInstrumentId());
    }

    /**
     * 设置子账户最大持仓限制, 需要分别设置多空两个方向的持仓限制
     *
     * @param subAccountId  子账户ID
     * @param instrument    交易标的
     * @param longLimitQty  多仓限制
     * @param shortLimitQty 空仓限制
     */
    public static void setSubAccountPositionsLimit(int subAccountId, Instrument instrument,
                                                   int longLimitQty, int shortLimitQty) {
        long key = mergePositionsKey(subAccountId, instrument);
        SubAccountInstrumentLongLimit.put(key, abs(longLimitQty));
        log.info("Set long positions limit -> subAccountId==[{}], instrument -> {}, longLimitQty==[{}]", subAccountId,
                instrument, SubAccountInstrumentLongLimit.get(key));
        SubAccountInstrumentShortLimit.put(key, -abs(shortLimitQty));
        log.info("Set short positions limit -> subAccountId==[{}], instrument -> {}, shortLimitQty==[{}]", subAccountId,
                instrument, SubAccountInstrumentShortLimit.get(key));
    }

    /**
     * 设置账户最大持仓限制, 需要分别设置多空两个方向的持仓限制
     *
     * @param accountId     账户ID
     * @param instrument    交易标的
     * @param longLimitQty  多仓限制
     * @param shortLimitQty 空仓限制
     */
    public static void setAccountPositionsLimit(int accountId, Instrument instrument,
                                                int longLimitQty, int shortLimitQty) {
        long key = mergePositionsKey(accountId, instrument);
        AccountInstrumentLongLimit.put(key, abs(longLimitQty));
        log.info("Set long positions limit -> accountId==[{}], instrument -> {}, longLimitQty==[{}]", accountId,
                instrument, AccountInstrumentLongLimit.get(key));
        AccountInstrumentShortLimit.put(key, -abs(shortLimitQty));
        log.info("Set short positions limit -> accountId==[{}], instrument -> {}, shortLimitQty==[{}]", accountId,
                instrument, AccountInstrumentShortLimit.get(key));
    }

    /**
     * 根据已有持仓计算子账户当前持仓限制
     *
     * @param subAccountId 子账户ID
     * @param instrument   交易标的
     * @param direction    交易方向
     * @return int
     */
    public static int getSubAccountPositionLimit(int subAccountId, Instrument instrument,
                                                 TrdDirection direction) {
        long key = mergePositionsKey(subAccountId, instrument);
        int currentQty = SubAccountInstrumentPos.get(key);
        return switch (direction) {
            case LONG -> SubAccountInstrumentLongLimit.get(key) - currentQty;
            case SHORT -> SubAccountInstrumentShortLimit.get(key) - currentQty;
            default -> 0;
        };
    }

    /**
     * 根据已有持仓计算账户当前持仓限制
     *
     * @param accountId  账户ID
     * @param instrument 交易标的
     * @param direction  交易方向
     * @return int
     */
    public static int getAccountPositionLimit(int accountId, Instrument instrument,
                                              TrdDirection direction) {
        long key = mergePositionsKey(accountId, instrument);
        int currentQty = AccountInstrumentPos.get(key);
        return switch (direction) {
            case LONG -> AccountInstrumentLongLimit.get(key) - currentQty;
            case SHORT -> AccountInstrumentShortLimit.get(key) - currentQty;
            default -> 0;
        };
    }

    /**
     * 获取子账户当前头寸数量
     *
     * @param subAccountId 子账户ID
     * @param instrument   交易标的
     * @return int
     */
    public static int getSubAccountPosition(int subAccountId, Instrument instrument) {
        long positionKey = mergePositionsKey(subAccountId, instrument);
        int currentPosition = SubAccountInstrumentPos.get(positionKey);
        log.info("Get current position, subAccountId==[{}], instrumentCode==[{}], currentPosition==[{}]", subAccountId,
                instrument.getInstrumentCode(), currentPosition);
        return currentPosition;
    }

    /**
     * 获取账户当前头寸数量
     *
     * @param accountId  账户ID
     * @param instrument 交易标的
     * @return int
     */
    public static int getAccountPosition(int accountId, Instrument instrument) {
        long positionKey = mergePositionsKey(accountId, instrument);
        int currentPosition = AccountInstrumentPos.get(positionKey);
        log.info("Get current position, accountId==[{}], instrumentCode==[{}], currentPosition==[{}]",
                accountId, instrument.getInstrumentCode(), currentPosition);
        return currentPosition;
    }

    private static final String UpdatePosLogTemplate = "Update position -> "
            + "subAccountId==[{}], "
            + "instrumentCode==[{}], "
            + "beforePos==[{}], "
            + "trdQty==[{}], "
            + "currentPos==[{}]";

    /**
     * 根据子单状态变化更新持仓信息
     *
     * @param order 子订单
     */
    public static void updatePositionWith(ChildOrder order) {
        int subAccountId = order.getSubAccountId();
        Instrument instrument = order.getInstrument();
        int trdQty = order.getLastRecord().tradeQty();
        switch (order.getDirection()) {
            case LONG -> {
                switch (order.getAction()) {
                    case OPEN -> trdQty = abs(trdQty);
                    case CLOSE, CLOSE_TODAY, CLOSE_YESTERDAY -> trdQty = -abs(trdQty);
                    case INVALID ->
                            loggingInvalidAction(subAccountId, order.getOrdSysId(), instrument.getInstrumentCode());
                }
            }
            case SHORT -> {
                switch (order.getAction()) {
                    case OPEN -> trdQty = -abs(trdQty);
                    case CLOSE, CLOSE_TODAY, CLOSE_YESTERDAY -> trdQty = abs(trdQty);
                    case INVALID ->
                            loggingInvalidAction(subAccountId, order.getOrdSysId(), instrument.getInstrumentCode());
                }
            }
            case INVALID ->
                    log.error("Order direction is [Invalid], subAccountId==[{}], ordSysId==[{}], instrumentCode==[{}]",
                            subAccountId, order.getOrdSysId(), instrument.getInstrumentCode());
        }
        long posKey = mergePositionsKey(subAccountId, instrument);
        int beforePos = SubAccountInstrumentPos.get(posKey);
        int currentPos = beforePos + trdQty;
        SubAccountInstrumentPos.put(posKey, currentPos);
        log.info(UpdatePosLogTemplate, subAccountId, instrument.getInstrumentCode(), beforePos, trdQty, currentPos);
    }

    private static void loggingInvalidAction(int subAccountId, long ordSysId, String instrumentCode) {
        log.error("Order action is [Invalid], subAccountId==[{}], ordSysId==[{}], instrumentCode==[{}]",
                subAccountId, ordSysId, instrumentCode);
    }

    private static final String AddPosLogTemplate = "Add current position -> "
            + "subAccountId==[{}], "
            + "instrumentCode==[{}], "
            + "beforePos==[{}], "
            + "qty==[{}], "
            + "currentPos==[{}]";

    /**
     * 添加当前头寸
     *
     * @param subAccountId 子账户ID
     * @param instrument   交易标的
     * @param direction    方向
     * @param qty          仓位数量
     */
    public static void addPosition(int subAccountId, Instrument instrument, TrdDirection direction, int qty) {
        switch (direction) {
            case LONG -> qty = abs(qty);
            case SHORT -> qty = -abs(qty);
            case INVALID ->
                    log.warn("Add position, direction is [Invalid], subAccountId==[{}], instrumentCode==[{}], qty==[{}]",
                            subAccountId, instrument.getInstrumentCode(), qty);
        }
        long key = mergePositionsKey(subAccountId, instrument);
        int beforePos = SubAccountInstrumentPos.get(key);
        int currentPos = beforePos + qty;
        SubAccountInstrumentPos.put(key, currentPos);
        log.info(AddPosLogTemplate, subAccountId, instrument.getInstrumentCode(), beforePos, qty, currentPos);
    }

    @Override
    public String toString() {
        var map = new HashMap<String, Object>();
        map.put("SubAccountInstrumentPos", SubAccountInstrumentPos);
        map.put("SubAccountInstrumentLongLimit", SubAccountInstrumentLongLimit);
        map.put("SubAccountInstrumentShortLimit", SubAccountInstrumentShortLimit);
        return JsonWriter.toJson(map);
    }

}
