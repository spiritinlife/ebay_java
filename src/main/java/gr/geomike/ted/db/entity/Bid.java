package gr.geomike.ted.db.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by potis on 31-Aug-15.
 */
@Entity
public class Bid {
    private int id;
    private int itemId;
    private int sellerId;
    private int bidderId;
    private Timestamp time;
    private Integer amount;
    private Bidder bidderByBidderId;
    private Item itemByItemId;
    private Seller sellerBySellerId;

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ITEM_ID")
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    @Basic
    @Column(name = "SELLER_ID")
    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    @Basic
    @Column(name = "BIDDER_ID")
    public int getBidderId() {
        return bidderId;
    }

    public void setBidderId(int bidderId) {
        this.bidderId = bidderId;
    }

    @Basic
    @Column(name = "TIME")
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Basic
    @Column(name = "AMOUNT")
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
    @JoinColumn(name = "BIDDER_ID", referencedColumnName = "USER_ID", nullable = false)
    public Bidder getBidderByBidderId() {
        return bidderByBidderId;
    }

    public void setBidderByBidderId(Bidder bidderByBidderId) {
        this.bidderByBidderId = bidderByBidderId;
    }

    @ManyToOne
    @JoinColumn(name = "ITEM_ID", referencedColumnName = "ID", nullable = false)
    public Item getItemByItemId() {
        return itemByItemId;
    }

    public void setItemByItemId(Item itemByItemId) {
        this.itemByItemId = itemByItemId;
    }

    @ManyToOne
    @JoinColumn(name = "SELLER_ID", referencedColumnName = "USER_ID", nullable = false)
    public Seller getSellerBySellerId() {
        return sellerBySellerId;
    }

    public void setSellerBySellerId(Seller sellerBySellerId) {
        this.sellerBySellerId = sellerBySellerId;
    }
}
