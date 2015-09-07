package gr.geomike.ted.api.db.entity;

import gr.geomike.ted.Views;
import com.fasterxml.jackson.annotation.JsonView;
import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Entity
@XmlRootElement(name = "Location")
public class Location implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private Float longitude;
    private Float latitude;
    private Collection<Item> items;

    @Id
    @Column(name = "NAME")
    @JsonView(Views.Basic.class)
    @XmlValue
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "LONGITUDE")
    @JsonView(Views.Basic.class)
    @XmlAttribute(name = "Longitude")
    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    @Basic
    @Column(name = "LATITUDE")
    @JsonView(Views.Basic.class)
    @XmlAttribute(name = "Latitude")
    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location)) return false;

        Location location = (Location) o;

        if (getName() != null ? !getName().equals(location.getName()) : location.getName() != null) return false;
        if (getLongitude() != null ? !getLongitude().equals(location.getLongitude()) : location.getLongitude() != null)
            return false;
        if (getLatitude() != null ? !getLatitude().equals(location.getLatitude()) : location.getLatitude() != null)
            return false;
        return !(getItems() != null ? !getItems().equals(location.getItems()) : location.getItems() != null);

    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getLongitude() != null ? getLongitude().hashCode() : 0);
        result = 31 * result + (getLatitude() != null ? getLatitude().hashCode() : 0);
        result = 31 * result + (getItems() != null ? getItems().hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "location")
    @JsonView(Views.Location.class)
    @XmlTransient
    public Collection<Item> getItems() {
        return items;
    }
    public void setItems(Collection<Item> items) {
        this.items = items;
    }
}