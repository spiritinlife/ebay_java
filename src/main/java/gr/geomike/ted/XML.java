package gr.geomike.ted;

import gr.geomike.ted.api.db.entity.Items;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class XML {
    public static Items unmarshalItems(String filename) {
        JAXBContext context = null;
        Items items = null;

        try {
            context = JAXBContext.newInstance(Items.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            items = (Items)unmarshaller.unmarshal(new FileReader(filename));
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return items;
    }
}
