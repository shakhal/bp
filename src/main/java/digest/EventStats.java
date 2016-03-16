package digest;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.util.concurrent.AtomicLongMap;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class EventStats {

    private AtomicLong wordCount;
    private AtomicLongMap<String> eventsCounter;

    public EventStats(){
        eventsCounter = AtomicLongMap.create();
        wordCount = new AtomicLong();
    }

    @JsonIgnore
    public AtomicLongMap<String> getEventsCounter() {
        return eventsCounter;
    }

    public Map<String,Long> getEventsStats(){
        return eventsCounter.asMap();
    }

    public AtomicLong getWordCount() {
        return wordCount;
    }
}
