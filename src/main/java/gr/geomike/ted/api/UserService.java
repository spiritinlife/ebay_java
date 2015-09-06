package gr.geomike.ted.api;

import gr.geomike.ted.JSON;
import gr.geomike.ted.Views;
import gr.geomike.ted.api.db.EntityDao;
import gr.geomike.ted.api.db.entity.User;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/users")
public class UserService {

    @PermitAll
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getUsers() {
        return JSON.toJson(EntityDao.Find("User.FindAll"), Views.Item.class);
    }

    @RolesAllowed({"USER", "ADMIN"})
    @POST
    @Path("login")
    public Response getTest(){
        return Response.status(Response.Status.FOUND).build();
    }

    @PermitAll
    @POST
    @Path("/signup")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response signup(User user) {
        EntityDao.insert(user);
        return  Response.status(201).build();
    }
}