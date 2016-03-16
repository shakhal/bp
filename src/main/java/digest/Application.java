package digest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import reactor.Environment;
import reactor.bus.EventBus;
import static reactor.bus.selector.Selectors.$;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);

        Application app = ctx.getBean(Application.class);
        app.start();

    }

    @Autowired
    private EventBus eventBus;

    @Autowired
    private EventGenerator eventGenerator;

    @Autowired
    private EventStatsCounter eventStatsCounter;

    private void start() {
        eventBus.on($("bpevents"), eventStatsCounter);
        eventGenerator.run();

    }

}