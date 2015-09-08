package gr.geomike.ted.api;

import gr.geomike.ted.JSON;
import gr.geomike.ted.Views;
import gr.geomike.ted.api.db.EntityDao;
import gr.geomike.ted.api.db.entity.Item;
import gr.geomike.ted.api.db.entity.User;
import org.glassfish.jersey.internal.util.Base64;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;


@Path("/users")
public class UserService {

    @PermitAll
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getUsers() {
        return JSON.toJson(EntityDao.Find("User.findAll"), Views.Item.class);
    }

    @RolesAllowed({"ADMIN", "AUTH_USER"})
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
    @GET
    @Path("/login")
    @Produces("application/json")
    public String login(@QueryParam(AuthenticationFilter.AUTHORIZATION_PROPERTY) String auth){
        System.err.println("DWDWDW");
        final String encodedUserPassword = auth.replaceFirst(AuthenticationFilter.AUTHENTICATION_SCHEME + " ", "");
        String usernameAndPassword = new String(Base64.decode(encodedUserPassword.getBytes()));

        final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
        final String username = tokenizer.nextToken();
        final String password = tokenizer.nextToken();

        HashMap<String,Object> params = new HashMap<String,Object>();
        params.put("username",username);
        params.put("password",password);

        List<User> users = EntityDao.Find("User.findByUsernameAndPassword",params);

        String j =  JSON.toJson(users.get(0), Views.User.class);
        System.err.println(j);
        return j;
    }

    @RolesAllowed({"ADMIN", "AUTH_USER"})
    @Path("{id}/bids")
    @GET
    @Produces("application/json")
    public String getUserBids(@PathParam("id") String username) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("username", username);

        List<Item> items = EntityDao.Find("User.findByUsername", params);

        return JSON.toJson(items.get(0), Views.Item.class);
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