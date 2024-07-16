package io.rapid.adaptor.ctp;

import io.mercury.common.datetime.EpochTime;
import io.mercury.common.thread.Sleep;
import org.eclipse.collections.api.map.primitive.MutableLongObjectMap;
import org.eclipse.collections.api.map.primitive.MutableObjectLongMap;
import org.slf4j.Logger;

import java.io.Serial;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;

import static io.mercury.common.collections.Capacity.L10_2048;
import static io.mercury.common.collections.MutableMaps.newLongObjectMap;
import static io.mercury.common.collections.MutableMaps.newObjectLongMap;
import static io.mercury.common.datetime.TimeZone.CST;
import static io.mercury.common.log4j2.Log4j2LoggerFactory.getLogger;
import static io.rapid.core.order.OrdSysIdAllocator.FOR_EXTERNAL_ORDER;
import static java.lang.System.currentTimeMillis;

/**
 * @author yellow013
 * <p>
 * TODO - Add Persistence
 */
public class OrderRefKeeper {

    private static final Logger log = getLogger(OrderRefKeeper.class);

    private final MutableObjectLongMap<String> orderRefMapper = newObjectLongMap(L10_2048.size());

    private final MutableLongObjectMap<String> ordSysIdMapper = newLongObjectMap(L10_2048.size());

    public OrderRefKeeper() {
    }

    public void put(String orderRef, long ordSysId) {
        log.info("PUT ORDER MAPPING orderRef==[{}] <==> ordSysId==[{}]", orderRef, ordSysId);
        orderRefMapper.put(orderRef, ordSysId);
        ordSysIdMapper.put(ordSysId, orderRef);
    }

    /**
     * @param orderRef String
     * @return long
     */
    public long getOrdSysId(String orderRef) {
        long ordSysId = orderRefMapper.get(orderRef);
        if (ordSysId == 0L) {
            // 处理其他来源的订单
            ordSysId = FOR_EXTERNAL_ORDER.getOrdSysId();
            log.warn("Handle external order, allocate external order used ordSysId==[{}], orderRef==[{}]",
                    ordSysId, orderRef);
        }
        return ordSysId;
    }

    /**
     * @param ordSysId long
     * @return String
     */
    public String getOrderRef(long ordSysId) throws OrderRefNotFoundException {
        String orderRef = ordSysIdMapper.get(ordSysId);
        if (orderRef == null)
            throw new OrderRefNotFoundException(ordSysId);
        return orderRef;
    }

    /**
     * 以<b> [PM 03:30] </b> 作为计算OrderRef的基准时间
     */
    public static final LocalTime BENCHMARK_TIME = LocalTime.of(15, 30);

    /**
     * 如果当前时间在基准时间之后, 则使用当天的基准时间;
     * 如果在基准时间之前, 则使用前一天的基准时间
     */
    public static final long BENCHMARK_POINT = EpochTime.getEpochMillis(
            ZonedDateTime.of(LocalTime.now().isBefore(BENCHMARK_TIME)
                            ? LocalDate.now().minusDays(1)
                            : LocalDate.now(),
                    BENCHMARK_TIME, CST));

    /**
     * 基于<b> Epoch时间戳与前一天基准点 </b>的偏移量计算OrderRef,
     * 保证OrderRef在同一个交易日内自增
     *
     * @return int
     */
    public int nextOrderRef() {
        return (int) (currentTimeMillis() - BENCHMARK_POINT);
    }

    public static final class OrderRefNotFoundException extends Exception {

        @Serial
        private static final long serialVersionUID = -74254388017422611L;

        private OrderRefNotFoundException(long ordSysId) {
            super("ordSysId -> [" + ordSysId + "] is not find orderRef");
        }

    }


    public static void main(String[] args) {

        var keeper = new OrderRefKeeper();

        for (int i = 0; i < 1000; i++) {
            System.out.println(keeper.nextOrderRef());
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
