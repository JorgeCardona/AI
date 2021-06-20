package jorge.cardona.concepts.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Primary
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate(this.configureTimeOutRestTemplate());
    }

    @Bean(name="remoteRestTemplate")
    public RestTemplate remoteRestTemplate() {
        return new RestTemplate(this.configureTimeOutRestTemplate());
    }


    //Override timeouts in request factory
    private SimpleClientHttpRequestFactory configureTimeOutRestTemplate()
    {
        SimpleClientHttpRequestFactory clientHttpRequestFactory
                = new SimpleClientHttpRequestFactory();

        //Connect timeout
        clientHttpRequestFactory.setConnectTimeout(1_000);

        //Read timeout
        clientHttpRequestFactory.setReadTimeout(1_000);
        return clientHttpRequestFactory;
    }

}
