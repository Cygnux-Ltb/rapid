package io.rapid.core.order;

import io.mercury.common.sequence.SnowflakeAlgo;

import java.util.function.LongSupplier;

import static io.rapid.core.strategy.Strategy.EXTERNAL_ORDER_STRATEGY_ID;

/**
 * OrdSysIdAllocator OrdSysId分配器接口
 *
 * @author yellow013
 */
@FunctionalInterface
public interface OrdSysIdAllocator extends LongSupplier {

    long nextOrdSysId();

    @Override
    default long getAsLong() {
        return nextOrdSysId();
    }

    OrdSysIdAllocator FOR_EXTERNAL_ORDER = new OrdSysIdAllocator() {

        /**
         * 接收到非系统报单的订单回报, 统一使用0作为策略ID, 用于根据订单回报创建订单, 并管理订单状态.
         */
        private final SnowflakeAlgo snowflake = new SnowflakeAlgo(EXTERNAL_ORDER_STRATEGY_ID);

        @Override
        public long nextOrdSysId() {
            return snowflake.next();
        }

    };

}
