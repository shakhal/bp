package digest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.bus.Event;
import reactor.fn.Consumer;

@Service
public class EventStatsCounter implements Consumer<Event<BpEvent>> {

    @Autowired
    private EventStats eventStats;

    @Override
    public void accept(Event<BpEvent> bpEventEvent) {
        eventStats.getEventsCounter().addAndGet(bpEventEvent.getData().getEventType(), 1);

        int wordCount = bpEventEvent.getData().getData().split(" ").length;
        eventStats.getWordCount().addAndGet(wordCount);

    }
}
