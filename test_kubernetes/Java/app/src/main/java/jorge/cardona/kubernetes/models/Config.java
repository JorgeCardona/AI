package jorge.cardona.kubernetes.models;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "variablesconfigmap")
public class Config {

    private String directorio;

    private String archivo;

    private String fullpath;
}