package com.kfr.admin.config.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    // config 설정
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(apiInfo());
    }
    //api config  설정
    private Info apiInfo() {
        return new Info()
                .title("kfr admin")
                .description(" 유저 및 인증 , REST API")
                .version("1.0.0");
    }
}