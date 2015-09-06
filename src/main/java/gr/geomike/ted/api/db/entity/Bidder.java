package gr.geomike.ted.api.db.entity;

import com.fasterxml.jackson.annotation.JsonView;
import gr.geomike.ted.Views;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Collection;

@Entity
@XmlRootElement(name = "Bidder")
public class Bidder implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username;

    private Integer rating;
    private String location;

    private Collection<Bid> bids;
    private User user;

    public Bidder() {
    }
    public Bidder(String username) {
        this.username = username;
    }
    public Bidder(String username, Integer rating, String location, Collection<Bid> bids, User user) {
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

    @OneToMany(mappedBy = "bidder")
    @JsonView(Views.BidderInternal.class)
    @XmlTransient
    public Collection<Bid> getBids() {
        return bids;
    }
    public void setBids(Collection<Bid> bids) {
        this.bids = bids;
    }

    @OneToOne
    @JsonView(Views.Bidder.class)
    @PrimaryKeyJoinColumn(name = "USERNAME", referencedColumnName = "USERNAME")
    @XmlTransient
    public User getUser() {
        return user;
    }
    public void setUser(User userByUserId) {
        this.user = userByUserId;
    }
}
