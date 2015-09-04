package gr.geomike.ted.api.db.entity;

import com.fasterxml.jackson.annotation.JsonView;
import gr.geomike.ted.Views;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Collection;

@Entity
@XmlRootElement
public class Bidder implements Serializable {
    private static final long serialVersionUID = 1L;

    private int userId;

    private Integer rating;
    private String location;

    private Collection<Bid> bids;
    private User user;

    public Bidder() {
    }
    public Bidder(int userId) {
        this.userId = userId;
    }
    public Bidder(int userId, Integer rating, String location, Collection<Bid> bids, User user) {
        this.userId = userId;
        this.rating = rating;
        this.location = location;
        this.bids = bids;
        this.user = user;
    }

    @Id
    @Column(name = "USER_ID")
    @JsonView(Views.Basic.class)
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "RATING")
    @JsonView(Views.Basic.class)
    public Integer getRating() {
        return rating;
    }
    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @Basic
    @Column(name = "LOCATION")
    @JsonView(Views.Basic.class)
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bidder bidder = (Bidder) o;

        if (userId != bidder.userId) return false;
        if (rating != null ? !rating.equals(bidder.rating) : bidder.rating != null) return false;
        if (location != null ? !location.equals(bidder.location) : bidder.location != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (rating != null ? rating.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "bidder")
    @JsonView(Views.Bidder.class)
    public Collection<Bid> getBids() {
        return bids;
    }
    public void setBids(Collection<Bid> bids) {
        this.bids = bids;
    }

    @OneToOne
    @JsonView(Views.Bidder.class)
    @PrimaryKeyJoinColumn(name = "USER_ID", referencedColumnName = "ID")
    public User getUser() {
        return user;
    }
    public void setUser(User userByUserId) {
        this.user = userByUserId;
    }
}
