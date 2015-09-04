package gr.geomike.ted.api.db.entity;

import gr.geomike.ted.Views;
import com.fasterxml.jackson.annotation.JsonView;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Entity
@XmlRootElement
public class Location implements Serializable{
    private static final long serialVersionUID = 1L;

    private int itemId;

    private String name;
    private Float longitude;
    private Float latitude;
    private Item item;

    @Id
    @Column(name = "ITEM_ID")
    @JsonView(Views.Internal.class)
    public int getItemId() {
        return itemId;
    }
    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    @Basic
    @Column(name = "NAME")
    @JsonView(Views.Basic.class)
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "LONGITUDE")
    @JsonView(Views.Basic.class)
    public Float getLongitude() {
        return longitude;
    }
    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    @Basic
    @Column(name = "LATITUDE")
    @JsonView(Views.Basic.class)
    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location = (Location) o;

        if (itemId != location.itemId) return false;
        if (name != null ? !name.equals(location.name) : location.name != null) return false;
        if (longitude != null ? !longitude.equals(location.longitude) : location.longitude != null) return false;
        if (latitude != null ? !latitude.equals(location.latitude) : location.latitude != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = itemId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (longitude != null ? longitude.hashCode() : 0);
        result = 31 * result + (latitude != null ? latitude.hashCode() : 0);
        return result;
    }

    @OneToOne
    @PrimaryKeyJoinColumn(name = "ITEM_ID", referencedColumnName = "ID")
    @JsonView(Views.Location.class)
    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
