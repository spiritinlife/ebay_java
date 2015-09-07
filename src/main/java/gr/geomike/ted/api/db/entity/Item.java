package gr.geomike.ted.api.db.entity;

import com.fasterxml.jackson.annotation.JsonView;
import gr.geomike.ted.CurrencyAdapter;
import gr.geomike.ted.DateAdapter;
import gr.geomike.ted.Views;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Table(name = "ITEM")
@XmlRootElement(name="item")
@NamedQueries({
        @NamedQuery(name = "Item.findAll", query = "SELECT i FROM Item  i"),
        @NamedQuery(name = "Item.findById", query = "SELECT i FROM Item i WHERE i.id = :id")
})
public class Item implements Serializable{
    private static final long serialVersionUID = 1L;

    private int id;

    private String name;
    private Float currently;
    private Float buyPrice;
    private Float firstBid;
    private Integer numberOfBids;
    private String country;
    private Timestamp started;
    private Timestamp ends;
    private String description;

    private Collection<Bid> bids;
    private Collection<Category> categories;

    private Location location;

    private Seller seller;

    public Item() {
    }

    public Item(int id) {
        this.id = id;
    }
    public Item(int id, String itemName, Float currently, Float buyPrice, Float firstBid, Integer numberOfBids, String country
            , Timestamp started, Timestamp ends, String description, Collection<Bid> bids, Collection<Category> categories, Location location) {
        this.id = id;
        this.name = itemName;
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
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID")
    @JsonView(Views.Basic.class)
    @XmlAttribute(name = "ItemID")
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NAME")
    @JsonView(Views.Basic.class)
    @XmlElement(name = "Name")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "CURRENTLY")
    @JsonView(Views.Basic.class)
    @XmlJavaTypeAdapter(CurrencyAdapter.class)
    @XmlElement(name = "Currently")
    public Float getCurrently() {
        return currently;
    }
    public void setCurrently(Float currently) {
        this.currently = currently;
    }

    @Basic
    @Column(name = "BUY_PRICE")
    @JsonView(Views.Basic.class)
    @XmlJavaTypeAdapter(CurrencyAdapter.class)
    @XmlElement(name = "Buy_Price")
    public Float getBuyPrice() {
        return buyPrice;
    }
    public void setBuyPrice(Float buyPrice) {
        this.buyPrice = buyPrice;
    }

    @Basic
    @Column(name = "FIRST_BID")
    @JsonView(Views.Basic.class)
    @XmlJavaTypeAdapter(CurrencyAdapter.class)
    @XmlElement(name = "First_Bid")
    public Float getFirstBid() {
        return firstBid;
    }
    public void setFirstBid(Float firstBid) {
        this.firstBid = firstBid;
    }

    @Basic
    @Column(name = "NUMBER_OF_BIDS")
    @JsonView(Views.Basic.class)
    @XmlElement(name = "Number_of_Bids")
    public Integer getNumberOfBids() {
        return numberOfBids;
    }
    public void setNumberOfBids(Integer numberOfBids) {
        this.numberOfBids = numberOfBids;
    }

    @Basic
    @Column(name = "COUNTRY")
    @JsonView(Views.Basic.class)
    @XmlElement(name = "Country")
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }

    @Basic
    @Column(name = "STARTED")
    @JsonView(Views.Basic.class)
    @XmlJavaTypeAdapter(DateAdapter.class)
    @XmlElement(name = "Started")
    public Timestamp getStarted() {
        return started;
    }
    public void setStarted(Timestamp started) {
        this.started = started;
    }

    @Basic
    @Column(name = "ENDS")
    @JsonView(Views.Basic.class)
    @XmlJavaTypeAdapter(DateAdapter.class)
    @XmlElement(name = "Ends")
    public Timestamp getEnds() {
        return ends;
    }
    public void setEnds(Timestamp ends) {
        this.ends = ends;
    }

    @Basic
    @Column(name = "DESCRIPTION")
    @JsonView(Views.Basic.class)
    @XmlElement(name = "Description")
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
    @JsonView(Views.Item.class)
    @XmlElementWrapper(name = "Bids")
    @XmlElement(name = "Bid")
    public Collection<Bid> getBids() {
        return bids;
    }
    public void setBids(Collection<Bid> bids) {
        this.bids = bids;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name="ITEM_HAS_CATEGORY",
            joinColumns={@JoinColumn(name="ITEM_ID", referencedColumnName="ID")},
            inverseJoinColumns={@JoinColumn(name="CATEGORY_NAME", referencedColumnName="NAME")}
    )
    @JsonView(Views.Item.class)
    @XmlElement(name = "Category")
    public Collection<Category> getCategories() {
        return categories;
    }
    public void setCategories(Collection<Category> categories) {
        this.categories = categories;
    }

    @ManyToOne
    @JsonView(Views.Item.class)
    @XmlElement(name = "Location")
    @JoinColumn(name="LOCATION_NAME")
    public Location getLocation() {
        return location;
    }
    public void setLocation(Location locationById) {
        this.location = locationById;
    }


    @ManyToOne(fetch=FetchType.LAZY)
    @JsonView(Views.Item.class)
    @XmlElement(name = "Seller")
    @JoinColumn(name="SELLER_USERNAME")
    public Seller getSeller() {
        return seller;
    }
    public void setSeller(Seller seller) {
        this.seller = seller;
    }


}
