package com.rednorte.centros.config;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(new Info()
            .title("RedNorte - service-centros")
            .description("API REST del microservicio de la Red de Salud del Norte")
            .version("1.0.0")
            .contact(new Contact().name("RedNorte Dev Team").email("dev@rednorte.cl")));
    }
}
