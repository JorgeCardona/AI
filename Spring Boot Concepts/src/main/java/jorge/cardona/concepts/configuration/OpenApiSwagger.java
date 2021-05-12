package jorge.cardona.concepts.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiSwagger {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Spring Boot Concepts API by Jorge Cardona")
                        .version("1.0")
                        .description("Multiples Tips for Spring Boot Projects")
                        .termsOfService("https://github.com/JorgeCardona")
                        .license(new License()
                                .name("Copyright")
                                .url("https://www.linkedin.com/in/jorgecardona1")));
    }
}

