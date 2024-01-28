package io.cygnuxltb.console;


import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SysConfiguration {

    @Value("${test.mock:false}")
    private boolean mock;

    @PostConstruct
    private void init() {
        System.out.println(STR."### SysConfiguration -> mock == \{mock}");
    }

    public boolean isMock() {
        return mock;
    }

}
