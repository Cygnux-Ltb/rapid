package io.cygnux.rapid.backtest;

import io.cygnux.rapid.core.account.Account;
import io.mercury.common.datetime.DateTimeUtil;

public class BacktestAccount extends Account {

    public static final String BACKTEST_BROKER_CODE = "BT";

    public static final String BACKTEST_INVESTOR_CODE = "BT0001";

    public BacktestAccount() {
        super(1000, BACKTEST_BROKER_CODE, BACKTEST_INVESTOR_CODE, 1000000L, 0L);
        this.remark = "Backtest-by-" + DateTimeUtil.datetimeOfSecond();
    }

}
