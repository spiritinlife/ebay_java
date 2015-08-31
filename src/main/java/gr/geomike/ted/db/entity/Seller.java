package gr.geomike.ted.db.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by potis on 31-Aug-15.
 */
@Entity
public class Seller {
    private int userId;
    private Integer rating;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Seller seller = (Seller) o;

        if (userId != seller.userId) return false;
        if (rating != null ? !rating.equals(seller.rating) : seller.rating != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (rating != null ? rating.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "sellerBySellerId")
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
