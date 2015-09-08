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
    private Timestamp time;
    private Float amount;

    private Bidder bidder;
    private Item item;

    public Bid() {
    }
    public Bid(int id) {
        this.id = id;
    }
    public Bid(int id, int itemId, Timestamp time, Float amount, Bidder bidder, Item item) {
        this.id = id;

        this.itemId = itemId;

        this.time = time;
        this.amount = amount;

        this.bidder = bidder;
        this.item = item;
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

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "ITEM_ID")
    @JsonView(Views.BidInternal.class)
    public Item getItem() {
        return item;
    }
    public void setItem(Item item) {
        this.item = item;
    }

    @ManyToOne
    @JoinColumn(name = "BIDDER_USERNAME")
    @JsonView(Views.Bid.class)
    @XmlElement(name = "Bidder")
    public Bidder getBidder() {
        return bidder;
    }
    public void setBidder(Bidder bidder) {
        this.bidder = bidder;
    }
}
