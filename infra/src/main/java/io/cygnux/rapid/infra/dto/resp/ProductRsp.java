package io.cygnux.rapid.infra.dto.resp;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 交易产品表
 * Product Entity
 *
 * @author yellow013
 */
@Getter
@Setter
@Accessors(chain = true)
public final class ProductRsp {

    /**
     * 产品ID
     */
    private int productId;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 子账户ID
     */
    private String subAccountId;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 接口名称
     */
    private String interfaceType;

    /**
     * 可交易标识
     */
    private boolean tradable;


}
