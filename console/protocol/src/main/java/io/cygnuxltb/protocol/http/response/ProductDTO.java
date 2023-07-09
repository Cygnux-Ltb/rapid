package io.cygnuxltb.protocol.http.response;

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
public final class ProductDTO {

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

}
