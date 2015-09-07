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


@Path("/users")
public class UserService {

    @PermitAll
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getUsers() {
        return JSON.toJson(EntityDao.Find("User.FindAll"), Views.Item.class);
    }

    /*@RolesAllowed({"USER", "ADMIN"})
    @POST
    @Path("login")
    public Response getTest(){
        return Response.status(Response.Status.FOUND).build();
    }*/

    @RolesAllowed("USER")
    @Path("{id}")
    @GET
    @Produces("application/json")
    public String getUser(@PathParam("id") String username) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("username", username);

        List<Item> items = EntityDao.Find("User.findByUsername", params);

        return JSON.toJson(items.get(0), Views.Item.class);
    }

    @PermitAll
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response signUp(User user) {
        System.err.println("REACHED");
        user.setRole("USER");
        EntityDao.insert(user);
        return  Response.status(201).build();
    }
}