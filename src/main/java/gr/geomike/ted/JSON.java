package gr.geomike.ted;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

public class JSON {

    public static String toJson(Object obj, Class<?> view){
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.writerWithView(view).writeValueAsString(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "{}";
    }
}
