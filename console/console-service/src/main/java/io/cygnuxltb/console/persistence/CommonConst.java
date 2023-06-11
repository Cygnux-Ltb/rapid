package io.cygnuxltb.console.persistence;

public enum CommonConst {

    ;

    public enum Column {

        ;

        /**
         * 策略ID
         */
        public static final String STRATEGY_ID = "strategy_id";

        /**
         * 策略名称
         */
        public static final String STRATEGY_NAME = "strategy_name";

        /**
         * 经纪商
         */
        public static final String BROKER_ID = "broker_id";

        /**
         * 投资者ID
         */
        public static final String INVESTOR_ID = "investor_id";

        /**
         * 账户ID
         */
        public static final String ACCOUNT_ID = "account_id";

        /**
         * 子账户ID (逻辑账户)
         */
        public static final String SUB_ACCOUNT_ID = "sub_account_id";

        /**
         * 用户ID
         */
        public static final String USER_ID = "user_id";

        /**
         * 交易标的代码
         */
        public static final String INSTRUMENT_CODE = "instrument_code";

        /**
         * 交易日
         */
        public static final String TRADING_DAY = "trading_day";
    }

    /**
     * 参数组
     */
    public enum ParamGroup {

        ;

        /**
         * 策略
         */
        public static final String STRATEGY = "strategy";

        /**
         * 交易
         */
        public static final String TRADER = "trader";

        /**
         * 市场
         */
        public static final String MARKET = "market";

        /**
         * 系统
         */
        public static final String SYSTEM = "system";
    }


}
