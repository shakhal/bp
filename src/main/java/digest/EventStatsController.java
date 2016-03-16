package digest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/stats")
public class EventStatsController {

    @Autowired
    EventStats eventStats;

    @RequestMapping(method=RequestMethod.GET)
    public @ResponseBody
    EventStats showStats() {
        return eventStats;
    }

}