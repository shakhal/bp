package digest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.Environment;
import reactor.bus.EventBus;

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

    @Bean
    Environment env() {
        return Environment.initializeIfEmpty()
                .assignErrorJournal();
    }

    @Bean
    EventBus createEventBus(Environment env) {
        return EventBus.create(env, Environment.THREAD_POOL);
    }
}
