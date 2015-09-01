package gr.geomike.ted.api.db.entity;

//import org.codehaus.jackson.annotate.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collection;


@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="userId")
@XmlRootElement
public class Seller {
    private int userId;

    private Integer rating;

    private Collection<Bid> bids;
    private User user;

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

    @OneToMany(mappedBy = "seller")
    //@JsonBackReference(value="bid-seller")
    public Collection<Bid> getBidsByUserId() {
        return bids;
    }

    public void setBidsByUserId(Collection<Bid> bids) {
        this.bids = bids;
    }

    @OneToOne
    //@JsonBackReference(value="user-bidder")
    @PrimaryKeyJoinColumn(name = "USER_ID", referencedColumnName = "ID")
    public User getUser() {
        return user;
    }

    public void setUser(User userByUserId) {
        this.user = userByUserId;
    }
}
