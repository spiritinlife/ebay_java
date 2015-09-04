package gr.geomike.ted.api;

import gr.geomike.ted.JSON;
import gr.geomike.ted.Views;
import gr.geomike.ted.api.db.dao.EntityDao;
import gr.geomike.ted.api.db.dao.QueryParameter;
import gr.geomike.ted.api.db.dao.UserDao;
import gr.geomike.ted.api.db.entity.User;

//import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;


@Path("/users")
public class UserService {

    @PermitAll
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getUsers() {
        return JSON.toJson(UserDao.findAll(), Views.Item.class);
    }

    /*@RolesAllowed("USER")
    @POST
    @Path("login")
    public Response getTest(@HeaderParam("username") String username, @HeaderParam("password") String password){
        List<QueryParameter> params =  new ArrayList<QueryParameter>();
        params.add(new QueryParameter("username", username));
        params.add(new QueryParameter("password", password));
        List<User> users = ((List<User>) EntityDao.Find("User.findByUsernameAndPassword", params));

        System.out.println(users.get(0));

        if (users.isEmpty())
            return Response.status(Response.Status.NOT_FOUND).build();
        else
            return Response.status(Response.Status.FOUND).build();
    }*/

    @RolesAllowed("USER")
    @POST
    @Path("login")
    public Response getTest(){
        System.err.println("passed?");
        return Response.status(Response.Status.FOUND).build();
    }

    @PermitAll
    @POST
    @Path("signup")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response signup(User user) {
        UserDao.insert(user);
        return  Response.status(201).build();
    }
}