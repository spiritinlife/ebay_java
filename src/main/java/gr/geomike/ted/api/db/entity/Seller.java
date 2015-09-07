package gr.geomike.ted.api.db.entity;

import gr.geomike.ted.Views;
import com.fasterxml.jackson.annotation.JsonView;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Collection;


@Entity
@XmlRootElement(name = "Seller")
@NamedQueries({
@NamedQuery(name = "Seller.findByUsername", query = "SELECT u FROM Seller u WHERE u.username = :username")})
public class Seller implements Serializable {
    private static final long serialVersionUID = 1L;
    private String username;
    private User user;

    private Integer rating;

    private Collection<Bid> bids;
    private Collection<Item> items;

    public Seller() {
    }
    public Seller(String username, Integer rating, Collection<Bid> bids, User user) {
        this.username = username;
        this.rating = rating;
        this.bids = bids;
        this.user = user;
    }

    @Id
    @Column(name = "USERNAME")
    @JsonView(Views.Basic.class)
    @XmlAttribute(name = "UserID")
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "RATING")
    @JsonView(Views.Basic.class)
    @XmlAttribute(name = "Rating")
    public Integer getRating() {
        return rating;
    }
    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Seller)) return false;

        Seller seller = (Seller) o;

        if (!getUsername().equals(seller.getUsername())) return false;
        if (getRating() != null ? !getRating().equals(seller.getRating()) : seller.getRating() != null) return false;
        if (bids != null ? !bids.equals(seller.bids) : seller.bids != null) return false;
        return getUser().equals(seller.getUser());

    }

    @Override
    public int hashCode() {
        int result = getUsername().hashCode();
        result = 31 * result + (getRating() != null ? getRating().hashCode() : 0);
        result = 31 * result + (bids != null ? bids.hashCode() : 0);
        result = 31 * result + getUser().hashCode();
        return result;
    }


    @OneToOne(fetch=FetchType.LAZY)
    @JsonView(Views.Seller.class)
    @PrimaryKeyJoinColumn(name="USERNAME")
    @XmlTransient
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }


    @OneToMany(mappedBy = "seller")
    @JsonView(Views.SellerInternal.class)
    @XmlTransient
    public Collection<Bid> getBids() {
        return bids;
    }
    public void setBids(Collection<Bid> bids) {
        this.bids = bids;
    }

    @OneToMany(mappedBy = "seller")
    @JsonView(Views.SellerInternal.class)
    @XmlTransient
    public Collection<Item> getItems() {
        return items;
    }
    public void setItems(Collection<Item> items) {
        this.items = items;
    }
}
