package k8s;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(GreetingsProperties.class)
class MyAwesomeAutoConfiguration {


    @Bean
    ApplicationListener<ApplicationReadyEvent> greetingApplicationReadyEventListener(
            ObjectMapper objectMapper, GreetingsProperties greetingsProperties) {
        return event -> {
            Greeting g = new Greeting(greetingsProperties.getName());
            try {
                String json = objectMapper.writeValueAsString(g);
                System.out.println(json);
            }//
            catch (JsonProcessingException e) {
                System.err.println("something went wrong when converting to JSON! " +
                        e.getMessage());
            }
        };
    }
}

@ConstructorBinding
@ConfigurationProperties(prefix = "bootiful")
class GreetingsProperties {

    private final String name;

    GreetingsProperties(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Greeting {

    private final String greeting;

    public String getGreeting() {
        return greeting;
    }

    Greeting(String name) {
        this.greeting = "Hello, " + name + "!";
    }
}
