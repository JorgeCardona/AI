package jorge.cardona.concepts.multiserviceadapter.configuration;

import jorge.cardona.concepts.multiserviceadapter.interfaces.ServiceRegistry;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.ServiceLocatorFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public FactoryBean<?> factoryBean() {

        final ServiceLocatorFactoryBean bean = new ServiceLocatorFactoryBean();
        bean.setServiceLocatorInterface(ServiceRegistry.class);
        return bean;
    }
}
