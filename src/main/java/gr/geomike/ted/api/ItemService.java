package gr.geomike.ted.api;

import gr.geomike.ted.JSON;
import gr.geomike.ted.Views;
import gr.geomike.ted.api.db.EntityDao;
import gr.geomike.ted.api.db.entity.Item;

import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/items")
public class ItemService {
    @PermitAll
    @GET
    @Produces("application/json")
    public String getItems() {
        String itemsJson = JSON.toJson(EntityDao.Find("Item.findAll"), Views.Item.class);
        return itemsJson;
    }

    @PermitAll
    @Path("{id}")
    @GET
    @Produces("application/json")
    public String getItem(@PathParam("id") int id) {
        /*List<QueryParameter> params = new ArrayList<QueryParameter>();
        params.add(new QueryParameter("id", id));*/
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);

        List<Item> items = EntityDao.Find("Item.findById", params);

        return JSON.toJson(items.get(0), Views.Item.class);
    }
}
