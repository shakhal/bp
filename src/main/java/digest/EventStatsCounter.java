package digest;


import org.springframework.beans.factory.annotation.Autowired;

public class EventStatsCounter implements EventHandler{

    @Autowired
    private EventStats eventStats;

    @Override
    public void handle(Event event){
        eventStats.getEventsCounter().addAndGet(event.getEventType(), 1);

        int wordCount = event.getData().split(" ").length;
        eventStats.getWordCount().addAndGet(wordCount);
    }
}
