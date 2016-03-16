package digest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);

        Application app = ctx.getBean(Application.class);
        app.start();

    }

    @Autowired
    private EventGenerator eventGenerator;

    @Autowired
    private EventStatsCounter eventStatsCounter;

    private void start() {
        eventGenerator.addHandler(eventStatsCounter);
        eventGenerator.run();
    }

}