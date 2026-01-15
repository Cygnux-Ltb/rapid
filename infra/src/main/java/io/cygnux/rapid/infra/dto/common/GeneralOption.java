package io.cygnux.rapid.infra.dto.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public final class GeneralOption {

    /**
     * ID
     */
    private int id;

    /**
     * NAME
     */
    private String name;

    public GeneralOption(String name) {
        this.name = name;
    }

}
