package ticketingsystem.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConfigurationInitializer {

    private final Configuration configuration;

    @Autowired
    public ConfigurationInitializer(Configuration configuration) {
        this.configuration = configuration;
    }

    @PostConstruct
    public void init() {
        // This method will be called after the bean is constructed
        System.out.println("Configuration initialized successfully: " + configuration);
    }
}
