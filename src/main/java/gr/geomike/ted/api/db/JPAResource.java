package gr.geomike.ted.api.db;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAResource {
    public static final EntityManagerFactory factory = Persistence.createEntityManagerFactory("ebay_ted");
}
