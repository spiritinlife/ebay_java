package gr.geomike.ted.api.db.dao;

import gr.geomike.ted.api.db.JPAResource;
import gr.geomike.ted.api.db.entity.Category;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
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
