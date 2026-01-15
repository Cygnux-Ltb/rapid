package io.cygnux.rapid.infra.dto.req;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public final class LogReq {

    /**
     *
     */
    private long epoch;

    /**
     *
     */
    private String datetime;

    /**
     *
     */
    private String level;

    /**
     *
     */
    private String type;

    /**
     *
     */
    private String keyword;

    /**
     *
     */
    private String title;

    /**
     *
     */
    private String text;

}
