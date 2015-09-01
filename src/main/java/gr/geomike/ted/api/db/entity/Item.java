package gr.geomike.ted.api.db.entity;

//import org.codehaus.jackson.annotate.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@Table(name = "ITEM")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Item.findAll", query = "SELECT i FROM Item  i")/*,
        @NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username"),
        @NamedQuery(name = "User.findById", query = "SELECT u FROM User u WHERE u.id = :id"),
        @NamedQuery(name = "User.findByFirstName", query = "SELECT u FROM User u WHERE u.firstName = :firstName"),
        @NamedQuery(name = "User.findByLastName", query = "SELECT u FROM User u WHERE u.lastName = :lastName"),
        @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password")*/})
//@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="categories")
public class Item implements Serializable{
    private static final long serialVersionUID = 1L;

    private int id;

    private String name;
    private Integer currently;
    private Integer buyPrice;
    private Integer firstBid;
    private Integer numberOfBids;
    private String country;
    private Timestamp started;
    private Timestamp ends;
    private String description;

    private Collection<Bid> bids;
    private Collection<Category> categories;
    private Location location;

    public Item() {
    }

    public Item(int id, String name, Integer currently, Integer buyPrice, Integer firstBid, Integer numberOfBids, String country
            , Timestamp started, Timestamp ends, String description, Collection<Bid> bids, Collection<Category> categories, Location location) {
        this.id = id;
        this.name = name;
        this.currently = currently;
        this.buyPrice = buyPrice;
        this.firstBid = firstBid;
        this.numberOfBids = numberOfBids;
        this.country = country;
        this.started = started;
        this.ends = ends;
        this.description = description;
        this.bids = bids;
        this.categories = categories;
        this.location = location;
    }

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "CURRENTLY")
    public Integer getCurrently() {
        return currently;
    }

    public void setCurrently(Integer currently) {
        this.currently = currently;
    }

    @Basic
    @Column(name = "BUY_PRICE")
    public Integer getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(Integer buyPrice) {
        this.buyPrice = buyPrice;
    }

    @Basic
    @Column(name = "FIRST_BID")
    public Integer getFirstBid() {
        return firstBid;
    }

    public void setFirstBid(Integer firstBid) {
        this.firstBid = firstBid;
    }

    @Basic
    @Column(name = "NUMBER_OF_BIDS")
    public Integer getNumberOfBids() {
        return numberOfBids;
    }

    public void setNumberOfBids(Integer numberOfBids) {
        this.numberOfBids = numberOfBids;
    }

    @Basic
    @Column(name = "COUNTRY")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Basic
    @Column(name = "STARTED")
    public Timestamp getStarted() {
        return started;
    }

    public void setStarted(Timestamp started) {
        this.started = started;
    }

    @Basic
    @Column(name = "ENDS")
    public Timestamp getEnds() {
        return ends;
    }

    public void setEnds(Timestamp ends) {
        this.ends = ends;
    }

    @Basic
    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (id != item.id) return false;
        if (name != null ? !name.equals(item.name) : item.name != null) return false;
        if (currently != null ? !currently.equals(item.currently) : item.currently != null) return false;
        if (buyPrice != null ? !buyPrice.equals(item.buyPrice) : item.buyPrice != null) return false;
        if (firstBid != null ? !firstBid.equals(item.firstBid) : item.firstBid != null) return false;
        if (numberOfBids != null ? !numberOfBids.equals(item.numberOfBids) : item.numberOfBids != null) return false;
        if (country != null ? !country.equals(item.country) : item.country != null) return false;
        if (started != null ? !started.equals(item.started) : item.started != null) return false;
        if (ends != null ? !ends.equals(item.ends) : item.ends != null) return false;
        if (description != null ? !description.equals(item.description) : item.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (currently != null ? currently.hashCode() : 0);
        result = 31 * result + (buyPrice != null ? buyPrice.hashCode() : 0);
        result = 31 * result + (firstBid != null ? firstBid.hashCode() : 0);
        result = 31 * result + (numberOfBids != null ? numberOfBids.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (started != null ? started.hashCode() : 0);
        result = 31 * result + (ends != null ? ends.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "item")
    //@JsonManagedReference(value = "item-bid")
    public Collection<Bid> getBids() {
        return bids;
    }

    public void setBids(Collection<Bid> bids) {
        this.bids = bids;
    }

    @ManyToMany
    //@JsonManagedReference(value = "item-category")
    @JoinTable(name="ITEM_HAS_CATEGORY",
            joinColumns={@JoinColumn(name="ITEM_ID", referencedColumnName="ID")},
            inverseJoinColumns={@JoinColumn(name="CATEGORY_NAME", referencedColumnName="NAME")}
    )
    public Collection<Category> getCategories() {
        return categories;
    }

    public void setCategories(Collection<Category> categories) {
        this.categories = categories;
    }

    @OneToOne(mappedBy = "item")
    //@JsonManagedReference(value = "item-location")
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location locationById) {
        this.location = locationById;
    }
}
