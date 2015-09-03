package gr.geomike.ted.api;

import gr.geomike.ted.JSON;
import gr.geomike.ted.api.db.dao.CategoryDao;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/categories")
public class CategoryService {
    @GET
    @Produces("application/json")
    public String getCategories() {

        String json = JSON.toJson(CategoryDao.findAll());
        System.err.println(json);
        return json;
    }
}
