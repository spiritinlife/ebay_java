package gr.geomike.ted.api;

import gr.geomike.ted.JSON;
import gr.geomike.ted.Views;
import gr.geomike.ted.api.db.EntityDao;

import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;


@Path("/categories")
public class CategoryService {

    @PermitAll
    @GET
    @Produces("application/json")
    public String getItems() {
        String categoriesJson = JSON.toJson(EntityDao.Find("Category.findAll"), Views.Category.class);
        return categoriesJson;
    }
}
