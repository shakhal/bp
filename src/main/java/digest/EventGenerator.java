package digest;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class EventGenerator {

    private List<EventHandler> eventHandlers;

    public EventGenerator(){
        eventHandlers = new ArrayList<>();
    }

    public void addHandler(EventHandler eventHandler){
        eventHandlers.add(eventHandler);
    }

    public void run(){
        Process p = null;

        try {

            p = Runtime.getRuntime().exec("bin/generator-macosx-amd64");
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
            ObjectMapper mapper = new ObjectMapper();

            String inputStr;
            while ((inputStr = streamReader.readLine()) != null){

                try{
                    Event event = mapper.readValue(inputStr, Event.class);

                    for(EventHandler eventHandler: eventHandlers){
                        eventHandler.handle(event);
                    }

                }
                catch (JsonParseException jsonParseException){
                    System.err.println("ERROR PARSING:"+inputStr);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
