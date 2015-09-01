package gr.geomike.ted.api.db.dao;

import gr.geomike.ted.api.db.JPAResource;
import gr.geomike.ted.api.db.entity.Item;
import gr.geomike.ted.api.db.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import java.util.List;

public class ItemDao {
    public static List<Item> findAll() {

        EntityManager em =  JPAResource.factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        List<Item> items = em.createNamedQuery("Item.findAll", Item.class).getResultList();
        tx.commit();
        em.close();


        return items;
    }


    public static int insert(User user ) {
        int id = -1;
        EntityManager em =  JPAResource.factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try
        {
            em.persist(user);
            em.flush();
            id = user.getId();
            tx.commit();
            return id;
        }
        catch (PersistenceException e)
        {
            if (tx.isActive()) tx.rollback();
            return id;
        }
        finally
        {
            em.close();
        }
    }


}
