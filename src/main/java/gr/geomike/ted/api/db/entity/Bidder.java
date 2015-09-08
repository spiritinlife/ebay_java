package gr.geomike.ted.api.db.entity;

import com.fasterxml.jackson.annotation.JsonView;
import gr.geomike.ted.Views;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.List;

@Entity
@XmlRootElement(name = "Bidder")
@NamedQueries({
        @NamedQuery(name = "Bidder.findByUsername", query = "SELECT b FROM Bidder b WHERE b.username = :username")})
public class Bidder implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username;
    private User user;

    private Integer rating;
    private String location;

    private List<Bid> bids;


    public Bidder() {
    }
    public Bidder(String username) {
        this.username = username;
    }
    public Bidder(String username, Integer rating, String location, List<Bid> bids, User user) {
        this.username = username;
        this.rating = rating;
        this.location = location;
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

    @Basic
    @Column(name = "LOCATION")
    @JsonView(Views.Basic.class)
    @XmlElement(name = "Location")
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

   /* @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bidder)) return false;

        Bidder bidder = (Bidder) o;

        if (!username.equals(bidder.username)) return false;
        if (rating != null ? !rating.equals(bidder.rating) : bidder.rating != null) return false;
        if (location != null ? !location.equals(bidder.location) : bidder.location != null) return false;
        if (bids != null ? !bids.equals(bidder.bids) : bidder.bids != null) return false;
        return user.equals(bidder.user);

    }

    @Override
    public int hashCode() {
        int result = username.hashCode();
        result = 31 * result + (rating != null ? rating.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (bids != null ? bids.hashCode() : 0);
        result = 31 * result + user.hashCode();
        return result;
    }*/

    @OneToOne(fetch=FetchType.LAZY)
    @PrimaryKeyJoinColumn(name="USERNAME")
    @JsonView(Views.Bidder.class)
    @XmlTransient
    public User getUser() {
        return user;
    }
    public void setUser(User userByUserId) {
        this.user = userByUserId;
    }

    @OneToMany(mappedBy = "bidder")
    @JsonView(Views.BidderInternal.class)
    @XmlTransient
    public List<Bid> getBids() {
        return bids;
    }
    public void setBids(List<Bid> bids) {
        this.bids = bids;
    }
}
