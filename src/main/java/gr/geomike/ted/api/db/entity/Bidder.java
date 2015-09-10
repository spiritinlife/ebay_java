package gr.geomike.ted.api.db.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    //basic-------------------------
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

    @Basic
    @Column(name = "LOCATION")
    @JsonView(Views.Basic.class)
    @JsonProperty("location")
    @XmlElement(name = "Location")
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    //references-----------------------------------
    @OneToOne(fetch=FetchType.LAZY)
    @PrimaryKeyJoinColumn(name="USERNAME")
    @JsonView(Views.Bidder.class)
    @JsonProperty("user")
    @XmlTransient
    public User getUser() {
        return user;
    }
    public void setUser(User userByUserId) {
        this.user = userByUserId;
    }

    @OneToMany(mappedBy = "bidder", cascade = CascadeType.ALL)
    @JsonView(Views.BidderInternal.class)
    @JsonProperty("bids")
    @XmlTransient
    public List<Bid> getBids() {
        return bids;
    }
    public void setBids(List<Bid> bids) {
        this.bids = bids;
    }
}
