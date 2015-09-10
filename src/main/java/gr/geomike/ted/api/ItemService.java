package gr.geomike.ted.api;

import gr.geomike.ted.JSON;
import gr.geomike.ted.Views;
import gr.geomike.ted.api.db.EntityDao;
import gr.geomike.ted.api.db.entity.Item;
import gr.geomike.ted.api.db.entity.User;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);

        List<Item> items = EntityDao.Find("Item.findById", params);

        return JSON.toJson(items.get(0), Views.Item.class);
    }

    @RolesAllowed({"USER"})
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createItem(Item item) {
        System.err.println(item);
        return  Response.status(201).build();
    }
}
