package io.cygnux.rapid.core.order;

public enum OrdConstant {

    ;

    /**
     * 无效
     */
    public static final char INVALID = 'I';

    /* ********************* ORDER LEVEL ********************* */
    /**
     * 子订单
     */
    public static final char LEVEL_CHILD = 'C';
    /**
     * 父订单
     */
    public static final char LEVEL_PARENT = 'P';
    /**
     * 策略订单
     */
    public static final char LEVEL_STRATEGY = 'S';
    /**
     * 组订单
     */
    public static final char LEVEL_GROUP = 'G';

    /* ********************* ORDER SIDE ********************* */
    /**
     * 买
     */
    public static final char SIDE_BUY = 'B';
    /**
     * 卖
     */
    public static final char SIDE_SELL = 'S';
    /**
     * 融资买入
     */
    public static final char SIDE_MARGIN_BUY = 'M';
    /**
     * 融券卖出
     */
    public static final char SIDE_SHORT_SELL = 'T';

    /* ********************* ORDER STATUS ********************* */
    /**
     * 未知
     */
    public static final char STATUS_UNKNOWN = 'U';
    /**
     * 新订单未确认
     */
    public static final char STATUS_PENDING_NEW = 'P';
    /**
     * 新订单
     */
    public static final char STATUS_NEW = 'N';
    /**
     * 新订单已拒绝
     */
    public static final char STATUS_NEW_REJECTED = 'R';
    /**
     * 部分成交
     */
    public static final char STATUS_PARTIALLY_FILLED = 'E';
    /**
     * 全部成交
     */
    public static final char STATUS_FILLED = 'F';
    /**
     * 未确认撤单
     */
    public static final char STATUS_PENDING_CANCEL = 'C';
    /**
     * 已撤单
     */
    public static final char STATUS_CANCELED = 'X';
    /**
     * 撤单已拒绝
     */
    public static final char STATUS_CANCEL_REJECTED = 'Y';
    /**
     * 未确认修改订单
     */
    public static final char STATUS_PENDING_REPLACE = 'Q';
    /**
     * 已修改
     */
    public static final char STATUS_REPLACED = 'A';
    /**
     * 已暂停
     */
    public static final char STATUS_SUSPENDED = 'D';
    /**
     * 已停止
     */
    public static final char STATUS_STOPPED = 'S';
    /**
     * 已过期
     */
    public static final char STATUS_EXPIRED = 'V';
    /**
     * 未提供
     */
    public static final char STATUS_UNPROVIDED = 'K';

    /* ********************* ORDER TYPE ********************* */
    /**
     * Limited
     */
    public static final char TYPE_LIMITED = 'L';
    /**
     * Market
     */
    public static final char TYPE_MARKET = 'M';
    /**
     * Limited Stop, 在目前的市场价格达到指定的止损价格时, 被激活成为限价单的报单.
     */
    public static final char TYPE_LIMITED_STOP = 'S';
    /**
     * Market Stop, 在目前的市场价格达到指定的止损价格时, 被激活成为市价单的报单.
     */
    public static final char TYPE_MARKET_STOP = 'P';
    /**
     * Market To Limited, 按照市价报单的方式成交, 不能成交的部分保留在报单队列中, 变成限价单的报单.
     */
    public static final char TYPE_MTL = 'K';
    /**
     * Best Price, 不带有价格限定, 按照市场中存在的最好价格买入或者卖出的报单.
     */
    public static final char TYPE_BP = 'B';
    /**
     * Average Price, 限定最终成交平均价格的报单.
     */
    public static final char TYPE_AP = 'V';
    /**
     * Fill Or Kill, 表示要求立即全部成交, 否则就全部取消的报单.
     */
    public static final char TYPE_FOK = 'O';
    /**
     * Fill And Kill, 表示要求立即成交, 对于无法满足的部分予以取消的报单.
     */
    public static final char TYPE_FAK = 'A';
    /**
     * Minimum Volume, 要求满足成交量达到这个最小成交量, 否则就取消的报单.
     */
    public static final char TYPE_MV = 'M';

    /* ********************* ORDER VALID ********************* */
    /**
     * Good Till Cancel
     */
    public static final char VALID_GTC = 'C';
    /**
     * Good Till Date
     */
    public static final char VALID_GTD = 'D';
    /**
     * Good For Day
     */
    public static final char VALID_GFD = 'G';

}
