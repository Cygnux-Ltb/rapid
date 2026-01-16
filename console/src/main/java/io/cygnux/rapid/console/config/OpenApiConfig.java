package io.cygnux.rapid.console.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI getOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("Console Open API")
                .version("1.0.0")
                .description("Trading System Console API"));
    }

}
