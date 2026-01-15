package io.cygnux.rapid.core.types.field;

public final class ValueLimitation {

    private ValueLimitation() {
    }

    public static final int MIN_ORD_SYS_ID = 1;

    public static final int MIN_ALGO_ID = 1;

    public static final int MIN_STRATEGY_ID = 1;

    /**
     * 系统可允许的最大策略ID (雪花算法ID)
     */
    public static final int MAX_STRATEGY_ID = 1023;

    public static final int MIN_TRADING_ID = 1;

    public static final int MIN_SUB_ACCOUNT_ID = 1;

    public static final int MAX_SUB_ACCOUNT_ID = Integer.MAX_VALUE >> 1;

    public static final int MIN_ACCOUNT_ID = 1;

    public static final int MAX_ACCOUNT_ID = Integer.MAX_VALUE >> 1;

    public static final int MIN_USERID = 1;

    public static final int MIN_ADAPTOR_ID = 1;

    public static final int MIN_EPOCH_DATE = 19700101;

    public static final int MIN_SIMULATION_DATE = 20100101;

}
