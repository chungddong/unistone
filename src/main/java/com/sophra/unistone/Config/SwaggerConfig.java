package com.sophra.unistone.Config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList("sessionAuth")) // Security Requirement 추가
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes("sessionAuth",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.APIKEY) // API Key 기반
                                        .in(SecurityScheme.In.COOKIE)     // 쿠키에서 인증
                                        .name("JSESSIONID") // 세션 ID를 담는 쿠키 이름
                        ));
    }
}
