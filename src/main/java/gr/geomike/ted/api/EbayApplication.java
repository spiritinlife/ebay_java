package gr.geomike.ted.api;

import gr.geomike.ted.api.db.EntityDao;
import gr.geomike.ted.api.db.entity.*;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@ApplicationPath("api")
public class EbayApplication extends ResourceConfig {

    public EbayApplication() {
        packages("gr.geomike.ted.api");

        register(AuthenticationFilter.class);

        //PERSIST TEST
        /*Location location = new Location();
        location.setName("Athens");

        User user = new User();
        user.setFirstName("George");
        user.setLastName("Chailazopoulos");
        user.setRole("ADMIN");
        user.setPassword("ted123");
        user.setEmail("ted@ted.com");
        user.setCountry("Greece");
        user.setUsername("spiritinlife");

        Seller seller = new Seller();
        seller.setUsername("spiritinlife");
        seller.setUser(user);
        seller.setRating(5);

        Bidder bidder = new Bidder();
        bidder.setUsername("spiritinlife");
        bidder.setUser(user);
        bidder.setRating(4);

        user.setSeller(seller);
        user.setBidder(bidder);

        Item item = new Item();
        item.setName("Pencils ( 12 items )");
        item.setBids(null);
        item.setSeller(seller);
        item.setLocation(location);
        item.setBuyPrice(23f);
        item.setCountry("USA");
        item.setCurrently(23f);

        List<Item> items = new ArrayList<Item>();
        items.add(item);
        user.getSeller().setItems(items);

        EntityDao.insert(user);

        HashMap<String,Object> params = new HashMap<String, Object>();
        params.put("username","spiritinlife");
        List<User> users  = EntityDao.Find("User.findByUsername",params);
        System.err.println("Lets see " + users.get(0).getSeller().getItems().get(0).getName());*/
    }
}
