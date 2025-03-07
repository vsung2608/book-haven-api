package com.bookhaven.payment_service.configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    OpenAPI paymentSerivceAPI(){
        return new OpenAPI().info(new Info()
                        .title("Customer Service API")
                        .description("This is the REST API for Payment Service")
                        .version("0.0.1")
                        .license(new License().name("Apache 2.0").url("https://www.apache.org/licenses/LICENSE-2.0.html")))
                .externalDocs(new ExternalDocumentation()
                        .description("You can read more about Payment Service REST API at: ")
                        .url("https://bookhaven-uri.com/docs"));
    }
}
