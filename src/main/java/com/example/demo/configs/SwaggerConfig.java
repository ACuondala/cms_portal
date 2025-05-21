package com.example.demo.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenApi(){
        return new OpenAPI()
                .info(
                        new Info().title("CMS_Portal")
                                .version("1.0")
                                .description("CMS API documentation")
                                .contact( new Contact()
                                        .email("acuondala@gmail.com")
                                        .name("Anderson Cuondala Mendes da Costa")

                                )
                )
                ;
    }

}
