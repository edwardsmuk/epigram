package net.ripe.app.epigram.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Random Epigram API")
                        .description("Spring Boot web app that prints a random epigram. Users can trigger loading another epigram and\n" +
                                "enable/disable automatic reloading. Users can contribute their own epigrams.")
                        .version("v1.0.0")
                        .contact(new Contact().name("Edwards Mukasa").email("muks2048@gmail.com")));
    }
}
