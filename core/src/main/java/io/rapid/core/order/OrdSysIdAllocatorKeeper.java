package io.rapid.core.order;

import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.mercury.common.sequence.SnowflakeAlgo;
import org.slf4j.Logger;

import javax.annotation.concurrent.ThreadSafe;

/**
 * Generate规则<br>
 * <br>
 * A方案<br>
 * 1.获取当前epoch秒<br>
 * 2.如果是同一秒内生成的两个id, 则自增位加一<br>
 * <br>
 * B方案<br>
 * 1.使用一个固定日期作为基准<br>
 * 2.使用一个较长的自增位<br>
 * <br>
 * C方案<br>
 * 1.使用位运算合并long类型, 分配64位<br>
 * 2.最高位使用strategyId <br>
 * <br>
 * 当前实现为方案A<br>
 *
 * @author yellow013
 * @creation 2019年4月13日
 */
@ThreadSafe
public final class OrdSysIdAllocatorKeeper {

    private final static Logger log = Log4j2LoggerFactory.getLogger(OrdSysIdAllocatorKeeper.class);

    private static final OrdSysIdAllocator[] allocators = new OrdSysIdAllocator[1024];

    /**
     * @param strategyId int
     * @return OrdSysIdAllocator
     */
    public static OrdSysIdAllocator acquireAllocator(int strategyId) {
        if (strategyId < 0 || strategyId > 1023) {
            log.error("OrdSysIdAllocatorKeeper::newAllocator, strategyId==[{}] is not in range", strategyId);
            throw new IllegalArgumentException("strategyId is illegal, [strategyId]=" + strategyId);
        }
        if (allocators[strategyId] != null)
            return allocators[strategyId];
        allocators[strategyId] = new SnowflakeAlgo(strategyId)::next;
        log.info("OrdSysIdAllocatorKeeper::newAllocator, successfully created, strategyId==[{}]", strategyId);
        return allocators[strategyId];
    }

    /**
     * @param strategyId min value 0 max value 1023
     * @return long
     */
    public static long nextOrdSysId(int strategyId) {
        if (strategyId < 0 || strategyId > 1023)
            throw new IllegalArgumentException("strategyId is illegal, [strategyId]=" + strategyId);
        return allocators[strategyId].nextOrdSysId();
    }

    public static void main(String[] args) throws InterruptedException {

    }

}
