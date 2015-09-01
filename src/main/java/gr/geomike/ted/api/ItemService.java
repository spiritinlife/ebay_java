package gr.geomike.ted.api;

import gr.geomike.ted.JSON;
import gr.geomike.ted.api.db.dao.ItemDao;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/items")
public class ItemService {
    @GET
    @Produces("application/json")
    public String getItems() {
        return JSON.toJson(ItemDao.findAll());
    }



}
