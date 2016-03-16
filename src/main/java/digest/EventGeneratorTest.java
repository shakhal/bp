package digest;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class EventGeneratorTest {

    @Autowired
    EventGenerator eventGenerator;

    @Test
    public void generateBadEvent(){

        eventGenerator.createEvent("{hello}");
        eventGenerator.createEvent("}");
        eventGenerator.createEvent("{");
        eventGenerator.createEvent("");

    }

    @Test
    public void generateGoodEvent(){

        BpEvent bpEvent = eventGenerator.createEvent("{ \"event_type\": \"baz\", \"data\": \"amet\", \"timestamp\": 1458149068 }");
        Assert.assertEquals("baz", bpEvent.getEventType());
        Assert.assertEquals("amet", bpEvent.getData());
        Assert.assertEquals(1458149068, bpEvent.getTimestamp());
    }
}
