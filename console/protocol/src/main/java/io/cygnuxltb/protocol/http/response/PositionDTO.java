package io.cygnuxltb.protocol.http.response;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class PositionDTO {

    /**
     * 用户ID
     */
    private int userId;

    /**
     * 头寸列表
     */
    private List<PositionObj> positions;

    /**
     * 头寸对象
     */
    @Getter
    @Setter
    @Accessors(chain = true)
    public static class PositionObj {

        /**
         * 标的代码
         */
        private String instrumentCode;

        /**
         * 多头头寸
         */
        private int longPos;

        /**
         * 空头头寸
         */
        private int shortPos;

        /**
         * 净头寸
         */
        private int netPos;

    }

}
