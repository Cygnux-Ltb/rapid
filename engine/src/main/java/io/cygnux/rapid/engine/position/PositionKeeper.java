package io.cygnux.rapid.engine.position;

import io.cygnux.rapid.core.instrument.Instrument;
import io.cygnux.rapid.core.order.impl.ChildOrder;
import io.cygnux.rapid.core.stream.enums.TrdDirection;
import io.mercury.common.collections.MutableMaps;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.mercury.common.util.BitOperator;
import io.mercury.serialization.json.JsonWriter;
import org.eclipse.collections.api.map.primitive.MutableLongIntMap;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.concurrent.NotThreadSafe;
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
@Component
public final class PositionKeeper {

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
    private final MutableLongIntMap subAccountInstrumentPos = MutableMaps.newLongIntMap();

    /**
     * [subAccount]的[instrument]最大多仓持仓限制<br>
     * 使用mergeId作为主键<br>
     * 高位subAccountId<br>
     * 低位instrumentId
     */
    private final MutableLongIntMap subAccountInstrumentLongLimit = MutableMaps.newLongIntMap();

    /**
     * [subAccount]的[instrument]最大空仓持仓限制<br>
     * 使用mergeId作为主键<br>
     * 高位subAccountId<br>
     * 低位instrumentId
     */
    private final MutableLongIntMap subAccountInstrumentShortLimit = MutableMaps.newLongIntMap();

    /**
     * [account]的[instrument]持仓数量<br>
     * 使用mergeId作为主键<br>
     * 高位accountId<br>
     * 低位instrumentId
     */
    private final MutableLongIntMap accountInstrumentPos = MutableMaps.newLongIntMap();

    /**
     * [account]的[instrument]最大多仓持仓限制<br>
     * 使用mergeId作为主键<br>
     * 高位accountId<br>
     * 低位instrumentId
     */
    private final MutableLongIntMap accountInstrumentLongLimit = MutableMaps.newLongIntMap();

    /**
     * [account]的[instrument]最大空仓持仓限制<br>
     * 使用mergeId作为主键<br>
     * 高位accountId<br>
     * 低位instrumentId
     */
    private final MutableLongIntMap accountInstrumentShortLimit = MutableMaps.newLongIntMap();


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
    public void setSubAccountPositionsLimit(int subAccountId, Instrument instrument,
                                            int longLimitQty, int shortLimitQty) {
        long mergedKey = mergePositionsKey(subAccountId, instrument);
        subAccountInstrumentLongLimit.put(mergedKey, abs(longLimitQty));
        log.info("Set long positions limit -> subAccountId==[{}], instrument -> {}, longLimitQty==[{}]", subAccountId,
                instrument, subAccountInstrumentLongLimit.get(mergedKey));
        subAccountInstrumentShortLimit.put(mergedKey, -abs(shortLimitQty));
        log.info("Set short positions limit -> subAccountId==[{}], instrument -> {}, shortLimitQty==[{}]", subAccountId,
                instrument, subAccountInstrumentShortLimit.get(mergedKey));
    }

    /**
     * 设置账户最大持仓限制, 需要分别设置多空两个方向的持仓限制
     *
     * @param accountId     账户ID
     * @param instrument    交易标的
     * @param longLimitQty  多仓限制
     * @param shortLimitQty 空仓限制
     */
    public void setAccountPositionsLimit(int accountId, Instrument instrument,
                                         int longLimitQty, int shortLimitQty) {
        long mergedKey = mergePositionsKey(accountId, instrument);
        accountInstrumentLongLimit.put(mergedKey, abs(longLimitQty));
        log.info("Set long positions limit -> accountId==[{}], instrument -> {}, longLimitQty==[{}]", accountId,
                instrument, accountInstrumentLongLimit.get(mergedKey));
        accountInstrumentShortLimit.put(mergedKey, -abs(shortLimitQty));
        log.info("Set short positions limit -> accountId==[{}], instrument -> {}, shortLimitQty==[{}]", accountId,
                instrument, accountInstrumentShortLimit.get(mergedKey));
    }

    /**
     * 根据已有持仓计算子账户当前持仓限制
     *
     * @param subAccountId 子账户ID
     * @param instrument   交易标的
     * @param direction    交易方向
     * @return int
     */
    public int getSubAccountPositionLimit(int subAccountId, Instrument instrument,
                                          TrdDirection direction) {
        long mergedKey = mergePositionsKey(subAccountId, instrument);
        int currentQty = subAccountInstrumentPos.get(mergedKey);
        return switch (direction) {
            case LONG -> subAccountInstrumentLongLimit.get(mergedKey) - currentQty;
            case SHORT -> subAccountInstrumentShortLimit.get(mergedKey) - currentQty;
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
    public int getAccountPositionLimit(int accountId, Instrument instrument,
                                       TrdDirection direction) {
        long mergedKey = mergePositionsKey(accountId, instrument);
        int currentQty = accountInstrumentPos.get(mergedKey);
        return switch (direction) {
            case LONG -> accountInstrumentLongLimit.get(mergedKey) - currentQty;
            case SHORT -> accountInstrumentShortLimit.get(mergedKey) - currentQty;
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
    public int getSubAccountPosition(int subAccountId, Instrument instrument) {
        long mergedKey = mergePositionsKey(subAccountId, instrument);
        int currentPosition = subAccountInstrumentPos.get(mergedKey);
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
    public int getAccountPosition(int accountId, Instrument instrument) {
        long mergedKey = mergePositionsKey(accountId, instrument);
        int currentPosition = accountInstrumentPos.get(mergedKey);
        log.info("Get current position, accountId==[{}], instrumentCode==[{}], currentPosition==[{}]",
                accountId, instrument.getInstrumentCode(), currentPosition);
        return currentPosition;
    }

    /**
     * 根据子单状态变化更新持仓信息
     *
     * @param order 子订单
     */
    public void updatePositionWith(ChildOrder order) {
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
        int beforePos = subAccountInstrumentPos.get(posKey);
        int currentPos = beforePos + trdQty;
        subAccountInstrumentPos.put(posKey, currentPos);
        log.info("Update position -> subAccountId==[{}], instrumentCode==[{}], beforePos==[{}], trdQty==[{}], currentPos==[{}]",
                subAccountId, instrument.getInstrumentCode(), beforePos, trdQty, currentPos);
    }

    private static void loggingInvalidAction(int subAccountId, long ordSysId, String instrumentCode) {
        log.error("Order action is [Invalid], subAccountId==[{}], ordSysId==[{}], instrumentCode==[{}]",
                subAccountId, ordSysId, instrumentCode);
    }

    /**
     * 添加当前头寸
     *
     * @param subAccountId 子账户ID
     * @param instrument   交易标的
     * @param direction    方向
     * @param qty          仓位数量
     */
    public void addPosition(int subAccountId, Instrument instrument, TrdDirection direction, int qty) {
        switch (direction) {
            case LONG -> qty = abs(qty);
            case SHORT -> qty = -abs(qty);
            case INVALID ->
                    log.warn("Add position, direction is [Invalid], subAccountId==[{}], instrumentCode==[{}], qty==[{}]",
                            subAccountId, instrument.getInstrumentCode(), qty);
        }
        long key = mergePositionsKey(subAccountId, instrument);
        int beforePos = subAccountInstrumentPos.get(key);
        int currentPos = beforePos + qty;
        subAccountInstrumentPos.put(key, currentPos);
        log.info("Add current position -> subAccountId==[{}], instrumentCode==[{}], beforePos==[{}], qty==[{}], currentPos==[{}]",
                subAccountId, instrument.getInstrumentCode(), beforePos, qty, currentPos);
    }

    @Override
    public String toString() {
        var map = new HashMap<String, Object>();
        map.put("SubAccountInstrumentPos", subAccountInstrumentPos);
        map.put("SubAccountInstrumentLongLimit", subAccountInstrumentLongLimit);
        map.put("SubAccountInstrumentShortLimit", subAccountInstrumentShortLimit);
        return JsonWriter.toJson(map);
    }

}
