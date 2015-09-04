package gr.geomike.ted.api.db.dao;

import gr.geomike.ted.api.db.JPAResource;
import gr.geomike.ted.api.db.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import java.util.List;

/**
* Created by spiritinlife on 8/30/15.
*/
public class UserDao {

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

    public static User find(int id) {
        User user = null;

        EntityManager em =  JPAResource.factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        user = em.find(User.class, id);

        tx.commit();
        em.close();


        return user;
    }

    public static List<User> findAll() {

        EntityManager em =  JPAResource.factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

       List<User> users = em.createNamedQuery("User.findAll", User.class).getResultList();

        tx.commit();
        em.close();


        return users;
    }
}
