package gr.geomike.ted.api.db.entity;

import com.fasterxml.jackson.annotation.JsonView;
import gr.geomike.ted.Views;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@XmlRootElement
public class Bid implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;

    private int itemId;
    private int sellerId;
    private int bidderId;

    private Timestamp time;
    private Integer amount;

    private Bidder bidder;
    private Item item;
    private Seller seller;

    public Bid() {
    }
    public Bid(int id) {
        this.id = id;
    }
    public Bid(int id, int itemId, int sellerId, int bidderId, Timestamp time, Integer amount, Bidder bidder, Item item, Seller seller) {
        this.id = id;
        this.itemId = itemId;
        this.sellerId = sellerId;
        this.bidderId = bidderId;
        this.time = time;
        this.amount = amount;
        this.bidder = bidder;
        this.item = item;
        this.seller = seller;
    }

    @Id
    @Column(name = "ID")
    @JsonView(Views.Internal.class)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ITEM_ID")
    @JsonView(Views.Basic.class)
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    @Basic
    @Column(name = "SELLER_ID")
    @JsonView(Views.Basic.class)
    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    @Basic
    @Column(name = "BIDDER_ID")
    @JsonView(Views.Basic.class)
    public int getBidderId() {
        return bidderId;
    }

    public void setBidderId(int bidderId) {
        this.bidderId = bidderId;
    }

    @Basic
    @Column(name = "TIME")
    @JsonView(Views.Basic.class)
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Basic
    @Column(name = "AMOUNT")
    @JsonView(Views.Basic.class)
    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bid bid = (Bid) o;

        if (id != bid.id) return false;
        if (itemId != bid.itemId) return false;
        if (sellerId != bid.sellerId) return false;
        if (bidderId != bid.bidderId) return false;
        if (time != null ? !time.equals(bid.time) : bid.time != null) return false;
        if (amount != null ? !amount.equals(bid.amount) : bid.amount != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + itemId;
        result = 31 * result + sellerId;
        result = 31 * result + bidderId;
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JsonView(Views.Bid.class)
    @JoinColumn(name = "BIDDER_ID", referencedColumnName = "USER_ID", nullable = false, insertable=false, updatable=false)
    public Bidder getBidder() {
        return bidder;
    }

    public void setBidder(Bidder bidder) {
        this.bidder = bidder;
    }

    @ManyToOne
    @JsonView(Views.Bid.class)
    @JoinColumn(name = "ITEM_ID", referencedColumnName = "ID", nullable = false, insertable=false, updatable=false)
    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @ManyToOne
    @JsonView(Views.Bid.class)
    @JoinColumn(name = "SELLER_ID", referencedColumnName = "USER_ID", nullable = false, insertable=false, updatable=false)
    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }
}
