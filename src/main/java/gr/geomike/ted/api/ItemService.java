package gr.geomike.ted.api;

import gr.geomike.ted.JSON;
import gr.geomike.ted.Views;
import gr.geomike.ted.api.db.dao.ItemDao;

import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/items")
public class ItemService {
    @PermitAll
    @GET
    @Produces("application/json")
    public String getItems() {
        String itemsJson = JSON.toJson(ItemDao.findAll(), Views.Item.class);
        //System.err.println(itemsJson);
        return itemsJson;
    }



}
