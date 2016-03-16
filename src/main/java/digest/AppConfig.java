package digest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public EventStats eventStats() {
        return new EventStats();
    }

    @Bean
    public EventStatsCounter eventStatsCounter() {
        return new EventStatsCounter();
    }

    @Bean
    public EventGenerator eventGenerator(){
        return new EventGenerator();
    }
}
