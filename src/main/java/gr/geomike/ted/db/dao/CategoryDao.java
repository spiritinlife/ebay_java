package gr.geomike.ted.db.dao;

import gr.geomike.ted.db.JPAResource;
import gr.geomike.ted.db.entity.Category;
import gr.geomike.ted.db.entity.Item;
import gr.geomike.ted.db.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import java.util.List;

public class CategoryDao {
    public static List<Category> findAll() {

        EntityManager em =  JPAResource.factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        List<Category> categories = em.createNamedQuery("Category.findAll", Category.class).getResultList();

        tx.commit();
        em.close();


        return categories;
    }


}
