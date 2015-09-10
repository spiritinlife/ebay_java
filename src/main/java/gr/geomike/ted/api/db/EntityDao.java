package gr.geomike.ted.api.db;


import gr.geomike.ted.api.db.JPAResource;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

public class EntityDao {

    public static  List Find(String namedQuery, Map<String,Object> parameters) {
        EntityManager em =  JPAResource.factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Query query = em.createNamedQuery(namedQuery);
        for (Map.Entry<String, Object> param : parameters.entrySet()) {
            query.setParameter(param.getKey(), param.getValue());
        }

        List results =  query.getResultList();

        tx.commit();
        em.close();

        return results;
    }

    public static  List Find(String namedQuery, String paramName, Object paramValue) {
        EntityManager em =  JPAResource.factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Query query = em.createNamedQuery(namedQuery);
        query.setParameter(paramName, paramValue);

        List results =  query.getResultList();

        tx.commit();
        em.close();

        return results;
    }

    public static  List Find(String namedQuery) {
        EntityManager em =  JPAResource.factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Query query = em.createNamedQuery(namedQuery);

        List results =  query.getResultList();

        tx.commit();
        em.close();

        return results;
    }

    public static void insert(Object entity) {

        EntityManager em =  JPAResource.factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try
        {
            em.persist(entity);
            em.flush();
            tx.commit();
        }
        catch (PersistenceException e)
        {
            if (tx.isActive()) tx.rollback();
        }
        finally
        {
            em.close();
        }
    }

    public static void merge(Object entity) {

        EntityManager em =  JPAResource.factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try
        {
            em.merge(entity);
            em.flush();
            tx.commit();
        }
        catch (PersistenceException e)
        {
            if (tx.isActive()) tx.rollback();
        }
        finally
        {
            em.close();
        }
    }
}
