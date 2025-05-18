package io.rapid.core.order;

import io.mercury.common.epoch.EpochTimeUtil;
import io.mercury.common.thread.Sleep;
import org.eclipse.collections.api.map.primitive.MutableLongObjectMap;
import org.eclipse.collections.api.map.primitive.MutableObjectLongMap;
import org.slf4j.Logger;

import javax.annotation.concurrent.ThreadSafe;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;

import static io.mercury.common.collections.Capacity.HEX_1_000;
import static io.mercury.common.collections.MutableMaps.newLongObjectMap;
import static io.mercury.common.collections.MutableMaps.newObjectLongMap;
import static io.mercury.common.datetime.TimeZone.CST;
import static io.mercury.common.log4j2.Log4j2LoggerFactory.getLogger;
import static io.rapid.core.order.OrdSysIdAllocator.FOR_EXTERNAL_ORDER;
import static java.lang.System.currentTimeMillis;

/**
 * Next step adds persistence
 *
 * @author yellow013
 * <p>
 *
 */
@ThreadSafe
public class OrderRefKeeperInHeap implements OrderRefKeeper {

    private static final Logger log = getLogger(OrderRefKeeperInHeap.class);

    private final MutableObjectLongMap<String> orderRefMap = newObjectLongMap(HEX_1_000.size());

    private final MutableLongObjectMap<String> ordSysIdMap = newLongObjectMap(HEX_1_000.size());

    OrderRefKeeperInHeap() {
    }

    @Override
    public synchronized void binding(String orderRef, long ordSysId) {
        log.info("REF_KEEPER ORDER BINDING >>>> orderRef==[{}] <==> ordSysId==[{}]", orderRef, ordSysId);
        orderRefMap.put(orderRef, ordSysId);
        ordSysIdMap.put(ordSysId, orderRef);
    }

    /**
     * @param orderRef String
     * @return long
     */
    @Override
    public synchronized long getOrdSysId(String orderRef) {
        long ordSysId = orderRefMap.get(orderRef);
        if (ordSysId == 0L) {
            // 处理其他来源的订单
            ordSysId = FOR_EXTERNAL_ORDER.nextOrdSysId();
            log.warn("Handle external order, allocate external order used ordSysId==[{}], orderRef==[{}]",
                    ordSysId, orderRef);
            binding(orderRef, ordSysId);
        }
        return ordSysId;
    }

    /**
     * @param ordSysId long
     * @return String
     */
    public synchronized String getOrderRef(long ordSysId) throws OrderRefNotFoundException {
        String orderRef = ordSysIdMap.get(ordSysId);
        if (orderRef == null)
            throw new OrderRefNotFoundException(ordSysId);
        return orderRef;
    }

    /**
     * 以<b> [PM 03:30] </b> 作为计算OrderRef的基准时间
     */
    public static final LocalTime BASELINE_TIME = LocalTime.of(15, 30);

    /**
     * 如果当前时间在基准时间之后, 则使用当天的基准时间;
     * 如果在基准时间之前, 则使用前一天的基准时间
     */
    public static final long EPOCH_BASELINE_POINT = EpochTimeUtil.getEpochMillis(
            ZonedDateTime.of(LocalTime.now().isBefore(BASELINE_TIME)
                            ? LocalDate.now().minusDays(1)
                            : LocalDate.now(),
                    BASELINE_TIME, CST));

    /**
     * 基于<b> Epoch时间戳与前一天基准点 </b>的偏移量计算OrderRef,
     * 保证OrderRef在同一个交易日内自增
     *
     * @return int
     */
    public int nextOrderRef() {
        return (int) (currentTimeMillis() - EPOCH_BASELINE_POINT);
    }

    public static void main(String[] args) {

        var allocator = new OrderRefKeeperInHeap();

        for (int i = 0; i < 1000; i++) {
            System.out.println(allocator.nextOrderRef());
            Sleep.millis(2);
        }

        Duration duration = Duration
                .between(ZonedDateTime.of(LocalDate.now().minusDays(1),
                                LocalTime.of(17, 0), CST),
                        ZonedDateTime.of(LocalDate.now(),
                                LocalTime.of(17, 0), CST));
        System.out.println(duration.getSeconds() * 1000);
        System.out.println(Integer.MAX_VALUE);

    }

}
