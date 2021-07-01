package jorge.cardona.concepts.jpa.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;


@Configuration
public class OpenApiSwagger {

    @Bean
    public OpenAPI customOpenAPI() {

        List<Server> server = new ArrayList<>();

        server.add(new Server().url("http://localhost:8080/api"));
        server.add(new Server().url("https://localhost:8080"));
        server.add(new Server().url("http://localhost:3000"));
        server.add(new Server().url("https://example.com/api3"));


        return new OpenAPI()
                .servers(server)
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

