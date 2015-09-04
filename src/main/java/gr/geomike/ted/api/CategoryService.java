package gr.geomike.ted.api;

import gr.geomike.ted.JSON;
import gr.geomike.ted.Views;
import gr.geomike.ted.api.db.dao.CategoryDao;

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
        String categoriesJson = JSON.toJson(CategoryDao.findAll(), Views.Category.class);
        //System.err.println(categoriesJson);
        return categoriesJson;
    }
}
