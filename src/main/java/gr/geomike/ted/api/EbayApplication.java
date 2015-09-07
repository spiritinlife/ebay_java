package gr.geomike.ted.api;

import gr.geomike.ted.JSON;
import gr.geomike.ted.Views;
import gr.geomike.ted.XML;
import gr.geomike.ted.api.db.EntityDao;
import gr.geomike.ted.api.db.entity.Bid;
import gr.geomike.ted.api.db.entity.Category;
import gr.geomike.ted.api.db.entity.Item;
import gr.geomike.ted.api.db.entity.Location;
import org.glassfish.jersey.server.ResourceConfig;
import javax.ws.rs.ApplicationPath;
import java.util.Collection;
import java.util.List;

@ApplicationPath("api")
public class EbayApplication extends ResourceConfig {

    public EbayApplication() {
        packages("gr.geomike.ted.api");

        register(AuthenticationFilter.class);


        Item item = new Item();
        item.setName("Pencils ( 12 items )");
        item.setBids(null);
        item.setLocationName("Athens");
        item.setBuyPrice(23f);
        item.setCountry("USA");
        item.setCurrently(23f);

        EntityDao.insert(item);
    }
}
