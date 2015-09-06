package gr.geomike.ted.api.db.entity;

import com.fasterxml.jackson.annotation.JsonView;
import gr.geomike.ted.CurrencyAdapter;
import gr.geomike.ted.DateAdapter;
import gr.geomike.ted.Views;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@XmlRootElement(name = "Bid")
public class Bid implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;

    private int itemId;
    private String sellerUsername;
    private String bidderUsername;

    private Timestamp time;
    private Float amount;

    private Bidder bidder;
    private Item item;
    private Seller seller;

    public Bid() {
    }
    public Bid(int id) {
        this.id = id;
    }
    public Bid(int id, int itemId, String sellerUsername, String bidderUsername, Timestamp time, Float amount, Bidder bidder, Item item, Seller seller) {
        this.id = id;

        this.itemId = itemId;
        this.sellerUsername = sellerUsername;
        this.bidderUsername = bidderUsername;

        this.time = time;
        this.amount = amount;

        this.bidder = bidder;
        this.item = item;
        this.seller = seller;
    }

    @Id
    @Column(name = "ID")
    @JsonView(Views.Basic.class)
    @XmlTransient
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ITEM_ID")
    @JsonView(Views.Basic.class)
    @XmlTransient
    public int getItemId() {
        return itemId;
    }
    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    @Basic
    @Column(name = "SELLER_USERNAME")
    @JsonView(Views.Basic.class)
    @XmlTransient
    public String getSellerUsername() {
        return sellerUsername;
    }
    public void setSellerUsername(String sellerId) {
        this.sellerUsername = sellerId;
    }

    @Basic
    @Column(name = "BIDDER_USERNAME")
    @JsonView(Views.Basic.class)
    @XmlTransient
    public String getBidderUsername() {
        return bidderUsername;
    }
    public void setBidderUsername(String bidderUsername) {
        this.bidderUsername = bidderUsername;
    }

    @Basic
    @Column(name = "TIME")
    @JsonView(Views.Basic.class)
    @XmlJavaTypeAdapter(DateAdapter.class)
    @XmlElement(name = "Time")
    public Timestamp getTime() {
        return time;
    }
    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Basic
    @Column(name = "AMOUNT")
    @JsonView(Views.Basic.class)
    @XmlJavaTypeAdapter(CurrencyAdapter.class)
    @XmlElement(name = "Amount")
    public Float getAmount() {
        return amount;
    }
    public void setAmount(Float amount) {
        this.amount = amount;
    }

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JsonView(Views.BidInternal.class)
    @JoinColumn(name = "ITEM_ID", referencedColumnName = "ID"
            , nullable = false, insertable=false, updatable=false)
    public Item getItem() {
        return item;
    }
    public void setItem(Item item) {
        this.item = item;
    }

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JsonView(Views.Bid.class)
    @JoinColumn(name = "BIDDER_USERNAME", referencedColumnName = "USERNAME",
            nullable = false, insertable=false, updatable=false)
    @XmlElement(name = "Bidder")
    public Bidder getBidder() {
        return bidder;
    }
    public void setBidder(Bidder bidder) {
        this.bidder = bidder;
    }

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JsonView(Views.Bid.class)
    @JoinColumn(name = "SELLER_USERNAME", referencedColumnName = "USERNAME"
            , nullable = false, insertable=false, updatable=false)
    @XmlElement(name = "Seller")
    public Seller getSeller() {
        return seller;
    }
    public void setSeller(Seller seller) {
        this.seller = seller;
    }
}
