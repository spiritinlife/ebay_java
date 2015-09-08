package gr.geomike.ted.api.db.entity;

import gr.geomike.ted.Views;
import com.fasterxml.jackson.annotation.JsonView;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "Items")
public class Items {
    private List<Item> items;

    @JsonView(Views.Item.class)
    @XmlElement(name = "Item")
    public List<Item> getItems(){
        return  items;
    }
    public void setItems(List<Item> items){
        this.items = items;
    }
}
