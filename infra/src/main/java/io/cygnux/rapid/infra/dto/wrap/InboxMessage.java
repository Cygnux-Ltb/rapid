package io.cygnux.rapid.infra.dto.wrap;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class InboxMessage {

    private int code;

    private String content;

}
