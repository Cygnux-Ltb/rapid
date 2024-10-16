package io.rapid.core.order;

public interface OrdConstant {

    /**
     * 无效
     */
    char INVALID = 'I';

    /* ********************* ORDER LEVEL ********************* */
    /**
     * 子订单
     */
    char LEVEL_CHILD = 'C';
    /**
     * 父订单
     */
    char LEVEL_PARENT = 'P';
    /**
     * 策略订单
     */
    char LEVEL_STRATEGY = 'S';
    /**
     * 组订单
     */
    char LEVEL_GROUP = 'G';

    /* ********************* ORDER SIDE ********************* */
    /**
     * 买
     */
    char SIDE_BUY = 'B';
    /**
     * 卖
     */
    char SIDE_SELL = 'S';
    /**
     * 融资买入
     */
    char SIDE_MARGIN_BUY = 'M';
    /**
     * 融券卖出
     */
    char SIDE_SHORT_SELL = 'T';

    /* ********************* ORDER STATUS ********************* */
    /**
     * 未知
     */
    char STATUS_UNKNOWN = 'U';
    /**
     * 新订单未确认
     */
    char STATUS_PENDING_NEW = 'P';
    /**
     * 新订单
     */
    char STATUS_NEW = 'N';
    /**
     * 新订单已拒绝
     */
    char STATUS_NEW_REJECTED = 'R';
    /**
     * 部分成交
     */
    char STATUS_PARTIALLY_FILLED = 'E';
    /**
     * 全部成交
     */
    char STATUS_FILLED = 'F';
    /**
     * 未确认撤单
     */
    char STATUS_PENDING_CANCEL = 'C';
    /**
     * 已撤单
     */
    char STATUS_CANCELED = 'X';
    /**
     * 撤单已拒绝
     */
    char STATUS_CANCEL_REJECTED = 'Y';
    /**
     * 未确认修改订单
     */
    char STATUS_PENDING_REPLACE = 'Q';
    /**
     * 已修改
     */
    char STATUS_REPLACED = 'A';
    /**
     * 已暂停
     */
    char STATUS_SUSPENDED = 'D';
    /**
     * 已停止
     */
    char STATUS_STOPPED = 'S';
    /**
     * 已过期
     */
    char STATUS_EXPIRED = 'V';
    /**
     * 未提供
     */
    char STATUS_UNPROVIDED = 'K';

    /* ********************* ORDER TYPE ********************* */
    /**
     * Limited
     */
    char TYPE_LIMITED = 'L';
    /**
     * Market
     */
    char TYPE_MARKET = 'M';
    /**
     * Limited Stop, 在目前的市场价格达到指定的止损价格时, 被激活成为限价单的报单.
     */
    char TYPE_LIMITED_STOP = 'S';
    /**
     * Market Stop, 在目前的市场价格达到指定的止损价格时, 被激活成为市价单的报单.
     */
    char TYPE_MARKET_STOP = 'P';
    /**
     * Market To Limited, 按照市价报单的方式成交, 不能成交的部分保留在报单队列中, 变成限价单的报单.
     */
    char TYPE_MTL = 'K';
    /**
     * Best Price, 不带有价格限定, 按照市场中存在的最好价格买入或者卖出的报单.
     */
    char TYPE_BP = 'B';
    /**
     * Average Price, 限定最终成交平均价格的报单.
     */
    char TYPE_AP = 'V';
    /**
     * Fill Or Kill, 表示要求立即全部成交, 否则就全部取消的报单.
     */
    char TYPE_FOK = 'O';
    /**
     * Fill And Kill, 表示要求立即成交, 对于无法满足的部分予以取消的报单.
     */
    char TYPE_FAK = 'A';
    /**
     * Minimum Volume, 要求满足成交量达到这个最小成交量, 否则就取消的报单.
     */
    char TYPE_MV = 'M';

    /* ********************* ORDER VALID ********************* */
    /**
     * Good Till Cancel
     */
    char VALID_GTC = 'C';
    /**
     * Good Till Date
     */
    char VALID_GTD = 'D';
    /**
     * Good For Day
     */
    char VALID_GFD = 'G';

}
