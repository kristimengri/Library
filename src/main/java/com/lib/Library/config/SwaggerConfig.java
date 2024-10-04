package com.lib.Library.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.Contact;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Library Application",
                version = "1.0.0",
                description = "Backend APIs of the Library Application",
                contact = @Contact(
                        name = "****",
                        email = "***@gmail.com",
                        url = "http://www.****.com"
                )
        )
)
public class SwaggerConfig {
}