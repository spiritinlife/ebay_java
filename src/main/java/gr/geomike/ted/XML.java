package gr.geomike.ted;

import gr.geomike.ted.api.db.entity.Bid;
import gr.geomike.ted.api.db.entity.Item;
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

        //System.out.println(JSON.toJson(((List<Bid>) ((List<Item>) items.getItems()).get(0).getBids()).get(0).getBidder(), Views.Item.class));
        return items;
    }
}
