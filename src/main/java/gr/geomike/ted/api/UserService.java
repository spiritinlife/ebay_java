package gr.geomike.ted.api;


import gr.geomike.ted.JSON;
import gr.geomike.ted.db.dao.UserDao;
import gr.geomike.ted.db.entity.User;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/users")
public class UserService {

    @POST
    @Produces("application/json")
    public String postUser() {
        User user = new User("DWDW","DWDWDW","DWDWDW","DWDWDW");
        UserDao.insert(user);

        return JSON.toJson(user);
    }

    @GET
    @Produces("application/json")
    public String getUser() {
        return JSON.toJson(UserDao.findAll());
    }

}