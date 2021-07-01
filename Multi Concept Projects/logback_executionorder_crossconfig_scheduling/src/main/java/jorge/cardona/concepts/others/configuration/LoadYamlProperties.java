package jorge.cardona.concepts.others.configuration;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "cloud")
public class LoadYamlProperties {

    private int release;
    private String name;
    private boolean enable;
}
