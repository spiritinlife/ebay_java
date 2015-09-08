package gr.geomike.ted.api.db.entity;

import gr.geomike.ted.Views;
import com.fasterxml.jackson.annotation.JsonView;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlValue;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "CATEGORY")
@XmlRootElement(name="Category")
@NamedQueries({
        @NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c")/*,
        @NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username"),
        @NamedQuery(name = "User.findById", query = "SELECT u FROM User u WHERE u.id = :id"),
        @NamedQuery(name = "User.findByFirstName", query = "SELECT u FROM User u WHERE u.firstName = :firstName"),
        @NamedQuery(name = "User.findByLastName", query = "SELECT u FROM User u WHERE u.lastName = :lastName"),
        @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password")*/})
public class Category implements Serializable{
    private static final long serialVersionUID = 1L;

    private String name;
    private List<Item> items;

    public Category() {
    }
    public Category(String name) {
        this.name = name;
    }
    public Category(String name, List<Item> items) {
        this.name = name;
        this.items = items;
    }

    @Id
    @Column(name = "NAME")
    @JsonView(Views.Basic.class)
    @XmlValue
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        if (name != null ? !name.equals(category.name) : category.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }*/

    @ManyToMany(mappedBy="categories",fetch=FetchType.LAZY)
    @JsonView(Views.Category.class)
    @XmlTransient
    public List<Item> getItems(){
        return items;
    }
    public void setItems( List<Item> items){
        this.items = items;
    }
}
