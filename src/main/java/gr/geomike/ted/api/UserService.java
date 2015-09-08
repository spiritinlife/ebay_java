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

    @RolesAllowed({"ADMIN"})
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getUsers() {
        return JSON.toJson(EntityDao.Find("User.findAll"), Views.Item.class);
    }


    @RolesAllowed({"ADMIN"})
    @PUT
    @Path("admin/account/{username}/{action}")
    public Response acceptUser(@PathParam("action") String action,@PathParam("username") String username) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("username", username);

        List<User> users = EntityDao.Find("User.findByUsername", params);
        if (action.equals("accept")) {
            users.get(0).setAccountStatus("ACCEPTED");
        } else if (action.equals("reject")) {
            users.get(0).setAccountStatus("REJECTED");
        } else if (action.equals("revoke")) {
            users.get(0).setAccountStatus("PENDING");
        }
        EntityDao.merge(users.get(0));

        return Response.ok().build();
    }


    @RolesAllowed({"ADMIN", "AUTH_USER"})
    @Path("{username}")
    @GET
    @Produces("application/json")
    public String getUser(@PathParam("username") String username) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("username", username);

        List<User> users = EntityDao.Find("User.findByUsername", params);

        return JSON.toJson(users.get(0), Views.Item.class);
    }







    @RolesAllowed({"ADMIN", "AUTH_USER"})
    @Path("{username}/bids")
    @GET
    @Produces("application/json")
    public String getUserBids(@PathParam("username") String username) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("username", username);

        List<Item> items = EntityDao.Find("User.findByUsername", params);

        return JSON.toJson(items.get(0).getBids(), Views.Item.class);
    }

    @PermitAll
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response signUp(User user) {
        user.setRole("USER");
        EntityDao.insert(user);
        return  Response.status(201).build();
    }
}