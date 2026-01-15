package io.cygnux.rapid.infra.component;


import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Value("${test.mock:false}")
    private boolean mock;

    @Value("${initialize.data:false}")
    private boolean initializeData;

    @PostConstruct
    private void init() {
        System.out.println("### SysConfiguration -> mock == " + mock);
        System.out.println("### SysConfiguration -> initializeData == " + initializeData);
    }

    public boolean isMock() {
        return mock;
    }

    public boolean isInitializeData() {
        return initializeData;
    }
}
