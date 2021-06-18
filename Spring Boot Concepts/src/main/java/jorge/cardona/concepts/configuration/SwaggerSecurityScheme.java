package jorge.cardona.concepts.configuration;


import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;


@SecurityScheme(
        name = "BearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)
@SecurityScheme(
        name = "BasicAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "basic"

)
@SecurityScheme(
        name = "ApiKeyAuth", // can be set to anything
        type = SecuritySchemeType.APIKEY,
        in = SecuritySchemeIn.HEADER
)
@Configuration
public class SwaggerSecurityScheme {
}
