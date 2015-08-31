package gr.geomike.ted.db.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by potis on 31-Aug-15.
 */
@Entity
public class Bidder {
    private int userId;
    private Integer rating;
    private String location;
    private Collection<Bid> bidsByUserId;
    private User userByUserId;

    @Id
    @Column(name = "USER_ID")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "RATING")
    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @Basic
    @Column(name = "LOCATION")
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

    @OneToMany(mappedBy = "bidderByBidderId")
    public Collection<Bid> getBidsByUserId() {
        return bidsByUserId;
    }

    public void setBidsByUserId(Collection<Bid> bidsByUserId) {
        this.bidsByUserId = bidsByUserId;
    }

    @OneToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID", nullable = false)
    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }
}
