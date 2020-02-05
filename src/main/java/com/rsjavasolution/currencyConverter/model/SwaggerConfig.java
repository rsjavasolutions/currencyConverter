package com.rsjavasolution.currencyConverter.model;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Currencies API")
                .description("My rest Api, if you can't use it contact RS Java Solution")
                .version("version1")
                .contact(new Contact("Robert Stan", "https://rsjavasolutions.herokuapp.com/",
                        "stan.robert.1986@gmail.com"))
                .build();

    }
}