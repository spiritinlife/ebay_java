package gr.geomike.ted.api;

import gr.geomike.ted.JSON;
import gr.geomike.ted.db.dao.CategoryDao;
import gr.geomike.ted.db.dao.ItemDao;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/categories")
public class CategoryService {
    @GET
    @Produces("application/json")
    public String getItems() {
        return JSON.toJson(CategoryDao.findAll());
    }
}
