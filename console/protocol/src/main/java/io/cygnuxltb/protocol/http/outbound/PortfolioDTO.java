package io.cygnuxltb.protocol.http.outbound;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 用户自选标的
 */
@Getter
@Setter
@Accessors(chain = true)
public class PortfolioDTO {

    private int userId;

    private String groupName;

    private List<String> instrumentCodes;

}
