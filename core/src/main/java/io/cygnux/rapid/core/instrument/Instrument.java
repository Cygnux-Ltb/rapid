package io.cygnux.rapid.core.instrument;

import io.mercury.common.state.Enableable;
import io.cygnux.rapid.core.instrument.enums.InstrumentType;
import io.cygnux.rapid.core.instrument.enums.PriceMultiplier;
import io.cygnux.rapid.core.instrument.enums.PriorityCloseType;

import java.time.ZoneOffset;

public sealed interface Instrument extends Enableable, Comparable<Instrument>
        permits AbstractInstrument {

    /**
     * Integer.MAX_VALUE == 2147483647
     * <p>
     * STOCK : exchange|symbol<br>
     * MAX_VALUE == 214|7483647<br>
     * <p>
     * FUTURES:exchange|symbol|term<br>
     * MAX_VALUE == 214|  74  |83647<br>
     * <p>
     * FOREX : exchange|symbol<br>
     * MAX_VALUE == 214|7483647<br>
     *
     * @return int
     */
    int getInstrumentId();

    String getInstrumentCode();

    Symbol getSymbol();

    Exchange getExchange();

    default String getExchangeCode() {
        return getExchange().getExchangeCode();
    }

    default ZoneOffset getZoneOffset() {
        return getExchange().getZoneOffset();
    }

    InstrumentType getType();

    default PriceMultiplier getMultiplier() {
        return getSymbol().getMultiplier();
    }

    default double getTickSize() {
        return getSymbol().getTickSize();
    }

    /**
     * 是否立即可用<br>
     * <p>
     * 用于计算可卖出仓位, 例如中国大陆股票的买入并不是立即可以卖出
     *
     * @return boolean
     */
    default boolean isAvailableImmediately() {
        return true;
    }

    /**
     * 优先平仓类型
     *
     * @return PriorityCloseType
     */
    default PriorityCloseType getPriorityCloseType() {
        return PriorityCloseType.NONE;
    }

    default void setTradable() {
        enable();
    }

    default void setNotTradable() {
        disable();
    }

    default boolean isTradable() {
        return isEnabled();
    }

    default boolean isNotTradable() {
        return isDisabled();
    }

    /**
     * 返回当前[Instrument]状态, 包含交易状态和行情订阅状态
     *
     * @return InstrumentStatus
     */
    InstrumentStatus getStatus();

    /**
     * 设置交易手续费
     *
     * @param tradeFee double
     */
    void setTradeFee(double tradeFee);

    /**
     * 获取交易手续费
     *
     * @return double
     */
    double getTradeFee();

    @Override
    default int compareTo(Instrument o) {
        return Integer.compare(getInstrumentId(), o.getInstrumentId());
    }

}
