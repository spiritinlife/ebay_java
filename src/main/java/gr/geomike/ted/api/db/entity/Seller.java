package gr.geomike.ted.api.db.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import gr.geomike.ted.Views;
import com.fasterxml.jackson.annotation.JsonView;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.List;

@Entity
@XmlRootElement(name = "Seller")
@NamedQueries({
        @NamedQuery(name = "Seller.findByUsername", query = "SELECT s FROM Seller s WHERE s.username = :username")})
public class Seller implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username;
    private User user;

    private Integer rating;

    private List<Item> items;


    public Seller() {
    }
    public Seller(String username, Integer rating, List<Bid> bids, User user) {
        this.username = username;
        this.rating = rating;
        this.user = user;
    }

    @Id
    @Column(name = "USERNAME")
    @JsonView(Views.Basic.class)
    @JsonProperty("username")
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
    @JsonProperty("rating")
    @XmlAttribute(name = "Rating")
    public Integer getRating() {
        return rating;
    }
    public void setRating(Integer rating) {
        this.rating = rating;
    }

    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Seller)) return false;

        Seller seller = (Seller) o;

        if (!username.equals(seller.username)) return false;
        if (!user.equals(seller.user)) return false;
        if (rating != null ? !rating.equals(seller.rating) : seller.rating != null) return false;
        return !(items != null ? !items.equals(seller.items) : seller.items != null);

    }

    @Override
    public int hashCode() {
        int result = username.hashCode();
        result = 31 * result + user.hashCode();
        result = 31 * result + (rating != null ? rating.hashCode() : 0);
        result = 31 * result + (items != null ? items.hashCode() : 0);
        return result;
    }*/

    @OneToOne(fetch=FetchType.LAZY)
    @PrimaryKeyJoinColumn(name="USERNAME")
    @JsonView(Views.Seller.class)
    @JsonProperty("user")
    @XmlTransient
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
    @JsonView(Views.SellerInternal.class)
    @JsonProperty("items")
    @XmlTransient
    public List<Item> getItems() {
        return items;
    }
    public void setItems(List<Item> items) {
        this.items = items;
    }
}
