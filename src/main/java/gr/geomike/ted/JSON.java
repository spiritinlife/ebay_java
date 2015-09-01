package gr.geomike.ted;


//import org.codehaus.jackson.map.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by spiritinlife on 8/30/15.
 */
public class JSON {


    public static String toJson(Object obj){
        ObjectMapper mapper = new ObjectMapper();


        // convert java object to JSON format,
        // and returned as JSON formatted string
        try {
            return mapper.writeValueAsString(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "{}";
    }
}
