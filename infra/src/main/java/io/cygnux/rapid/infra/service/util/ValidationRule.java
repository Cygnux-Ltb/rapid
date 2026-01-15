package io.cygnux.rapid.infra.service.util;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class ValidationRule {

    private String paramName;

    private String dataType;

    private String regex;

    private String valueType;

    private String minValue;
    
    private String maxValue;

}
