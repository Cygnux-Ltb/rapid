package io.cygnux.rapid.core.risk;

public interface CircuitBreaker {

    /**
     * Enable Account
     *
     * @param subAccountId int
     */
    void enableSubAccount(int subAccountId);

    /**
     * Disable Account
     *
     * @param subAccountId int
     */
    void disableSubAccount(int subAccountId);

    /**
     * Enable Account
     *
     * @param accountId int
     */
    void enableAccount(int accountId);

    /**
     * Disable Account
     *
     * @param accountId int
     */
    void disableAccount(int accountId);

    /**
     * Enable Instrument
     *
     * @param instrumentId int
     */
    void enableInstrument(int instrumentId);

    /**
     * Disable Instrument
     *
     * @param instrumentId int
     */
    void disableInstrument(int instrumentId);

}
