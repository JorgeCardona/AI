package jorge.cardona.concepts.others.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration headersOriginsMethodsCredentials = new CorsConfiguration();

        // credentials
        headersOriginsMethodsCredentials.setAllowCredentials(true);

        // origins
        headersOriginsMethodsCredentials.addAllowedOrigin("http://localhost:3000");
        headersOriginsMethodsCredentials.addAllowedOrigin("http://localhost:7000");
        headersOriginsMethodsCredentials.addAllowedOrigin("http://localhost:9999");

        // headers
        headersOriginsMethodsCredentials.addAllowedHeader("Access-Control-Allow-Origin");
        headersOriginsMethodsCredentials.addAllowedHeader("Origin");
        headersOriginsMethodsCredentials.addAllowedHeader("Accept");
        headersOriginsMethodsCredentials.addAllowedHeader("system-id");
        headersOriginsMethodsCredentials.addAllowedHeader("ClientId");
        headersOriginsMethodsCredentials.addAllowedHeader("ClientSecret");
        headersOriginsMethodsCredentials.addAllowedHeader("Access-Control-Request-Headers");
        headersOriginsMethodsCredentials.addAllowedHeader("Access-Control-Request-Method");

        // methods
        headersOriginsMethodsCredentials.addAllowedMethod(HttpMethod.GET);
        headersOriginsMethodsCredentials.addAllowedMethod(HttpMethod.POST);
        headersOriginsMethodsCredentials.addAllowedMethod("PUT");
        headersOriginsMethodsCredentials.addAllowedMethod("DELETE");
        headersOriginsMethodsCredentials.addAllowedMethod("OPTIONS");

        // register configuration for all path "/**"
        source.registerCorsConfiguration("/**", headersOriginsMethodsCredentials);
        return new CorsFilter(source);
    }

}
