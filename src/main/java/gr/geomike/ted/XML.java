package gr.geomike.ted;

import gr.geomike.ted.api.db.entity.Item;
import gr.geomike.ted.api.db.entity.Items;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.StringWriter;

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

    public static String marshalItem(Item item){
        java.io.StringWriter itemXml = new StringWriter();

        try {
            //File file = new File("C:/Code/ebay_java/web/xml/item" + item.getId()+ ".xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Item.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(item, itemXml);
            //jaxbMarshaller.marshal(item, file);
            jaxbMarshaller.marshal(item, System.out);
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }

        return itemXml.toString();
    }
}
