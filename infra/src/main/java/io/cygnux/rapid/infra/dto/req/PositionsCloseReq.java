package io.cygnux.rapid.infra.dto.req;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public final class PositionsCloseReq {

    /**
     * [不可为空] 用户ID
     */
    private int userid;

    /**
     * [可为空] 交易标的 (股票代码/期货代码)
     */
    private String instrumentCode;

    /**
     * [可为空, 默认: 0] 方向 (0:全部; 1:平多仓; 2:平空仓)
     */
    private int side;

    /**
     * [可为空, 默认: 0] 数量
     */
    private int qty;

    /**
     * [可为空] 开始时间, 精确到秒, 时间格式[YYYYMMDD-HHMMSS]
     */
    private String startTime;

}
