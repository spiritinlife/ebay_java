package gr.geomike.ted.api.db.entity;

import com.fasterxml.jackson.annotation.JsonView;
import gr.geomike.ted.Views;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Collection;


@Entity
@XmlRootElement
public class Seller implements Serializable {
    private static final long serialVersionUID = 1L;
    private int userId;

    private Integer rating;

    private Collection<Bid> bids;
    private User user;

    public Seller() {
    }
    public Seller(int userId, Integer rating, Collection<Bid> bids, User user) {
        this.userId = userId;
        this.rating = rating;
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
    @JsonView(Views.Seller.class)
    public Collection<Bid> getBidsByUserId() {
        return bids;
    }

    public void setBidsByUserId(Collection<Bid> bids) {
        this.bids = bids;
    }

    @OneToOne
    @JsonView(Views.Seller.class)
    @PrimaryKeyJoinColumn(name = "USER_ID", referencedColumnName = "ID")
    public User getUser() {
        return user;
    }

    public void setUser(User userByUserId) {
        this.user = userByUserId;
    }
}
