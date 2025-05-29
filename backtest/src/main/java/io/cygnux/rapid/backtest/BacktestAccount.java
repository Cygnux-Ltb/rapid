package io.cygnux.rapid.backtest;

import io.mercury.common.datetime.DateTimeUtil;
import io.rapid.core.account.Account;

public class BacktestAccount extends Account {

    public BacktestAccount() {
        super(1000, "BACKTEST", "BT0001", 1000000L, 0L);
        this.remark = "Backtest-by-" + DateTimeUtil.datetimeOfSecond();
    }

}
