package gr.geomike.ted.api;

import gr.geomike.ted.api.db.EntityDao;
import gr.geomike.ted.api.db.entity.Item;
import gr.geomike.ted.api.db.entity.Location;
import gr.geomike.ted.api.db.entity.Seller;
import gr.geomike.ted.api.db.entity.User;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;
import java.util.HashMap;
import java.util.List;

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
        seller.setUser(user);
        seller.setRating(5);
        EntityDao.insert(seller);

        // IMPORTANT !!
        // if that works we are good i think
        // Ok in order for this to work when we create seller we need to add the use by setUser
        HashMap<String,Object> params = new HashMap<String, Object>();
        params.put("username","spiritinlife");
        List<Seller> sellers  = EntityDao.Find("Seller.findByUsername",params);
        System.err.println("Lets see " + sellers.get(0).getUser().getFirstName());


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
