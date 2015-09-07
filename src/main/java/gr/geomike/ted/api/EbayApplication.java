package gr.geomike.ted.api;

import gr.geomike.ted.api.db.EntityDao;
import gr.geomike.ted.api.db.entity.*;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("api")
public class EbayApplication extends ResourceConfig {

    public EbayApplication() {
        packages("gr.geomike.ted.api");

        register(AuthenticationFilter.class);

        Location location = new Location();
        location.setName("Athens");
        EntityDao.insert(location);

        User user = new User();
        user.setFirstName("George");
        user.setLastName("Chailazopoulos");
        user.setRole("ADMIN");
        user.setPassword("ted123");
        user.setEmail("ted@ted.com");
        user.setCountry("Greece");
        user.setUsername("spiritinlife");
        EntityDao.insert(user);


        Seller seller = new Seller();
        seller.setUsername("spiritinlife");
        seller.setRating(5);
        EntityDao.insert(seller);



        Item item = new Item();
        item.setName("Pencils ( 12 items )");
        item.setBids(null);
        item.setSeller(seller);
        item.setLocation(location);
        item.setBuyPrice(23f);
        item.setCountry("USA");
        item.setCurrently(23f);
        EntityDao.insert(item);
    }
}
