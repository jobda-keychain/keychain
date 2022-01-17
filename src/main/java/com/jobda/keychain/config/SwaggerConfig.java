package com.jobda.keychain.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Profile("!production")
@Configuration
public class SwaggerConfig {

    private static final String API_NAME = "KEYCHAIN API";
    private static final String API_VERSION = "0.0.1";
    private static final String API_DESCRIPTION = "JOBDA Keychain Rest API Documentation";
    private static final String API_BASE_URL = "localhost:8080";

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(API_NAME)
                .version(API_VERSION)
                .description(API_DESCRIPTION)
                .build();
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                .groupName("Keychain API")
                .apiInfo(apiInfo())
                .host(API_BASE_URL)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.jobda.keychain.controller"))
                .build();
    }

}
