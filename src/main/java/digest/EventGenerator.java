package digest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;

import reactor.bus.Event;
import reactor.bus.EventBus;


@Service
public class EventGenerator {

    @Autowired
    EventBus eventBus;

    public void run(){
        Process p = null;

        try {

            p = Runtime.getRuntime().exec("bin/generator-macosx-amd64");
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));

            String inputStr;
            while ((inputStr = streamReader.readLine()) != null){

                createEvent(inputStr);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BpEvent createEvent(String json){
        ObjectMapper mapper = new ObjectMapper();

        try{
            BpEvent event = mapper.readValue(json, BpEvent.class);

            eventBus.notify("bpevents", Event.wrap(event));

            return event;

        }
        catch (IOException jsonParseException){
            System.err.println("ERROR PARSING:"+json);

            return null;
        }
    }
}
