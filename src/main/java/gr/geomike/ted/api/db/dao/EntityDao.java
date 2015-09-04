package gr.geomike.ted.api.db.dao;


import gr.geomike.ted.api.db.JPAResource;

import javax.persistence.*;
import java.util.List;

public class EntityDao {

    public static List<?> Find(String namedQuery, List<QueryParameter> parameters) {
        EntityManager em =  JPAResource.factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Query query = em.createNamedQuery(namedQuery);
        for (QueryParameter param : parameters) {
            query.setParameter(param.getName(), param.getValue());
        }

        List<?> results = query.getResultList();

        tx.commit();
        em.close();

        return results;
    }
}
