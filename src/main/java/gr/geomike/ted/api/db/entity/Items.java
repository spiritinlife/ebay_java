package gr.geomike.ted.api.db.entity;

import gr.geomike.ted.Views;
import com.fasterxml.jackson.annotation.JsonView;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collection;

@XmlRootElement(name = "Items")
public class Items {
    private Collection<Item> items;

    @JsonView(Views.Item.class)
    @XmlElement(name = "Item")
    public Collection<Item> getItems(){
        return  items;
    }
    public void setItems(Collection<Item> items){
        this.items = items;
    }
}
