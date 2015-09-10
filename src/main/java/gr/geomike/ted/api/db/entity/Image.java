package gr.geomike.ted.api.db.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import gr.geomike.ted.Views;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "IMAGE")
@XmlRootElement
public class Image {
    private String caption;
    private String url;
    private Item item;

    @Basic
    @Column(name = "CAPTION")
    @JsonView(Views.Basic.class)
    @JsonProperty("caption")
    public String getName() {
        return caption;
    }
    public void setName(String caption) {
        this.caption = caption;
    }

    @Id
    @Column(name = "URL")
    @JsonView(Views.Basic.class)
    @JsonProperty("url")
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    @ManyToOne
    @JoinColumn(name = "ITEM_ID")
    @JsonView(Views.Image.class)
    @JsonProperty("item")
    public Item getItem() {
        return item;
    }
    public void setItem(Item item) {
        this.item = item;
    }
}
