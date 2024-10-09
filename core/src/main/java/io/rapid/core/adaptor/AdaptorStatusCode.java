package io.rapid.core.adaptor;

public interface AdaptorStatusCode {

    /**
     * 无效
     */
    char INVALID = 'I';
    /**
     * 全部禁用
     */
    char ALL_DISABLE = 'X';
    /**
     * 全部启用
     */
    char ALL_ENABLE = 'A';
    /**
     * 仅行情启用
     */
    char MD_ENABLE = 'M';
    /**
     * 仅交易启用
     */
    char TRADER_ENABLE = 'T';

}