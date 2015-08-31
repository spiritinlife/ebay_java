package gr.geomike.ted.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collection;

/**
 * Created by potis on 31-Aug-15.
 */
@Entity
public class Category {
    private String name;
    private Collection<ItemHasCategory> itemHasCategoriesByName;

    @Id
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
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
    }

    @OneToMany(mappedBy = "categoryByCategoryName")
    public Collection<ItemHasCategory> getItemHasCategoriesByName() {
        return itemHasCategoriesByName;
    }

    public void setItemHasCategoriesByName(Collection<ItemHasCategory> itemHasCategoriesByName) {
        this.itemHasCategoriesByName = itemHasCategoriesByName;
    }
}
