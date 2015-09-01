package gr.geomike.ted.api;

import gr.geomike.ted.JSON;
import gr.geomike.ted.api.db.dao.UserDao;
import gr.geomike.ted.api.db.entity.User;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/users")
public class UserService {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getUsers() {
        return JSON.toJson(UserDao.findAll());
    }

    /*@GET
    @Path("{id}")
    @Produces("application/json")
    public String getUser(@PathParam("id") int id) {
        return JSON.toJson(UserDao.find(id));
    }
    @POST
    @Path("{id}")
    //@Produces("application/json")
    public void postUser(@PathParam("id") int id) {
        UserDao.insert(new User());
       // return JSON.toJson(UserDao.find(id));
    }*/

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(User user) {
        /*User userEntity = new User();

        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setUsername(user.getUsername());
        userEntity.setPassword(user.getPassword());
        userEntity.setAddress(user.getAddress());
        userEntity.setPhoneNumber(user.getPhoneNumber());
        userEntity.setSocialSecurityNumber(user.getSocialSecurityNumber());*/

        UserDao.insert(user);

        return  Response.status(201).build();
    }
}