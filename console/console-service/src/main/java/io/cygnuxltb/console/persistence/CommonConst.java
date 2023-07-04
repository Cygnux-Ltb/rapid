package io.cygnuxltb.console.persistence;

public enum CommonConst {
    ;

    public enum Column {
        ;

        /**
         * 策略ID
         */
        public static final String STRATEGY_ID = "STRATEGY_ID";

        /**
         * 策略名称
         */
        public static final String STRATEGY_NAME = "STRATEGY_NAME";

        /**
         * 经纪商
         */
        public static final String BROKER_ID = "BROKER_ID";

        /**
         * 投资者ID
         */
        public static final String INVESTOR_ID = "INVESTOR_ID";

        /**
         * 账户ID
         */
        public static final String ACCOUNT_ID = "ACCOUNT_ID";

        /**
         * 子账户ID (逻辑账户)
         */
        public static final String SUB_ACCOUNT_ID = "SUB_ACCOUNT_ID";

        /**
         * 用户ID
         */
        public static final String USER_ID = "USER_ID";

        /**
         * 交易标的代码
         */
        public static final String INSTRUMENT_CODE = "INSTRUMENT_CODE";

        /**
         * 交易日
         */
        public static final String TRADING_DAY = "TRADING_DAY";
    }

    /**
     * 参数组
     */
    public enum ParamGroup {

        ;

        /**
         * 策略
         */
        public static final String STRATEGY = "STRATEGY";

        /**
         * 交易
         */
        public static final String TRADER = "TRADER";

        /**
         * 市场
         */
        public static final String MARKET = "MARKET";

        /**
         * 系统
         */
        public static final String SYSTEM = "SYSTEM";
    }


}
