package gr.geomike.ted.api.db;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by spiritinlife on 8/30/15.
 */
public class JPAResource {
    public static final EntityManagerFactory factory = Persistence.createEntityManagerFactory("ebay_ted");
}
